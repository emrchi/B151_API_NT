package Practice01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get01_IDileContactGetirme {
    @Test
    public void get01() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath ="/contacts/64de3f316e14350013e08d88";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGRlMjg0ODZlMTQzNTAwMTNlMDhkNGIiLCJpYXQiOjE2OTIyODE4NTF9.Vp3bwmjRWlNkEiDHW7va9XlR9TMDxH9En6nKnQPaJ4Q";

        Response response = given().auth().oauth2(token).when().get();
        response.prettyPrint();
        response.then().body("firstName", equalTo("John"))
                       .body("lastName",equalToIgnoringCase("Doe"))
                       .body("email", not(equalTo("emre@emre.com")))
                       .body("email", containsString("@fake.com"))
                       .body("city",startsWith("Any"))
                       .body("city",endsWith("town"))
                       .body("stateProvince", anyOf(equalTo("KS"),equalTo("CA")))
                       .body("country", allOf(equalTo("USA"),equalToIgnoringCase("usa")))
                       .body("__v",greaterThan(-1));

    }
}
