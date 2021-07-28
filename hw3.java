// Saimanasa Juluru
// CS-UY 3913
// HW 3 : Building off of hw2, modify your button GUI program so that the buttons change color, automatically,
// about every second unless they've been pressed. Pressing a button only stops the current button from changing.
// Pressing the button again changes its color. You can press other buttons to stop them from automatically
// changing, as well.

import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class newButton extends JButton {
    boolean pressed;
    buttonThread bThread;

    newButton() {
        pressed = false;
        bThread = new buttonThread(this);
    }
}

class buttonThread extends Thread {
    newButton button;

    buttonThread(newButton _button) {
        button = _button;
    }

    @Override
    public void run() {
        while(true) {
            try {
                sleep(500);
                if(!button.pressed) { // if the button isn't pressed
                    Color new_color = hw3.getRandColor(); // change color randomly
                    button.setBackground(new_color);
                }
                else { // if the button is pressed
                    sleep(500); // sleep (changes color and stays on that color)
                }
            }
            catch(Exception e) {}
        }
    }
}


public class hw3 { // similar format to hw2, as well
    static final int GRID_ROWS = 4;
    static final int GRID_COLS = 2;
    static final int num_buttons = GRID_ROWS * GRID_COLS;

    static Color getRandColor() { // returns color itself
        Random rand = new Random();
        int[] rgbVal = new int[3];

        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);

        rgbVal[0] = r;
        rgbVal[1] = g;
        rgbVal[2] = b;

        return new Color(r, g, b);
    }

    public static void main(String[] args) { // similar main to that of hw2
        JFrame jf = new JFrame("Pick a button, any button! (Automated Version)");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 500);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(GRID_ROWS, GRID_COLS));

        newButton[] buttons = new newButton[num_buttons];

        for (int i = 0; i < num_buttons; i++) {
            Color currColor = getRandColor();

           // create a new button
            newButton currButton = new newButton();
            currButton.setBackground(currColor);
            currButton.setOpaque(true);
            currButton.setBorderPainted(false);

            currButton.addActionListener(new ButtonListener(buttons, currButton));
            // add to the beginning of the thread
            currButton.bThread.start();

            buttons[i] = currButton;
            jp.add(buttons[i]);
        }

        jf.add(jp);
        jf.setVisible(true);
    }
}

class ButtonListener implements ActionListener {
    newButton[] buttons;
    newButton currButton;

    ButtonListener(newButton[] _buttons, newButton _currButton) {
        buttons = _buttons;
        currButton = _currButton;
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        currButton.pressed = true;

        for (newButton button : buttons) {
            if (button != currButton) {
                Color new_color = hw3.getRandColor();
                button.setBackground(new_color);
            }
        }
    }
}

