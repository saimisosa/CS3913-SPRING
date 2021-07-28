import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class voting {
    static boolean choseCoffee;
    static boolean choseTea;
    static boolean choseOther;

    public static void main(String []args) {

        JFrame jf = new JFrame("Drink Poll");
        JPanel jp = new JPanel();
        jp.setBackground(Color.white);
        jp.setLayout(new BorderLayout());

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,400);

        jf.add(jp);

        JPanel window = new JPanel();
        window.setLayout(new GridLayout(1,3));
        jp.add(window,BorderLayout.SOUTH);

        JButton coffee = new JButton("Coffee");
        JButton tea = new JButton("Tea");
        JButton other = new JButton("Other");

        coffee.setBackground(Color.RED);
        coffee.setOpaque(true);
        coffee.setBorderPainted(false);

        tea.setBackground(Color.PINK);
        tea.setOpaque(true);
        tea.setBorderPainted(false);

        other.setBackground(Color.BLUE);
        other.setOpaque(true);
        other.setBorderPainted(false);

        window.add(coffee);
        window.add(tea);
        window.add(other);

        JButton confirm = new JButton("Confirm or Exit");
        confirm.setBackground(Color.PINK);
        confirm.setOpaque(true);
        confirm.setBorderPainted(false);

        ButtonListener coffeeButton = new ButtonListener(confirm, coffee);
        ButtonListener teaButton = new ButtonListener(confirm, tea);
        ButtonListener otherButton = new ButtonListener(confirm, other);


        jf.setVisible(true);

    }
}

class ButtonListener implements ActionListener {
    JButton currentButton, confirmButton;
    ButtonListener (JButton theconfirmButton, JButton newButton) {
        currentButton = newButton;
        confirmButton = theconfirmButton;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        currentButton.addActionListener((new ConfirmationListener(confirmButton, currentButton)));
    }
}

    class ConfirmationListener implements ActionListener {
        JButton confirmButton, currentButton;
        private int i, j, k;
        ConfirmationListener (JButton theconfirmButton, JButton thecurrentButton ) {
            currentButton = thecurrentButton;
            confirmButton = theconfirmButton;
            int i = 0;
            int j = 0;
            int k = 0;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (voting.choseCoffee) {
                i++;
                System.out.println(i);
            }
            if (voting.choseTea) {
                j++;
                System.out.println(j);
            }
            if (voting.choseOther) {
                k++;
                System.out.println(k);
            }
        }
    }
