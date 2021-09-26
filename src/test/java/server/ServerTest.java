package server;

import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setRemoveAssertJRelatedElementsFromStackTrace;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ServerTest {
    Server server = mock(Server.class);
    ClientHandler clientHandler = mock(ClientHandler.class);
    ArgumentCaptor<ClientHandler> argumentCaptor = ArgumentCaptor.forClass(ClientHandler.class);
    ArrayList<ClientHandler> clients = server.getClients();


//    @After
//    public void nullData() {
//        ArrayList<ClientHandler> clients = new ArrayList<>();
//    }

//    @Test
//    void the_Quantity_Of_Clients_Must_Be_Two() {
//        ArrayList<ClientHandler> clients = server.getClients();
//        clients.add(clientHandler);
//        clients.add(clientHandler);
//        Mockito.verify(clients., Mockito.times(1)).getClients();
//        Assertions.assertEquals(2, server.getClients().size());
//    }

    @Test
    void createClientMustByNotNull() {
         assertThat(clients).isNotNull();
    }

    @Test
    void testRemoveMethod() {
        ArrayList<ClientHandler> clients = server.getClients();
        clients.add(clientHandler);
        clients.add(clientHandler);
        server.removeClient(clientHandler);
        Mockito.verify(server).removeClient(clientHandler);
    }

    @Test
    void verifyQuantityClients() {
        ArrayList<ClientHandler> clients = server.getClients();
        clients.add(clientHandler);
        clients.add(clientHandler);
        Assertions.assertEquals(2, clients.size());
    }

      @Test
    void sendMessageToAllClients() {
          server.sendMessageToAllClients("Hello");
          verify(server).sendMessageToAllClients("Привет!");
//          String consoleOut = null;
//        PrintStream originalOut = System.out;
//        try {
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
//            PrintStream capture = new PrintStream(outputStream);
//            System.setOut(capture);
//            server.sendMessageToAllClients("Привет!");
//            capture.flush();
//            consoleOut = outputStream.toString();
//            System.setOut(originalOut);
//        } catch (
//                Exception e) {
//            e.printStackTrace();
//        }
//        assertEquals("Привет!", consoleOut);
    }
//    @Test
//    void sendMessage() throws IOException {
//        Server server = mock(Server.class);
//        Socket socket = mock((Socket.class));
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ByteArrayInputStream in = new ByteArrayInputStream("Привет".getBytes());
//        when(socket.getInputStream()).thenReturn(in);
//        when(socket.getOutputStream()).thenReturn(out);
//        ArrayList<ClientHandler> clients = new ArrayList<>();
//        ClientHandler client = mock(ClientHandler.class);
//        ClientHandler client1 = mock(ClientHandler.class);
//        clients.add(client);
//        clients.add(client1);
//        Assertions.assertEquals("Привет", client1.sendMsg("Привет"));
//    }
}