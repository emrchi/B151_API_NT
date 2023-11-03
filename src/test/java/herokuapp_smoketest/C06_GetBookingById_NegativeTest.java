package herokuapp_smoketest;

import baseURLDeposu.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerokuBookingDatesPojo;
import pojos.HerokuBookingPojo;

import static herokuapp_smoketest.C01_CreateBooking.id;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C06_GetBookingById_NegativeTest extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        kullanici Get request gonderir.
    Then
        Status code = 404
    And
        Body:
            Not Found

     */

    @Test
    public void get01() {
        //Set the Url
        spec.pathParams("pp1","booking","pp2",id);       //C01 class da id variable olusturduk ve jsonpath ile dinamik hale getirdik.

        //Set the expected data
        String expectedData = "Not Found";

        //Set the response
        Response response = given(spec).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //Do Assertion
        String actualData = response.asString();

        assertEquals(404,response.statusCode());
        assertEquals(expectedData,actualData);

    }
}
