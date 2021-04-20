package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client_1 {
    static ParserSettingTXT parserSettingTXT = new ParserSettingTXT(new File("settings.txt"));
    static String clientName;

    public static void main(String[] args) throws IOException, InterruptedException {
        final String SERVER_HOST = "localhost"; // адрес сервера
        final int SERVER_PORT = parserSettingTXT.getPort();// порт
        Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            String msg;
            System.out.println("введите Ваш никнейм:");
            clientName = scanner.nextLine();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // бесконечный цикл
                    while (!Thread.currentThread().isInterrupted()) {
                        // если есть входящее сообщение
                        String mess = null;
                        try {
                            mess = in.readLine();
                        } catch (IOException e) {
                            System.out.println("Вы вышли из чата");
                            Thread.currentThread().interrupt();
                        }
                        if (mess != null) {
                            //   водим на экран
                            System.out.println(mess);
                        }
                    }
                }
            }).start();
            while (true) {
                System.out.println("Введите сообщение");
                msg = scanner.nextLine();
                if (msg.equals("/exit")) {
                    out.println(clientName + ": " + msg);
                    break;
                } else {
                    out.println(clientName + ": " + msg);
                    Thread.sleep(500);
                }
            }
        }
    }
}
