package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {// реализуем интерфейс Runnable, который позволяет работать с потоками
    private final Server server;// экземпляр нашего сервера
    private PrintWriter outMessage;// исходящее сообщение
    private Scanner inMessage;// входящее собщение
    private final Socket clientSocket;// клиентский сокет
    private int clients_count = 0;// количество клиента в чате, статичное поле
    private final LoggerClass log = new LoggerClass();

    public ClientHandler(Socket socket, Server server) {// конструктор, который принимает клиентский сокет и сервер
        clients_count++;
        this.server = server;
        this.clientSocket = socket;
        this.outMessage = getOutMessage();
        this.inMessage = getInMessage();
    }

    // Переопределяем метод run(), который вызывается когда
    // мы вызываем new Thread(client).start();
    @Override
    public void run() {
        try {
            // сервер отправляет сообщения
            server.sendMessageToAllClients("Новый участник вошёл в чат!" + " Клиентов в чате = " + clients_count);
            while (true) {
                if (inMessage.hasNext()) {  // Если от клиента пришло сообщение
                    String clientMessage = inMessage.nextLine();
                    if (clientMessage.contains("/exit")) { // если клиент отправляет данное сообщение, то цикл прерывается и клиент выходит из чата
                        String nameClient = parserNameClient(clientMessage);
                        String mes = nameClient + " вышел из чата";
                        System.out.println(mes);
                        server.sendMessageToAllClients(mes);
                        log.log(mes);
                        break;
                    }
                    System.out.println(clientMessage); // выводим в консоль сообщение
                    log.log(clientMessage);
                    server.sendMessageToAllClients(clientMessage);   // отправляем данное сообщение всем клиентам
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

    public PrintWriter getOutMessage() {
        try {
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outMessage;
    }

    public Scanner getInMessage() {
        try {
            inMessage = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inMessage;
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
        clients_count--;   // удаляем клиента из списка
        server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
        server.removeClient(this);
    }

    public String parserNameClient(String str) {
        int value = str.indexOf(": ");
        return str.substring(0, value);
    }
}