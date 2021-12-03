import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.net.URI;

public class Client {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Socket socket = new Socket("localhost", 9092);

        Scanner serverInput =
                new Scanner(socket.getInputStream());

        String location = serverInput.nextLine();
        String weather = serverInput.nextLine();
        String googleMapsURL = serverInput.nextLine();

        System.out.println(location + "\n" + weather);

        Desktop.getDesktop().browse(new URI(googleMapsURL));

        socket.close();
        serverInput.close();
    }
}
