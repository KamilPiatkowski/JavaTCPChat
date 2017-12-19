package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        String name;
        String ip;
        int port;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Client name: ");
        name = scanner.nextLine();

        System.out.println("IP: ");
        ip = scanner.nextLine();

        System.out.println("Port: ");
        port = Integer.parseInt(scanner.nextLine());

        Socket socket = new Socket(ip, port);

        scanner.close();
        socket.close();

    }

}
