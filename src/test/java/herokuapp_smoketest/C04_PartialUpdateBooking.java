package herokuapp_smoketest;

import baseURLDeposu.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerokuBookingDatesPojo;
import pojos.HerokuBookingPojo;
import testDataDeposu.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static herokuapp_smoketest.C01_CreateBooking.id;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C04_PartialUpdateBooking extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
        "firstname" : "Ali",
        "lastname" : "Can"
        }
    When
        Kullanici Patch request gonderir
    Then
        Status Code = 200
    And
        Body:
            {
                "firstname" : "Ali",
                "lastname" : "Can",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
            }
     */

    @Test
    public void partialUpdateBooking() {
        //Set the Url
        spec.pathParams("pp1","booking","pp2",id);

        //Set the Expected Data
        HerokuAppTestData obj = new HerokuAppTestData();
        Map<String, Object> expectedData = obj.expectedDataMapper("Ali","Can",null,null,null,null);

        //Set the Response
        Response response = given(spec).body(expectedData).when().patch("{pp1}/{pp2}");

        //Do assertions
        Map<String,String> actualData = convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(HerokuBookingPojo.statusCodeOk, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
    }
}
