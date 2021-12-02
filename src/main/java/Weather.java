import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class Weather {

    static String location;
    static double currentTemperature;

    public static void setTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    updateWeather();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 3600000);
    }

    public static void updateWeather() throws IOException {
        getLocation();
        getCurrentTemperature();
    }

    public static void getCurrentTemperature() {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + location +
                "&units=metric&appid=" + ApiKey.getWeatherKey();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response;

        double temperature = 0;

        try {

            response = client.execute(get);
            HttpEntity entity = response.getEntity();

            String entityString = EntityUtils.toString(entity);

            temperature = new JSONObject(entityString).getJSONObject("main").getDouble("temp");

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

        currentTemperature = temperature;

    }
    public static void getLocation() throws IOException {

        String ipAddress = getIP();

        String locationUrl = "https://ipinfo.io/" + ipAddress + "/json?token=" + ApiKey.getLocationKey();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(locationUrl);
        CloseableHttpResponse response;

        String city = null;

        try {

            response = client.execute(get);
            HttpEntity entity1 = response.getEntity();

            String entityToString = EntityUtils.toString(entity1);

            city = new JSONObject(entityToString).getString("city");

        } catch (IOException e) {
            e.printStackTrace();
        }

        location = city;
    }
    public static String getIP() throws IOException {

        URL obtainIP = new URL("http://checkip.amazonaws.com/");
        BufferedReader in = new BufferedReader(new InputStreamReader(obtainIP.openStream()));

        String actualIP = in.readLine();

        in.close();
        return actualIP;

    }
}