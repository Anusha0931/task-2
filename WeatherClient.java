import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherClient {
    public static void main(String[] args) {
        String city = (args.length > 0) ? args[0] : "London";

        try {
            String urlString = "https://wttr.in/" + city + "?format=j1";
            System.out.println("Requesting: " + urlString);

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.err.println("HTTP error code: " + conn.getResponseCode());
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            conn.disconnect();

            JSONObject json = new JSONObject(sb.toString());

            // written.in JSON structure
            JSONObject current = json.getJSONArray("current_condition").getJSONObject(0);
            String tempC = current.getString("temp_C");
            String humidity = current.getString("humidity");
            String desc = current.getJSONArray("weatherDesc").getJSONObject(0).getString("value");

            System.out.println("Weather Report for " + city);
            System.out.println("------------------------------");
            System.out.println("Temperature : " + tempC + " °C");
            System.out.println("Humidity    : " + humidity + "%");
            System.out.println("Condition   : " + desc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
