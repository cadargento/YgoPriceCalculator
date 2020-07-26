
import com.google.gson.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class YugiohPricesInterface
{
    private static String apiHomeUrlString = "https://db.ygoprodeck.com/";
    private static String apiUrlFileString = "/api/v6/cardinfo.php";

    public static Card CardData(String cardId) {

        Map<String, String> cardInfoMap = new HashMap<>();
        JsonArray jArr = null;
        JsonObject jObj = null;
        JsonElement jsonTree = null;

        jsonTree = GetRequest("?id=" + cardId);

        assert jsonTree != null;
        if (jsonTree.isJsonArray()) {
            jArr = jsonTree.getAsJsonArray();
            jObj = jArr.get(0).getAsJsonObject();
        } else{
            jObj = jsonTree.getAsJsonObject();
        }

        try {
            JsonObject data = jObj;
            for (String field: Card.cardFields) {
                if ((data.has(field))) {
                    String cardField = data.get(field).getAsString();
                    cardInfoMap.put(field, cardField);
                }
                else {
                    cardInfoMap.put(field, null);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cardInfoMap.size() > 0) {
            return new Card(cardInfoMap);
        }
        return null;

    }

    public static JsonElement GetRequest(String dataType) {
        String responseString = "";
        HttpURLConnection conn = null;

        try {

            URL apiHomeUrl = new URL(apiHomeUrlString);
            URL cardUrl = new URL(apiHomeUrl, apiUrlFileString + dataType);

            conn = (HttpURLConnection) cardUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5 * 1000);
            conn.connect();
            int responseCode = conn.getResponseCode();

            if (responseCode != 200)
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            else {
                Scanner sc = new Scanner(conn.getInputStream());
                while (sc.hasNext()) {
                    responseString += sc.nextLine();
                }
                System.out.println("\nJSON Response in String format");
                System.out.println(responseString);
                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!responseString.equals("")) {
                conn.disconnect();
            }
        }
        if (!responseString.equals("")) {
            return JsonParser.parseString(responseString);
            // return ConvertStringToJson(responseString.toString());
        }
        return null;

    }

    public static JsonElement ConvertStringToJson(String responseString) {
        JsonElement responseJson = JsonParser.parseString(responseString);
        return responseJson;
    }

    public static void main(String [] args)
    {
        Card c3 = CardData("83994646");
        //Card c1 = CardData("Blue-Eyes%20White%20Dragon");
        //Card c2 = CardData("Blue-Eyes White Dragon");

        System.out.println(apiHomeUrlString);

    }

}

