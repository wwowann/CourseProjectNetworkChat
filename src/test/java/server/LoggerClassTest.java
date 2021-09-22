package server;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

class LoggerClassTest {
    @Mock
    Date date;
    SimpleDateFormat ft;
    FileOutputStream fos;

    @Test
    void createFileLog() {
    }

//    @Test
//    void log() {
//int counter =0;
//String msg = "Привет!";
//        Scanner scan = new Scanner(System.in);
//        String msgFinal = "[ " + ++counter + " " + ft.format(date) + "] " + msg + "\n";
//        byte[] bytes = msgFinal.getBytes();//перевод строки лога в массив байтов
//        fos.write(bytes, 0, bytes.length);
//    }//запись байтов в файл
//        catch(
//    IOException ex)
//
//    {
//        System.out.println(ex.getMessage());
//    }
//}
}