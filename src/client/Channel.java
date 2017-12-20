package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Channel implements Runnable {

    private Socket socket;
    private Scanner reader;
    private PrintWriter writer;
    private boolean running;

    public Channel(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stop() throws IOException {
        running = false;
        reader.close();
        socket.close();
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            reader = new Scanner(inputStream);

            OutputStream outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream);

            running = true;
            while(running) {
                try {
                    String msg = reader.nextLine();
                    System.out.println(msg);
                } catch (NoSuchElementException e) {
                    System.out.println("Server closed.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        writer.println(msg);
        writer.flush();
    }

}
