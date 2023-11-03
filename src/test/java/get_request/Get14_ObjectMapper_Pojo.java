package get_request;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import testDataDeposu.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14_ObjectMapper_Pojo extends JsonPlaceHolderBaseURL {
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
    public void get14() {
        //Set the Url
        spec.pathParams("pp1", "todos", "pp2", 198);

        //Set the expected data
        String body = JsonPlaceHolderTestData.convertJsonToString(10,"quis eius est sint explicabo",true);
        JsonPlaceHolderPojo expectedData = ObjectMapperUtils.convertJsonToJava(body,JsonPlaceHolderPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Set the response
        Response response = given(spec).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //Do assertions
        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);
        
        assertEquals(JsonPlaceHolderTestData.statusOk,response.statusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());
        


    }
}
