
import com.google.gson.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class YugiohPricesInterface
{
    private static String apiHomeUrlString = "https://db.ygoprodeck.com/";
    private static String apiUrlFileString = "/api/v7/cardinfo.php";

    public static Card CardData(String cardId) {

        Map<String, String> cardInfoMap = new HashMap<>();
        Map<String, JsonArray> cardJArrMap = new HashMap<>();
        JsonArray jArr = null;
        JsonObject jObj = null;
        JsonElement jsonTree = null;

        jsonTree = GetRequest("?id=" + cardId);

        assert jsonTree != null;
        jArr = (JsonArray) jsonTree.getAsJsonObject().get("data");

        if (jArr.size() <= 1) {
            jObj = jArr.get(0).getAsJsonObject();
        } else{
            // BOZO -- need to implement for when multiple cards are part of the response.
            jObj = jArr.get(0).getAsJsonObject();
        }

        try {
            JsonObject data = jObj;
            for (String field: Card.cardFields) {
                if ((data.has(field))) {
                    if (data.get(field).isJsonPrimitive()) {
                        String cardField = data.get(field).getAsString();
                        cardInfoMap.put(field, cardField);
                    }
                    else if (data.get(field).isJsonArray()) {
                        JsonArray dataArr = data.get(field).getAsJsonArray();
                        cardJArrMap.put(field, dataArr);
                    }
                }
                else {
                    cardInfoMap.put(field, null);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cardInfoMap.size() > 0 || cardJArrMap.size() > 0) {
            if (cardJArrMap.size() > 0) {
                return new Card(cardInfoMap, cardJArrMap);
            }
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

        System.out.println(apiHomeUrlString);

    }

}

