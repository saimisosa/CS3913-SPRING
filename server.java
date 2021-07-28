import java.io.*;
import java.net.*;
import java.util.*;

public class server {
    static int port = 5190;

    public static void main(String[] args) {
        ServerSocket socket = null;

        try {
            socket = new ServerSocket(port);

            while(true) {
                Socket client = socket.accept();
                new Connection(client).start();
            }
        }
        catch(IOException ex) {
            System.out.println("Socket unreachable..");
        }
    }
}

class Connection extends Thread{
    static ArrayList<Connection> users = new ArrayList<>();
    Socket client;
    String username;

    Connection(Socket data) { client = data; }

    public void run() {
        try {
            Scanner user_input = new Scanner(client.getInputStream());
            String message = "";
            username = user_input.nextLine();
            users.add(this);

            while (!message.equals("STOP")) {
                message = user_input.nextLine();
                for (Connection conn : users) {
                    conn.send(message, username);
                }
            }
            client.close();
        }
        catch(IOException ignored) {} // since no exception is thrown
    }

    public void send(String message, String username){
        try {
            PrintStream output = new PrintStream(client.getOutputStream());
            output.println("  " + username + ": " + message);
        }
        catch(IOException ignored) {}
    }
}
