import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class client extends JPanel implements ActionListener {
    // used a random color palette generator
    final static Color grey = new Color(32, 32, 32);
    final static Color lightgrey = new Color(85, 85, 85);
    final static Color pink = new Color(218, 98, 125);
    final static Color blush = new Color(255, 165, 171);
    final static Color peach = new Color(249, 219, 189);

    // declaring all the variables
    protected JPanel inputs;
    protected JTextField textBox1;
    protected JButton sendButton;
    protected JLabel instructions;
    protected static JTextArea messageSpace;
    protected JPanel chatBox;

    // for user info storage
    protected JPanel userInfo;
    protected JTextField textBox2;
    protected JTextField textBox3;
    protected JButton connectButton;

    static String username;
    static int port;
    static PrintStream output;

    public client() {
       // configuring the input
        inputs = new JPanel();
        inputs.setLayout(new BorderLayout());
        inputs.setBackground(peach);

        textBox1 = new JTextField(22);
        textBox1.setToolTipText("Enter here...");
        textBox1.setForeground(lightgrey);
        textBox1.addActionListener(this);

        sendButton = new JButton("Send");
        sendButton.setBackground(pink);
        sendButton.setForeground(grey);
        sendButton.setOpaque(true);
        sendButton.setBorderPainted(false);
        sendButton.addActionListener(this::actionSend);

        instructions = new JLabel();
        instructions.setText("<html><p><i>" +
                "> Type \"STOP\" to exit." +
                "</i></p></html>");
        instructions.setForeground(Color.gray);

        inputs.add(textBox1);
        inputs.add(sendButton, BorderLayout.EAST);
        inputs.add(instructions, BorderLayout.NORTH);

        instructions = new JLabel();
        instructions.setText("<html><p><i>" +
                "> Enter username, then hit send! <br />" +
                "> Type \"STOP\" to exit." +
                "</i></p></html>");
        instructions.setForeground(Color.gray);

        inputs.add(textBox1, BorderLayout.CENTER);
        inputs.add(sendButton, BorderLayout.EAST);
        inputs.add(instructions, BorderLayout.NORTH);

        // to hold user information
        userInfo = new JPanel();
        userInfo.setLayout(new BorderLayout());
        userInfo.setBackground(peach);

        textBox2 = new JTextField(11);
        textBox2.setText("Username: ");
        textBox2.setForeground(lightgrey);

        textBox3 = new JTextField(11);
        textBox3.setText("Port #: ");
        textBox3.setForeground(lightgrey);

        connectButton = new JButton("Connect");
        connectButton.setBackground(lightgrey);
        connectButton.setForeground(Color.white);
        connectButton.setOpaque(true);
        connectButton.setBorderPainted(false);
        connectButton.addActionListener(this::actionConnect);

        userInfo.add(textBox2, BorderLayout.WEST);
        userInfo.add(textBox3, BorderLayout.CENTER);
        userInfo.add(connectButton, BorderLayout.EAST);

        inputs.add(userInfo, BorderLayout.SOUTH);

       // output set up, colors and all
        messageSpace = new JTextArea(16, 22);
        messageSpace.setEditable(false);
        messageSpace.setForeground(lightgrey);

        chatBox = new JPanel();
        chatBox.setLayout(new BorderLayout());
        chatBox.setBackground(blush);
        chatBox.add(inputs, BorderLayout.SOUTH);
        chatBox.add(messageSpace, BorderLayout.NORTH);

        add(chatBox);
    }

    public void actionPerformed(ActionEvent evt) {
        String message = textBox1.getText();
        output.print("  " + message + "\r\n");
        textBox1.setText("");
    }

    public void actionSend(ActionEvent evt) { // getting a nullpointerexception here for some reason??
        String message = textBox1.getText();
        output.print("  " + message + "\r\n");
        textBox1.setText("");
    }

    public void actionConnect(ActionEvent evt) {
        username = textBox2.getText();
        String port_text = textBox3.getText();
        port = Integer.parseInt(textBox3.getText());

        String message = textBox1.getText();
        output.print(message + "\r\n");
        output.print("  >" + username +  " successfully connected to port " + port_text + "\r\n");

        textBox2.setText("");
        textBox3.setText("");
    }

    public static void clientSetUp() {
        // full chat window
        JFrame jf = new JFrame("Welcome to Messenger");
        jf.setSize(400, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.add(new client());
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        clientSetUp();

        try {
            Socket s = new Socket("localhost", 5190);
            Scanner input = new Scanner(s.getInputStream());
            output = new PrintStream(s.getOutputStream());
            String message = "";
            while (!message.equals("STOP")){
                message = input.nextLine();
                messageSpace.setText(messageSpace.getText() + message + "\n");
            }
        }
        catch (IOException ignored) {}
    }
}
