package post_request;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Post01_Payload_Map extends JsonPlaceHolderBaseURL {
    /*
    Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
        When
            Kullanıcı URL'e bir POST request gönderir
        Then
            Status code 201 olmalı
        And
            Response şu şekilde olmalı:
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
     */

    @Test
    public void post01() {
        //1- Set URL
        spec.pathParam("pp1", "todos");

        //2- Set the expected data
        String payload = "{ \n" +
                "             \"userId\": 55, \n" +
                "             \"title\": \"Tidy your room\", \n" +
                "             \"completed\": false \n" +
                "           }";

        //3- Send the request and get tehe response
        Response response = given(spec).body(payload).when().post("{pp1}");
        //response.prettyPrint();

        //4- Do assertion
        JsonPath jsonPath = response.jsonPath();
        assertEquals(201, response.statusCode());
        assertEquals(55,jsonPath.getInt("userId"));
        assertEquals("Tidy your room",jsonPath.getString("title"));
        assertFalse(jsonPath.getBoolean("completed"));
    }


    //Map kullanalim
    @Test
    public void post01Map() {
        //1- Set URL
        spec.pathParam("pp1", "todos");

        //2- Set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);

        //3- Send the request and get tehe response
        Response response = given(spec).body(expectedData).when().post("{pp1}");     // Serialization = Java Map datasının Json datasına çevrilmesidir
        //response.prettyPrint();
        //Biz map gondermeye calisiyoruz karsi taraf json data bekliyor. Bu nedenle IllegalStateException aliriz.
        //Serialization icin (veri donusumu) Jackson (Databind) eklentisini pom a yukleyerek handle edebiliriz

        //4- Do assertion
        assertEquals(201, response.statusCode());

        Map<String,Object> actualData = response.as(HashMap.class);      // De-Serialization = Json objesinin Java Map objesine çevrilmesidir.
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

    }
}
