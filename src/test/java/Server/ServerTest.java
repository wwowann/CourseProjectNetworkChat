package Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrdererContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;

class ServerTest {

    @Test
    void quantityClients() throws IOException {
        ArrayList<ClientHandler> clients = new ArrayList<>();
        Server server = mock(Server.class);
//        ServerSocket serverSocket = mock(ServerSocket.class);
//        Socket clientSocket = serverSocket.accept();
//        clientSocket = mock(Socket.class);
//        ClientHandler clientHandler = new ClientHandler(clientSocket, server);
        ClientHandler client = mock(ClientHandler.class);
        ClientHandler client1 = mock(ClientHandler.class);
//        clients.add(clientHandler);
        clients.add(client);
        clients.add(client1);
       server.removeClient(client);
 Assertions.assertEquals(1, clients.size());
         }
}