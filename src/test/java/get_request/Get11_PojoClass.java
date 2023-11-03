package get_request;

import baseURLDeposu.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerokuBookingDatesPojo;
import pojos.HerokuBookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get11_PojoClass extends HerokuAppBaseUrl {
    /*
     Given
         https://restful-booker.herokuapp.com/booking/11
     When
         I send GET Request to the URL
     Then
         Status code is 200
     And
         Response body is like:
              {
                     "firstname": "John",
                     "lastname": "Smith",
                     "totalprice": 111,
                     "depositpaid": true,
                     "bookingdates": {
                         "checkin": "2018-01-01",
                         "checkout": "2019-01-01"
                     },
                     "additionalneeds": "Breakfast"
                 }
  */

    @Test
    public void get11() {
        //Set the Url
        spec.pathParams("pp1","booking","pp2",11);

        //Set the expected data
        HerokuBookingDatesPojo bookingDates = new HerokuBookingDatesPojo("2018-01-01","2019-01-01");
        HerokuBookingPojo expectedData = new HerokuBookingPojo("John","Smith",111,true,bookingDates,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Icteki veriler icin olusturdugumuz pojo class
        //HerokuBookingDatesPojo class'indan bir obje olusturup, task icindeki verileri girdik. HerokuBookingDatesPojo class'ta
        //parametreli constructor olusturduk ve parametre olarak checkin ile checkout'u String olarak yazmistik
        //Bu nedenle objemize veri girdigimizde ilk veri icin checkin, ikinci veri icin checkout otomatik olarak ekleme
        //yaparken goruldu
        //Distaki veriler icin olusturdugumuz pojo class
        //HerokuBookingPojo class'tan obje olusturalim ve verileri oradaki parametreli constructor'a gore girelim

        //Set the response
        Response response = given(spec).when().get("{pp1}/{pp2}");

        //Do Assertion
        HerokuBookingPojo actualData = response.as(HerokuBookingPojo.class);
        System.out.println("actualData = " + actualData);

        //iki pojo class'imiz var. Biz HerokuBookingPojo class'i response.as
        //icine koyduk. Yani gelen tum datalar HerokuBookingPojo class'ta olmali. Biz tum datalari bu class'a almistik
        //private String firstname;
        //    private String lastname;
        //    private Integer totalprice;
        //    private Boolean depositpaid;
        //private BookingDatesPojo bookingdates;
        //private String additionalneeds;
        //Gelen response HerokuBookingPojo ile eslesmeli. Tum datalar bu class'ta oldugu icin bu pojo class'i sectik.
        //Response'agelen datalarin hepsi BookingPojo class icinde var

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin()); //icteki json'a girdigi icin kisa yol bookingDates'den cagiriyoruz.
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());


    }
}
