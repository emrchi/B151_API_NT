package post_request;

import baseURLDeposu.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02_MapExpActData extends HerokuAppBaseUrl {
    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like
            {
                "bookingid": 5315,
                "booking": {
                    "firstname": "John",
                    "lastname": "Doe",
                    "totalprice": 11111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2021-09-09",
                        "checkout": "2021-09-21"
                    }
                }
             }
     */

    @Test
    public void post02() {
        //Set the Url
        spec.pathParam("pp1", "booking");

        //Set the expected data
        HerokuAppTestData obj = new HerokuAppTestData();
        Map<String,String> bookingDatesData = obj.bookingDatesMapper("2021-09-09","2021-09-21");
        Map<String,Object> expectedData = obj.expectedDataMapper("John","Doe",1111,true,bookingDatesData,null);
        System.out.println("expectedData = " + expectedData);

        //Set the response
        Response response = given(spec).body(expectedData).when().post("{pp1}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);

        //ic ice gecmis json objectleri oldugu icin ic ice map type casting yapmak zorundayiz.
        assertEquals(obj.basariliStatusKodu, response.statusCode());
        assertEquals(expectedData.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingDatesData.get("checkin"), ((Map)((Map)actualData.get("booking")).get("depositpaid")).get("checkin"));
        assertEquals(bookingDatesData.get("checkin"), ((Map)((Map)actualData.get("booking")).get("depositpaid")).get("checkout"));

    }
}
