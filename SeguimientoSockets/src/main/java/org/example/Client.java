package org.example;
import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void startClientConnection(String ip, int port) {
        try {
            // Establish a connection with the server using the specified IP address and port
            socket = new Socket(ip, port);
            // Initialize input and output streams for communicating with the server
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessageToServer(String message) {
        try {
            // Send a message to the server
            out.println(message);
            // Receive the response from the server
            String response = in.readLine();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stopClientConnection() {
        try {
            // Close the streams and socket to release resources
            in.close();
            out.close();
            socket.close();
            System.out.println("Connection to server closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create an instance of the client and establish a connection with the server at localhost:12345
        Client client = new Client();
        client.startClientConnection("localhost", 12345);

        // Send a message to the server and display the response
        String response = client.sendMessageToServer("Hello, Server!");
        System.out.println("Response from server: " + response);

        // Stop the connection with the server
        client.stopClientConnection();
    }
}
