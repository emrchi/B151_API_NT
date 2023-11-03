package herokuapp_smoketest;

import baseURLDeposu.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerokuBookingDatesPojo;
import pojos.HerokuBookingPojo;

import static herokuapp_smoketest.C01_CreateBooking.id;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C05_DeleteBooking extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Kullanici Delete Request gonderir
    Then
        Status Code = 201
    And
        Body:
            Created
     */

    @Test
    public void DeleteBooking() {
        //Set the Url
        spec.pathParams("pp1","booking","pp2",id);
        //Set the expected data
        String expectedData = "Created";
        //Set the response
        Response response = given(spec).when().delete("{pp1}/{pp2}");
        //Do Assertions
        String actualdata = response.asString();
        assertEquals(HerokuBookingPojo.statusCreated,response.statusCode());
        assertEquals(expectedData, actualdata);
    }
}
