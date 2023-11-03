package post_request;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05_ObjectMapper_Map extends JsonPlaceHolderBaseURL {
    /*
        Given
            1) https://jsonplaceholder.typicode.com/todos
            2) {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
     */

    @Test
    public void post05() throws JsonProcessingException {
        //1- Set the Url
        spec.pathParam("pp1","todos");

        //2-Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataMethod(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        //3-Set the response
        Response response = given(spec).body(expectedData).when().post("{pp1}");
        response.prettyPrint();

        //Do Assertion
        ObjectMapper objectMapper = new ObjectMapper();
        //readValue() methodu birinci parametrede aldigi String datayi, ikinci parametrede belirttigimiz data tipine cevirir.
        Map<String,Object> actualData = objectMapper.readValue(response.asString(), HashMap.class);
        //Map<String,Object> actualData2 = response.as(HashMap.class); bu da ayni response i getirir
        System.out.println("actualData = " + actualData);

        assertEquals(obj.statusCreated, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }
}
