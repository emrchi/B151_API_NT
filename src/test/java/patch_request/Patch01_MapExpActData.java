package patch_request;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01_MapExpActData extends JsonPlaceHolderBaseURL {
    /*
    Given
        1) https://jsonplaceholder.typicode.com/todos/198
        2) {
              "title": "Wash the dishes"
           }
    When
      I send PATCH Request to the Url
    Then
          Status code is 200
          And response body is like
              {
                "userId": 10,
                "title": "Wash the dishes",
                "completed": true,
                "id": 198
              }
     */

    @Test
    public void patch01() {
        //Set the Url
        spec.pathParams("pp1","todos","pp2",198);

        //Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> payload = obj.expectedDataMethod(null,"Wash the dishes",null);
        // payload = veritabanina gonderdigimiz data(body).
        //patch islemi parcali degisiklik oldugu icin iki map olusturuyoruz biri payload biri expected data
        System.out.println("payload = " + payload);
        Map<String, Object> expectedData = obj.expectedDataMethod(10,"Wash the dishes",true);
        System.out.println("expectedData = " + expectedData);

        //Set the response
        Response response = given(spec).body(payload).when().patch("{pp1}/{pp2}"); // request yaparken  body()methodu ile veritabanina  expecteddata degil payload'u gonderdik
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(obj.statusOk,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

    }
}
