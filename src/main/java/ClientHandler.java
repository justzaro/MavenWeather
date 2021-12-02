import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket clientSocket;
    private final PrintStream clientOutput;

    public ClientHandler(Socket socket) throws IOException {
        clientSocket = socket;
        clientOutput = new PrintStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {

        String location;
        String weather;

        location = "Location: " + Weather.location;
        weather = "Temperature: " + Math.round(Weather.currentTemperature) + " Celsius";

        clientOutput.println(location);
        clientOutput.println(weather);

        clientOutput.close();

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
