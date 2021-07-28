// Saimanasa Juluru
// HW2
// 8 March 2021
// Create a window with 8 buttons (this may change, in the future, to 100 buttons so make sure to be able to do that easily, hint: use an array)
// organized in a 4x2 grid.  Have the background color (see java class Color) of each button start at some random value (See java class Random).
// When a button is pressed, it should cause every OTHER button to change background color.  The button pressed should not change background color at all.
// Detecting which button was pressed might be a bit of a challenge, so here's a hint: look at the parameter passed to the ActionListener class.

import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class button {
    static final int ROWS = 4;
    static final int COLUMNS = 2;
    static final int numofButtons = ROWS * COLUMNS;

    public static void main(String []args) {
        JFrame jf = new JFrame("Push A Button - Any Button!");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 500);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(ROWS, COLUMNS)); // constructs the grid

        JButton[] buttons = new JButton[numofButtons];

        for (int i = 0; i < numofButtons; i++) {

            Random randInt = new Random(); // generates random colors
            int r = randInt.nextInt(256);
            int g = randInt.nextInt(256);
            int b = randInt.nextInt(256);
            Color currColor = new Color(r, g, b);


            JButton button = new JButton(); // creates a new button using the JButton library
            button.setBackground(currColor);
            button.setOpaque(true);
            button.setBorderPainted(false);

            button.addActionListener(new ButtonListener(buttons, button));
            buttons[i] = button; // sets to random color
            jp.add(buttons[i]); // adds the newly colored button to array
        }

        jf.add(jp);
        jf.setVisible(true); // otherwise we wouldn't be able to see it!
    }
}

class ButtonListener implements ActionListener {
    JButton[] buttons;
    JButton currButton;

    ButtonListener(JButton[] _buttons, JButton _currButton) {
        buttons = _buttons;
        currButton = _currButton;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        for (JButton button : buttons) {
            if (button != currButton) { // makes sure the current button clicked doesn't change, either
                Random randInt = new Random();
                int r = randInt.nextInt(256);
                int g = randInt.nextInt(256);
                int b = randInt.nextInt(256);
                Color newColor = new Color(r, g, b);

                button.setBackground(newColor);
            }
        }
    }
}
