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

public class C03_UpdateBooking extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
    When
        Kullanici Put Request gonderiri
    Then
        Status code = 200
    And
        Body:
        {
    "firstname" : "James",
    "lastname" : "Brown",
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
    public void updateBooking() {
        //Set the Url
        spec.pathParams("pp1", "booking","pp2",id);     //C01 class da id variable olusturduk ve jsonpath ile dinamik hale getirdik.

        //Set the expected data
        HerokuBookingDatesPojo bookingDates = new HerokuBookingDatesPojo("2018-01-01","2019-01-01");
        HerokuBookingPojo expecteddata = new HerokuBookingPojo("James","Brown",111,true,bookingDates,"Breakfast");

        //Set the response
        Response response = given(spec).body(expecteddata).when().put("{pp1}/{pp2}");   //Burada header ekledik token authentication yapabilmek icin

        //Do assertions
        HerokuBookingPojo actualData = convertJsonToJava(response.asString(),HerokuBookingPojo.class);

        assertEquals(HerokuBookingPojo.statusCodeOk,response.statusCode());
        assertEquals(expecteddata.getFirstname(),actualData.getFirstname());
        assertEquals(expecteddata.getLastname(),actualData.getLastname());
        assertEquals(expecteddata.getTotalprice(),actualData.getTotalprice());
        assertEquals(expecteddata.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expecteddata.getAdditionalneeds(),actualData.getAdditionalneeds());




    }
}
