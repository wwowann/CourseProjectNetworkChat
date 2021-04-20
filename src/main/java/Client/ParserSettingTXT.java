package Client;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ParserSettingTXT {
    private int serverPort;
    private final File filename;
    List<String> stringList = new ArrayList<>();
    /* переменные, которые могут быть распарсены при необходимости из файла настроек сервера settings.txt */
//    private final int maxConnection;
//    private final String exitChat;

    public ParserSettingTXT(File filename) {
        this.filename = filename;
    }

    public int getPort() {
        int port = 0;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringList.add(line);
                if (line.startsWith("server-port")) {
                    int value = line.indexOf("=");
                    port = Integer.parseInt(line.substring(value + 1));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return port;
    }
}