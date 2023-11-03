package post_request;

import baseURLDeposu.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerokuBookingDatesPojo;
import pojos.HerokuBookingPojo;
import pojos.HerokuResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04_PojoClassTreeLayer extends HerokuAppBaseUrl {
    /*
        Given
          1)  https://restful-booker.herokuapp.com/booking
          2) {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
              }
        When
            I send POST Request to the URL
        Then
            Status code is 200
        And
            Response body is like
                 {
                    "bookingid": 16,
                    "booking" :{
                        "firstname": "Ali",
                        "lastname": "Can",
                        "totalprice": 999,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2021-09-21",
                            "checkout": "2021-12-21"
                        },
                        "additionalneeds": "Breakfast"
                     }
                  }
     */

    @Test
    public void post04() {
        //Set the url
        spec.pathParam("pp1","booking");

        //Set the expected data
        HerokuBookingDatesPojo bookingDates = new HerokuBookingDatesPojo("2021-09-21","2021-12-21");
        HerokuBookingPojo expectedData = new HerokuBookingPojo("Ali","Can",999,true,bookingDates,"Breakfast");

        System.out.println("expectedData = " + expectedData);
        
        //Set the response
        Response response = given(spec).body(expectedData).when().post("{pp1}");
        response.prettyPrint();
        
        //Do assertions
        HerokuResponsePojo actualData = response.as(HerokuResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.statusCodeOk, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

    }
}
