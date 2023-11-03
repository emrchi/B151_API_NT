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

public class C02_GetBookingById extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        kullanici Get request gonderir.
    Then
        Status code = 200
    And
        Body:
            {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2013-02-23",
                "checkout": "2014-10-23"
            },
            "additionalneeds": "Breakfast"
        }

     */

    @Test
    public void get01() {
        //Set the Url
        spec.pathParams("pp1","booking","pp2",id);       //C01 class da id variable olusturduk ve jsonpath ile dinamik hale getirdik.

        //Set the expected data
        HerokuBookingDatesPojo herokuBookingDates = new HerokuBookingDatesPojo("2013-02-23","2014-10-23");
        HerokuBookingPojo expectedData = new HerokuBookingPojo("Sally","Brown",111,true,herokuBookingDates,"Breakfast");

        //Set the response
        Response response = given(spec).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //Do Assertion
        HerokuBookingPojo actalData = convertJsonToJava(response.asString(),HerokuBookingPojo.class);

        assertEquals(HerokuBookingPojo.statusCodeOk,response.statusCode());
        assertEquals(expectedData.getFirstname(),actalData.getFirstname());
        assertEquals(expectedData.getLastname(),actalData.getLastname());
        assertEquals(expectedData.getTotalprice(),actalData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actalData.getDepositpaid());
        assertEquals(herokuBookingDates.getCheckin(),actalData.getBookingdates().getCheckin());
        assertEquals(herokuBookingDates.getCheckout(),actalData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actalData.getAdditionalneeds());
    }
}
