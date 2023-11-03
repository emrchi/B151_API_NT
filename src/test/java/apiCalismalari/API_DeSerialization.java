package apiCalismalari;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class API_DeSerialization extends JsonPlaceHolderBaseURL {
    
        /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
    body’e sahip bir PUT request yolladigimizda donen response’in
    response body’sinin asagida verilen ile ayni oldugunu test ediniz

 Request Body

{
"title":"Ahmet",
"body":"Merhaba",
"userId":10,
"id":70
}

Expected Data :

{
"title":"Ahmet",
"body":"Merhaba",
"userId":10,
"id":70
}
*/

    @Test
    public void test01() {
        
        //1 -URL ve Request body olustur
        spec.pathParam("pp1","70");

        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        Map<String,Object> requestBodyMap = jsonPlaceHolderTestData.expectedDataMethod(10,"Ahmet",false);
        //System.out.println("requestBodyMap = " + requestBodyMap);
        
        //2 - Expected Data olustur

        Map<String,Object> expectedDataMap = jsonPlaceHolderTestData.expectedDataMethod(10,"Ahmet",false);
        //System.out.println("expectedDataMap = " + expectedDataMap);

        //3- Response kaydet
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec).
                when().
                body(requestBodyMap).
                put("{pp1}");
        //response.prettyPrint();

        //4-Assertion
        //Burada exp data ve responese iki ayri data tipinde oldugu icin (yani map ile json) pom.xml e
        // yukledigimiz gson dipendisies sayesinde response'umuzu map'e cevirip sonra karsilastiririz.

        Map<String,Object> responseBodyMap = response.as(HashMap.class);

        assertEquals(jsonPlaceHolderTestData.statusOk,response.getStatusCode());

        assertEquals(expectedDataMap.get("title"), responseBodyMap.get("title"));
        assertEquals(expectedDataMap.get("body"), responseBodyMap.get("body"));
        assertEquals(expectedDataMap.get("userId"), responseBodyMap.get("userId"));
        assertEquals(expectedDataMap.get("id"), responseBodyMap.get("id"));
    }
}
