package ru.efremovdm.lesson6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Фабричный коннектор для сервера и клиента
 */
class Connector {

    private BufferedReader in = null;
    private PrintWriter out = null;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private BufferedReader console = null;
    private String type = null;

    Connector(String t) {
        this.type = t;
    }

    /**
     * Инициализация сокета
     */
    void start() {

        // клиент
        if (type.equals("client")) {
            try {
                socket = new Socket("localhost",1111);
                System.out.println("Client started. Connecting to localhost: 1111");
            }
            catch (IOException e) {
                System.out.println("Can't open connection on port 1111");
                System.exit(1);
            }
        }
        // сервер
        else {

            try {
                serverSocket = new ServerSocket(1111);
                System.out.print("Server started. Waiting for a client...");
            } catch (IOException e) {
                System.out.println("Can't open port 1111");
                System.exit(1);
            }

            try {
                socket = serverSocket.accept();
                System.out.println("Client connected");
            } catch (IOException e) {
                System.out.println("Can't accept");
                System.exit(1);
            }
        }

        try {
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            console = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Чтение сообщения
     */
    void readMessage() {
        String txt = null;
        while (!socket.isClosed()) {
            try {
                txt = in.readLine();
                if (txt != null) {
                    System.out.println(txt);
                    if (txt.equalsIgnoreCase("close") || txt.equalsIgnoreCase("exit")) {
                        close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Запись потока вывода в консоль
     */
    void sendMessage() {
        String txt = null;
        while (!socket.isClosed()) {
            try {
                txt = console.readLine();
                out.println(txt); // вывод в поток вывода

                if (txt.equalsIgnoreCase("close") || txt.equalsIgnoreCase("exit")) {
                    close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Закрытие потоков чтения-записи, сокета и сервера
     */
    private void close() {
        try {
            out.close();
            in.close();
            socket.close();
            if (serverSocket != null) {
                serverSocket.close();
            }

            System.out.println("Connection closed!");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
