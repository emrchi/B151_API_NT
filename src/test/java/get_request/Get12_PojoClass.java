package get_request;

import baseURLDeposu.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12_PojoClass extends GoRestBaseUrl {
    /*
      Given
          https://gorest.co.in/public/v1/users/4496516
      When
          User send GET Request to the URL
      Then
          Status Code should be 200
      And
          Response body should be like
      {
    "meta": null,
    "data": {
        "id": 4496516,
        "name": "Narinder Pandey",
        "email": "narinder_pandey@franecki-mcglynn.example",
        "gender": "male",
        "status": "active"
    }
}
  */

    @Test
    public void get12() {
        //Set the Url
        spec.pathParams("pp1","users","pp2",4496516);

        //Set the expected data

        GoRestDataPojo goRestData = new GoRestDataPojo("Narinder Pandey","narinder_pandey@franecki-mcglynn.example","male","active");
        GoRestPojo expectedData = new GoRestPojo(null, goRestData);
        System.out.println("expectedData = " + expectedData);

        //Set the response
        Response response = given(spec).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //Do Assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.status,response.getStatusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(goRestData.getName(),goRestData.getName());
        assertEquals(goRestData.getEmail(),goRestData.getEmail());
        assertEquals(goRestData.getGender(),goRestData.getGender());
        assertEquals(goRestData.getStatus(),goRestData.getStatus());



    }
}
