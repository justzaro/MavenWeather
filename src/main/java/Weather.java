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

public class Weather {
    public static double getCurrentTemperature() throws IOException {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + getLocation() + "&units=metric&appid=" + ApiKey.getWeatherKey();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response;

        double temperature = 0.0;

        try {

            response = client.execute(get);
            HttpEntity entity = response.getEntity();

            String entityString = EntityUtils.toString(entity);

            temperature = new JSONObject(entityString).getJSONObject("main").getDouble("temp");

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

        return temperature;
    }
    public static String getLocation() throws IOException {

        String ipAddress = getIP();

        String locationUrl = "https://ipinfo.io/" + ipAddress + "/json?token=" + ApiKey.getLocationKey();

        CloseableHttpClient client1 = HttpClients.createDefault();
        HttpGet get1 = new HttpGet(locationUrl);
        CloseableHttpResponse response;

        String city = null;

        try {

            response = client1.execute(get1);
            HttpEntity entity1 = response.getEntity();

            String entityToString = EntityUtils.toString(entity1);

            city = new JSONObject(entityToString).getString("city");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return city;
    }
    public static String getIP() throws IOException {

        URL obtainIP = new URL("http://checkip.amazonaws.com/");
        BufferedReader in = new BufferedReader(new InputStreamReader(obtainIP.openStream()));

        String actualIP = in.readLine();

        in.close();
        return actualIP;

    }
}