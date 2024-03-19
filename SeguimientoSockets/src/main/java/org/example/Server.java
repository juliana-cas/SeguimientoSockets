package org.example;
import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startServer(int port) {
        try {
            // Create a ServerSocket listening on the specified port
            serverSocket = new ServerSocket(port);
            System.out.println("Server is up and running on port " + port);

            // Accept incoming client connection
            clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            // Initialize input and output streams for communicating with the client
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Handle the client request
            String request = in.readLine();
            System.out.println("Received request from client: " + request);

            // Process the request (in this case, simply echo back the request)
            out.println("Server acknowledges: " + request);

            // Close the streams and sockets to release resources
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create an instance of the server and start listening on port 12345
        Server server = new Server();
        server.startServer(12345);
    }
}
