package client;

import server.ParserSettingTXT;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    public static void main(String[] args) throws IOException {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 3; i++) {
//            executorService.execute(Client::new);
//        }
            for (int i = 0; i < 3; i++) {
                startClient();
            }}

    @Override
    public void run() {
        try {
            startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startClient() throws IOException {
        ParserSettingTXT parserSettingTXT = new ParserSettingTXT(new File("settings.txt"));

        Socket socket = new Socket((String) parserSettingTXT.getData("host"),
                (int) parserSettingTXT.getData("port"));
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
        ) {
            threatStart(in);
            messageOut(out);
        }
    }

    private static void messageOut(PrintWriter out) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("введите Ваш никнейм:");
        String clientName = getValueString();
        while (true) {
            System.out.println("Введите сообщение");
            String msg = getValueString();
            if (msg.equals("/exit")) {
                out.println(clientName + ": " + msg);
                break;
            } else {
                out.println(clientName + ": " + msg);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static String getValueString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void threatStart(BufferedReader in) {
        new Thread(() -> {
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
                    //   выводим на экран
                    System.out.println(mess);
                }
            }
        }).start();
    }


}
