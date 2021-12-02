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

        String location = null;
        String weather = null;

        try {
            location = "Location: " + Weather.getLocation();
            weather = "Temperature: " + Math.round(Weather.getCurrentTemperature()) + " Celsius";
        } catch (IOException e) {
            e.printStackTrace();
        }

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
