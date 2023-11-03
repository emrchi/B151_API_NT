package get_request;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13_ObjectMapper_Map extends JsonPlaceHolderBaseURL {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
            I send GET Request to the URL
        Then
            Status code is 200
        And response body is like
            {
                "userId": 10,
                "id": 198,
                "title": "quis eius est sint explicabo",
                "completed": true
            }
    */

    @Test
    public void get13() {
        //Set the Url
        spec.pathParams("pp1","todos","pp2",198);

        //Set the expected data
        String body = "{\n" +
                "                \"userId\": 10,\n" +
                "                \"id\": 198,\n" +
                "                \"title\": \"quis eius est sint explicabo\",\n" +
                "                \"completed\": true\n" +
                "            }";

        Map<String,Object> expectedData = ObjectMapperUtils.convertJsonToJava(body, HashMap.class); //body'e tanimladigimiz string'i map'a cevirdik
        System.out.println("expectedData = " + expectedData);

        //Set the response
        Response response = given(spec).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //Do assertions
        Map<String,Object> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(JsonPlaceHolderTestData.statusOk, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }
}
