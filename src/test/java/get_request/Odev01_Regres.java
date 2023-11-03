package get_request;

import baseURLDeposu.RegresBaseUrl;
import org.junit.Test;

public class Odev01_Regres extends RegresBaseUrl {
    /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void odev01() {
        //Set the Url
        spec.pathParams("pp1","api","pp2","users","pp3","3");

        //Set the expected data


    }
}
