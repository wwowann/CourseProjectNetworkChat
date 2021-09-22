package server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static ParserSettingTXT parserSettingTXT = new ParserSettingTXT(new File("settings.txt"));
    static final int PORT = parserSettingTXT.getPort();// порт, который будет прослушивать наш сервер
    private final ArrayList<ClientHandler> clients = new ArrayList<>();// список клиентов, которые будут подключаться к серверу


    // сокет клиента, это некий поток, который будет подключаться к серверу
    // по адресу и порту
    public Server() {
        new LoggerClass().getInstance().createFileLog();// создается логер и файл логирования
        start();
    }

    private void start() {
        Socket clientSocket = null;
        ServerSocket serverSocket = null; // серверный сокет
        try {
            serverSocket = new ServerSocket(PORT);  // создаём серверный сокет на определенном порту
            System.out.println("Сервер запущен!");
            while (true) {  // запускаем бесконечный цикл
                clientSocket = serverSocket.accept();  // таким образом ждём подключений от сервера
                // создаём обработчик клиента, который подключился к серверу
                // this - это наш сервер
                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);
                new Thread(client).start(); // каждое подключение клиента обрабатываем в новом потоке
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // закрываем подключение
                clientSocket.close();
                System.out.println("Сервер остановлен");
                serverSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // отправляем сообщение всем клиентам
    public synchronized void sendMessageToAllClients(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    // удаляем клиента из коллекции при выходе из чата
    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public ArrayList<ClientHandler> getClients() {
        return clients;
    }
}