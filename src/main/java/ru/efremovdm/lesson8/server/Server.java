package ru.efremovdm.lesson8.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

class Server {

    private Vector<ClientHandler> clients;
    private AuthService authService = null;

    AuthService getAuthService() {
        return authService;
    }

    Server() {
        try (ServerSocket serverSocket = new ServerSocket(1234)){
            clients = new Vector<>();
            authService = new AuthService();
            authService.connect();
            System.out.println("Server started... Waiting clients...");
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Client connected " + socket.getInetAddress() + " " + socket.getPort() + " " + socket.getLocalPort());
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Не удалось запустить сервис авторизации");
        } finally {
            if (authService != null) {
                authService.disconnect();
            }
        }
    }

    void subscribe(ClientHandler clientHandler){
        clients.add(clientHandler);
        broadcastClientsList();
    }

    void unsubscribe(ClientHandler clientHandler){
        clients.remove(clientHandler);
        broadcastClientsList();
    }

    boolean isNickBusy(String nick){
        for (ClientHandler o: clients){
            if (o.getNick().equals(nick)){
                return true;
            }
        }
        return false;
    }

    void sendPrivateMsg(ClientHandler from, String nickTo, String msg){
        for (ClientHandler o: clients){
            if (o.getNick().equals(nickTo)){
                o.sendMsg("от " + from.getNick() + ": " + msg);
                from.sendMsg("клиенту " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + "не найден");
    }

    private void broadcastClientsList(){
        StringBuilder sb = new StringBuilder("/clientslist ");
        for(ClientHandler o: clients){
            sb.append(o.getNick() + " ");
        }
        String out = sb.toString();
        for (ClientHandler o: clients){
            o.sendMsg(out);
        }
    }

    void broadcastMsg(String msg){
        for (ClientHandler o: clients){
            o.sendMsg(msg);
        }
    }
}
