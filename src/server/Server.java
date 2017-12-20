package server;

import client.Channel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {

        int port;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Port: ");
        port = Integer.parseInt(scanner.nextLine());

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server has started.");

        Socket socket = serverSocket.accept();
        System.out.println("Client has connected.");

        // Receive msg
        Channel channel = new Channel(socket);
        channel.start();

        // Send msg
        while(true) {
            String msg = scanner.nextLine();

            if(msg.isEmpty()) {
                break;
            }

            channel.send("Server: " + msg);
        }

        channel.stop();
        scanner.close();
        serverSocket.close();
        System.out.println("Server has closed.");
    }

}
