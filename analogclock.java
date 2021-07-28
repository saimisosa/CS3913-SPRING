// Saimanasa Juluru
// CS3913
// 2 May 2021
// Make an analog clock using the Java Calander and Graphics class. Only focus on the lines, not numbers on the face.
// Make the seconds hand purple, minutes hand blue, and hours hand red. Make sure to have it "tic" as well.

import java.awt.*;
import java.net.Socket;
import java.util.*;
import javax.swing.*;

public class analogclock {

    // setting up colors again for easy use
    final static Color lightpink = new Color(255, 203, 240, 255); // background
    final static Color lighterpink = new Color(255, 216, 247); // clock circle

    final static Color purple = new Color(183, 117, 255); // seconds hand
    final static Color blue = new Color(46, 79, 179); // minutes hand
    final static Color red = new Color(167, 35, 35); // hours hand

    static int sec, min, hr;

    public static void main(String[] args) {
        JFrame jf = new JFrame("Java Analog Clock");
        jf.setSize(400,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setting up the analog clock panel
        AnalogPanel analogPanel = new AnalogPanel();
        analogPanel.setLayout(new BorderLayout());
        jf.add(analogPanel, BorderLayout.CENTER);

        getUTC();

        new Thread() {
            public void run() {
                while (true){
                    if(sec % 60 == 0) {
                        getUTC();
                    }
                    sec += 1;
                    analogPanel.repaint(); // triggers the clock to be updated aka redrawn
                    try {
                        sleep(1000);
                    }
                    catch(InterruptedException ignored) {}
                }
            }
        }.start();

        JPanel digital_panel = new JPanel();
        digital_panel.setLayout(new BorderLayout());
        JLabel digital = new JLabel("<html><br/>Time:" + hr + ":" + min + ":" + sec + "<br/><br/></html>",
                SwingConstants.CENTER);
        digital_panel.add(digital);

        jf.add(digital_panel, BorderLayout.SOUTH);
        jf.setVisible(true); // so we can actually see the clock
    }

    private static void getUTC() {
        try {
            Socket socket = new Socket("ntp-b.nist.gov", 13); // shows the time in UTC

            if (socket.isConnected()) {
                Scanner input = new Scanner(socket.getInputStream());
                input.nextLine();
                String[] data = input.nextLine().substring(15, 23).split(":");

                analogclock.hr = Integer.parseInt(data[0]);
                analogclock.min = Integer.parseInt(data[1]);
                analogclock.sec = Integer.parseInt(data[2]);
            }

            System.out.println(hr + ":" + min + ":" + sec);
        }
        catch (Exception ignored) { }
    }

}
class AnalogPanel extends JPanel{
    int rad;

    AnalogPanel() {
        super();
        analogclock.sec = 0;
    }

    Point getPos(String unit) { // calculates coordinate points in order to accurately draw lines
        Point point = new Point();
        double xVal = 0;
        double yVal = 0;

        if (unit == "second") {
            xVal = Math.sin(Math.toRadians(analogclock.sec) * 6);
            yVal = Math.cos(Math.toRadians(analogclock.sec) * 6);
        }
        else if (unit == "minute") {
            xVal = Math.sin(Math.toRadians(analogclock.min * 6 + analogclock.sec / 10));
            yVal = Math.cos(Math.toRadians(analogclock.min * 6 + analogclock.sec / 10));
        }
        else if (unit == "hour") {
            xVal = Math.sin(Math.toRadians((analogclock.hr % 12) * 30 + analogclock.min / 2));
            yVal = Math.cos(Math.toRadians((analogclock.hr % 12) * 30 + analogclock.min / 2));
        }
        else {
            System.out.println("Invalid format.");
        }

        point.x = (int)(xVal * rad);
        point.y = (int)(yVal * rad);

        return point;
    }

    protected void paintComponent(Graphics clockface) {
        // formatting the background of the clock
        int height = this.getSize().height;
        int width = this.getSize().width;
        clockface.setColor(analogclock.lightpink);
        clockface.fillRect(0, 0, width, height);

        // formatting the face of the clock
        rad = height / 3;
        int xCenter = width / 2;
        int xpCenter = xCenter - rad;

        int yCenter = height / 2;
        int yptCenter = yCenter - rad;

        int size = rad * 2; // used for width and height

        clockface.setColor(analogclock.lighterpink);
        clockface.fillOval(xpCenter, yptCenter, size, size);

        // formatting the hands of the clock

        // seconds hand
        Point seconds = getPos("second");
        clockface.setColor(analogclock.purple);
        clockface.drawLine(xCenter, yCenter, xCenter + seconds.x, yCenter - seconds.y);

        // minutes hand
        Point minutes = getPos("minute");
        clockface.setColor(analogclock.blue);
        clockface.drawLine(xCenter, yCenter, xCenter + minutes.x, yCenter - minutes.y);

        // hour hand
        Point hours = getPos("hour");
        clockface.setColor(analogclock.red);
        clockface.drawLine(xCenter, yCenter, xCenter + hours.x, yCenter - hours.y);
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point() {
        this(0,0);
    }
}