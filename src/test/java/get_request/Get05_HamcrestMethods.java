package get_request;

import baseURLDeposu.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

public class Get05_HamcrestMethods extends HerokuAppBaseUrl {
    /*Given
     https://restful-booker.herokuapp.com/booking
When
     User send a request to the URL
Then
     Status code is 200
And
     Among the data there should be someone whose firstname is "Sally" and last name is "Brown"

     */
//end point te .com dan sonraki kisim path
// ? 'den sonraki kisim ise query (sorgu) parametresidir

    @Test
    public void Get(){
        //1.Set Url
        spec.pathParam("first","booking")   //path parametresini ekledik
                .queryParams("firstname","Sally"    //sorgu parametrelerini ekledik
                                ,"lastname","Brown");

        //2. Set Expected data
        //3.Sent req. get resp

        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        //4.Do assertion
        response.then().statusCode(200)
                .body(containsString("bookingid"))
                .body("bookingid",hasSize(greaterThan(0))); // 0dan buyuk sayida id varsa pass verecek

        assertTrue(response.asString().contains("bookingid"));

    }
}