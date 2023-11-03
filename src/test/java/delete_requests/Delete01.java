package delete_requests;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.codehaus.groovy.ast.stmt.BreakStatement;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import testDataDeposu.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static testDataDeposu.JsonPlaceHolderTestData.statusCreated;
import static testDataDeposu.JsonPlaceHolderTestData.statusOk;

public class Delete01 extends JsonPlaceHolderBaseURL {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
            I send DELETE Request to the Url
        Then
            Status code is 200
        And Response body is { }
    */

    @Test
    public void delete01() {
        //Set the Url
        spec.pathParams("pp1","todos","pp2",198);

        //Set the expected data
        Map<String,String> expectedData = new HashMap<>();

        //Set the response
        Response response = given(spec).when().delete("{pp1}/{pp2}");
        response.prettyPrint();

        //Do assertion
        Map<String,String> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),HashMap.class) ;
        assertEquals(statusOk, response.statusCode());
        //1.Yol
        assertEquals(expectedData, actualData);
        //2.Yol
        assertEquals(expectedData.size(),actualData.size());
        //3. Yol
        assertTrue(actualData.isEmpty());
    }
}
