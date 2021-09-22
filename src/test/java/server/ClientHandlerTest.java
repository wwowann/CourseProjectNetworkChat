package server;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ClientHandlerTest {

    static ClientHandler clientHandler = Mockito.mock(ClientHandler.class);
//    @Test
//    void printMessage() {
//   ClientHandler clientHandler = mock(ClientHandler.class);
//    String consoleOut;
//    PrintStream originalOut = System.out;
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
//    PrintStream capture = new PrintStream(outputStream);
//        System.setOut(capture);
//        clientHandler.sendMsg("Привет!");
//        capture.flush();
//    consoleOut =outputStream.toString();
//        System.setOut(originalOut);
//
//    assertEquals("Привет!",consoleOut);
//}
    @Test
    void parserNameClient() {
        Mockito.when(clientHandler.parserNameClient("Николай: привет всем!")).
                thenReturn("Николай");
        String actual = clientHandler.parserNameClient("Николай: привет всем!");
        assertEquals("Николай", actual);

    }

    @Test
    void parserDoesNotMatchNameClient() {
        Mockito.when(clientHandler.parserNameClient("Николай: привет всем!")).thenReturn("Николай");
        String actual = clientHandler.parserNameClient("Николай: привет всем!");
        assertNotEquals("Василий", actual);
    }
//    @Test
//    void getOutMessage(){
//        try(PrintWriter pw = new PrintWriter(System.out))
//        {
//            pw.println("Hello world!");
//        }
//        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
//        Socket clientSocket = Mockito.mock(Socket.class);



    }
