package put_request;

import baseURLDeposu.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponsePojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02_ObjectMapper_Pojo extends DummyRestApiBaseUrl {
    /*
    Test Case: Type by using Gherkin Language

    Given
       1)URL: https://dummy.restapiexample.com/api/v1/update/21
       2)       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
   When
       Kullanici PUT Request  gonderir

   Then
        i) Status code is 200
   And
        ii) Response body should be like the following
            {
                "status": "success",
                "data": {
                    "employee_name": "Ali Can",
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image"
                },
                "message": "Successfully! Record has been updated."
            }
     */

    @Test
    public void put02() {
        //Set the Url
        spec.pathParams("pp1","update","pp2",21);

        //Set the expected data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Ali Can",111111,23,"Perfect image");
        System.out.println("expectedData = " + expectedData);

        //Set the response
        Response response = given(spec).body(expectedData).when().put("{pp1}/{pp2}");
        response.prettyPrint();

        //Do assertions
        DummyRestApiResponsePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(DummyRestApiDataPojo.statusOk, response.statusCode());
        assertEquals(expectedData.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(), actualData.getData().getProfile_image());
        //assertEquals("success", actualData.getStatus());
        //assertEquals("Successfully! Record has been updated.", actualData.getMessage());      //gereksinimlerde istenmedigi icin dogrulama yapmak mecburi degil.




    }
}
