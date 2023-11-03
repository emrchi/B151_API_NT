package get_request;

import baseURLDeposu.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

public class Get06_JsonPath extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/123
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is “application/json”
    And
        Response body should be like;
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
    public void Get() {
        //1.Set the url
        spec.pathParams("first","booking",
                   "second",123);

        //2.Set expected data
        //3.Send request and get respnse
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //4.Do assertion

        //1.Yol
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Josh")
                ,"lastname", equalTo("Allen"),
                "totalprice", equalTo(111),
                "depositpaid", equalTo(true),
                "bookingdates.checkin", equalTo("2018-01-01"), //bookingdates'ten sonra . koyup bookingdates body sine girerek checkin degerini kontrol edebiliriz
                "bookingdates.checkout", equalTo("2019-01-01"),
                "additionalneeds", equalTo("super bowls"));

        //2.Yol
        JsonPath json = response.jsonPath(); //response'u jsonPath() methodu kullanarak JsonPath data cesidine donusturduk
                                             // jsonpath datasindan response da datalara kolayca ulasabilirim
       // System.out.println(json.getString("firstname"));

        assertEquals("Josh",json.getString("firstname"));
        assertEquals("Allen",json.getString("lastname"));
        assertEquals(111,json.getInt("totalprice"));
        assertTrue(json.getBoolean("depositpaid"));
        assertEquals("2018-01-01",json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",json.getString("bookingdates.checkout"));
        assertEquals("super bowls",json.getString("additionalneeds"));


    }
}