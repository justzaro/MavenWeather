import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9092);

        Scanner serverInput =
                new Scanner(socket.getInputStream());

        String location = serverInput.nextLine();
        String weather = serverInput.nextLine();

        System.out.print(location + "\n" + weather);

        socket.close();
        serverInput.close();
    }
}
