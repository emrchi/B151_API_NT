package get_request;

import baseURLDeposu.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09_TypeCasting extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/472
    When
        I send GET Request to the url
    Then
        Response body should be like that;
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Extra pillows please"
            }
     */

    @Test
    public void get09() {

        //1- Set the Url
        spec.pathParams("pp1","booking","pp2",472);

        //2- Set the expected data
        Map<String,Object> bookingdatesData = new HashMap<>();
        bookingdatesData.put("checkin", "2018-01-01");
        bookingdatesData.put("checkout", "2019-01-01");
        System.out.println("bookingdatesData = " + bookingdatesData);
        
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname","Jane");
        expectedData.put("lastname","Doe");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.putAll(bookingdatesData);
        expectedData.put("additionalneeds","Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        //Set the response
        Response response = given(spec).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
        assertEquals(bookingdatesData.get("checkin"), (((Map)actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingdatesData.get("checkout"), (((Map)actualData.get("bookingdates"))).get("checkout"));

       // Object obj = new HashMap<>();
       // ((Map)obj).get("ghjgkjj");    Burada Type Casting yaptik cunku object ata oldugu icin child'in methodunu kullanamaz
                                      //Yani get() methodunu kullanabilmke icin yukarida da casting yapmamiz gerek


    }

    //Birde aynisini reuseble method ile yapalim

    @Test
    public void get09b() {

        //1- Set the Url
        spec.pathParams("pp1","booking","pp2",687);

        //2- Set the expected data
        HerokuAppTestData obj = new HerokuAppTestData();
        Map<String,String> bookingdatesData = obj.bookingDatesMapper("2018-01-01","2019-01-01");
        System.out.println("bookingdatesData = " + bookingdatesData);

        Map<String,Object> expectedData = obj.expectedDataMapper("Jane","Doe",111,true,
                                                                 bookingdatesData,"Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        //Set the response
        Response response = given(spec).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
        assertEquals(bookingdatesData.get("checkin"), (((Map)actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingdatesData.get("checkout"), (((Map)actualData.get("bookingdates"))).get("checkout"));

        // Object obj = new HashMap<>();
        // ((Map)obj).get("ghjgkjj");    Burada Type Casting yaptik cunku object ata oldugu icin child'in methodunu kullanamaz
        //Yani get() methodunu kullanabilmke icin yukarida da casting yapmamiz gerek

        //2. yol map type casting yapmadan jsonpath kullnarak da yapabiliriz...
        JsonPath jsonPath = response.jsonPath();
        assertEquals(bookingdatesData.get("checkin"), jsonPath.getString("bookingdates.checkin"));
        assertEquals(bookingdatesData.get("checkout"), jsonPath.getString("bookingdates.checkout"));



    }
}


