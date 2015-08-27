package scienceindia.com.news;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
/**
 * Created by shashankreddy509 on 8/27/15.
 * This calls is used to parser the Json data, we pass the server url and this fetches the data from server.
 */

@SuppressWarnings("deprecation")
class JSONParser {
    private static InputStream is = null;
    static String result = "";

    private JSONArray jsonArray = null;

    // constructor
    public JSONParser() {

    }

    public JSONArray getJSONFromUrl(String url) {
        // Making HTTP request
        try {
            // defaultHttpClient

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String json;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);//iso-8859-1//UTF-8
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            json = sb.toString();
            result = "SUCCESS";
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
            json = "{\"MESSAGE\": \"Network not available\"}";
            result = "Network Issue";
        }

        // try parse the string to a JSON object
        try {
            jsonArray = new JSONArray(json);

        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
            result = "Error";
        }
        // return JSON String
        return jsonArray;
    }
}
