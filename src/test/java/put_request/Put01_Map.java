package put_request;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01_Map extends JsonPlaceHolderBaseURL {
    /*
        Given
            1) https://jsonplaceholder.typicode.com/todos/198
            2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }

        When
            Kullanıcı URL'e bir PUT request gönderir

        Then
           Status code 200 olmalı
           Response şu şekilde olmalı:
           {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
                "id": 198
           }
     */

    @Test
    public void put01() {
        //1-Set the Url
        spec.pathParams("pp1", "todos", "pp2", 198);

        //2-Set the Expected Data
        JsonPlaceHolderTestData  obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataMethod(21,"Wash the dishes",false);
        System.out.println("expectedData = " + expectedData);

        //3- Set the Response
        Response response = given(spec).body(expectedData).when().put("{pp1}/{pp2}");
        response.prettyPrint();

        //4- Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));



    }
}
