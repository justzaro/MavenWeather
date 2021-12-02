import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        Weather.setTimer();
        ServerSocket socket = new ServerSocket(9092);

        while (true) {
            Socket client = socket.accept();
            new ClientHandler(client).start();
        }
    }
}
