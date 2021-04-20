package Server;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {// реализуем интерфейс Runnable, который позволяет работать с потоками
    static ParserSettingTXT parserSettingTXT = new ParserSettingTXT(new File("settings.txt"));
    private Server server;// экземпляр нашего сервера
    private PrintWriter outMessage;// исходящее сообщение
    private Scanner inMessage;// входящее собщение
    private static final String HOST = "localhost";
    private static final int PORT = parserSettingTXT.getPort();
    private Socket clientSocket;// клиентский сокет
    private static int clients_count = 0;// количество клиента в чате, статичное поле
    LoggerClass loggerClass = new LoggerClass();

    public ClientHandler(Socket socket, Server server) {// конструктор, который принимает клиентский сокет и сервер
        try {
            clients_count++;
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Переопределяем метод run(), который вызывается когда
    // мы вызываем new Thread(client).start();
    @Override
    public void run() {
        try {
            // сервер отправляет сообщения
            server.sendMessageToAllClients("Новый участник вошёл в чат!");
            server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
            while (true) {
                if (inMessage.hasNext()) {  // Если от клиента пришло сообщение
                    String clientMessage = inMessage.nextLine();
                    // если клиент отправляет данное сообщение, то цикл прерывается и
                    // клиент выходит из чата
                    if (clientMessage.contains("/exit")) {
                        String nameClient = parserNameClient(clientMessage);
                        String mes = nameClient + " вышел из чата";
                        System.out.println(mes);
                        server.sendMessageToAllClients(mes);
                        loggerClass.log(mes);
                        break;
                    }
                    // выводим в консоль сообщение
                    System.out.println(clientMessage);
                    loggerClass.log(clientMessage);
                    // отправляем данное сообщение всем клиентам
                    server.sendMessageToAllClients(clientMessage);
                }
                // останавливаем выполнение потока на 100 мс
                Thread.sleep(100);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            this.close();
        }
    }

    // отправляем сообщение
    public void sendMsg(String msg) {
        try {
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // клиент выходит из чата
    public void close() {
        // удаляем клиента из списка
        clients_count--;
        server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
        server.removeClient(this);
    }

    public String parserNameClient(String str) {
        int value = str.indexOf(": ");
        return str.substring(0, value);
    }
}
