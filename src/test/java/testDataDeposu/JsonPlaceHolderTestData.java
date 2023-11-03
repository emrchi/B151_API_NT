package testDataDeposu;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public static int statusOk =200;
    public static int statusCreated = 201;



    public JSONObject expectedDataOlustur() {
        JSONObject body = new JSONObject();
        body.put("userId", 3);
        body.put("id", 22);
        body.put("title", "dolor sint quo a velit explicabo quia nam");
        body.put("body", "eos qui et ipsum ipsam suscipit aut\n" +
                "sed omnis non odio\n" +
                "expedita earum mollitia molestiae aut atque rem suscipit\n" +
                "nam impedit esse");

        return body;
    }

    public Map<String,Object> expectedDataMethod(Integer userId, String title, Boolean completed){
        Map<String, Object> expectedData = new HashMap<>();
        if(userId!=null){
            expectedData.put("userId",userId);
        }
        if(title!=null){
            expectedData.put("title", title);
        }
        if(completed!=null){
            expectedData.put("completed",completed);
        }

        return expectedData;
    }

    //JsonPlaceholder json datasini String'e  ceviren method

    public  static  String convertJsonToString(Integer userId, String title, Boolean completed){
        return "{\n" +
                "                \"userId\": "+userId+",\n" +
                "                \"title\": \""+title+"\",\n" +
                "                \"completed\": "+completed+"\n" +
                "            }";
    }



}
