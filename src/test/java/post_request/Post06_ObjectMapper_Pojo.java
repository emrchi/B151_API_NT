package post_request;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.ObjectMapperUtils;

import java.io.Reader;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06_ObjectMapper_Pojo extends JsonPlaceHolderBaseURL {
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
    public void post06() {
        //Set the URL
        spec.pathParam("pp1","todos");

        //Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        //Set the response
        Response response   = given(spec).body(expectedData).when().post("{pp1}");
        response.prettyPrint();

        //Do Assertion
        //Bir onceki class da objectMapper.readValue() methodu kullanmistik burada ise
        // utils classta olusturdugumuz convertJsonToJava() methodunu kullandik.
        // readvalue() methodu nedeni ilen gelen exception 'i her defasinda try catch yapmaktan kurtulduk.

        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);

        assertEquals(expectedData.statusCreated, response.statusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());


    }
}
