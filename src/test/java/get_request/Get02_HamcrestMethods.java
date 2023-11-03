package get_request;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

public class Get02_HamcrestMethods {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/0
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status code 404 olmalı
        And
            Status Line "HTTP/1.1 404 Not Found" olmalı
        And
            Response body "Not Found" içermeli
        And
            Response body "TechProEd" içermemeli
        And
            Server değeri "Cowboy" olmalı
    */
    @Test
    public void get02() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/booking/0";
        given()
                .when()
                .get()
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .body(containsString("Not Found"))
                .body(not(containsString("TechProed")))
                .header("server","Cowboy");

    }
}
