package herokuapp_smoketest;

import baseURLDeposu.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerokuBookingDatesPojo;
import pojos.HerokuBookingPojo;
import pojos.HerokuResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static pojos.HerokuBookingPojo.statusCodeOk;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C01_CreateBooking extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
            {
        "firstname" : "Jim",
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
        Kullanici Post request gonderir
    Then
        Status code = 200
    And
        {
    "bookingid": 1,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}


     */
    public static int id;
    @Test
    public void post01() {
        //Set the Url
        spec.pathParam("pp1","booking");

        //Set the expected Data
        HerokuBookingDatesPojo bookingDates = new HerokuBookingDatesPojo("2018-01-01","2019-01-01");
        HerokuBookingPojo expectedData = new HerokuBookingPojo("Jim","Brown",111,true,bookingDates,"Breakfast");

        //Set the Response
        Response response = given(spec).body(expectedData).when().post("{pp1}");
        response.prettyPrint();
        id = response.jsonPath().getInt("bookingid");       //id yi burada dinamik hale getirdik.gelen response dan olusturulan id yi otomatik aliyoruz.
        System.out.println("bookingid = " + id);
        

        //Do Assertions
        HerokuResponsePojo actualData = convertJsonToJava(response.asString(), HerokuResponsePojo.class);

        assertEquals(statusCodeOk, response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());



    }
}
