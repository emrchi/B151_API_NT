package get_request;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static testDataDeposu.JsonPlaceHolderTestData.statusOk;

public class Get08_MapExpActData extends JsonPlaceHolderBaseURL {
       /* 
    Given 
        https://jsonplaceholder.typicode.com/todos/2 
    When 
        I send GET Request to the URL 
    Then 
        Status code is 200 
        And "completed" is false 
        And "userId" is 1 
        And "title" is "quis ut nam facilis et officia qui" 
        And header "Via" is "1.1 vegur" 
        And header "Server" is "cloudflare" 
        { 
            "userId": 1, 
            "id": 2, 
            "title": "quis ut nam facilis et officia qui", 
            "completed": false 
        } 
    */

    @Test
    public void get08() {
        //1-Set the Url
        spec.pathParams("pp1", "todos", "pp2", 2);

        //2-Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataMethod(1,"quis ut nam facilis et officia qui",false);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server","cloudflare");
        System.out.println("expectedData = " + expectedData);

        //3-Set the response
        Response response = given(spec).when().get("{pp1}/{pp2}");     //serialization (java datasini json datasina donusturme islemi)
        response.prettyPrint();

        //4- Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);     //response'u HashMap.class'a donusturmek istedik
        System.out.println("actualData = " + actualData);

        assertEquals(statusOk,response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("Via"),response.header("Via"));
        assertEquals(expectedData.get("Server"),response.header("Server"));

    }
}
