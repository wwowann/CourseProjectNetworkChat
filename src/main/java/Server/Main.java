package Server;

public class Main {
    public static void main(String[] args){
        LoggerClass loggerClass = LoggerClass.getInstance();
        loggerClass.createFileLog();// создается файл логирования
        Server server = new Server();
    }
}
