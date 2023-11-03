package apiCalismalari;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Api_PostRequest {
    @Test
    public void post01() {
    /*
    https://jsonplaceholder.typicode.com/posts  URL’ine
    asagidaki body ile bir POST request gonderdigimizde
    {
     "title":"API",
     "body":"API ogrenmek ne guzel",
     "userId":10
     }

     donen Response’un,
     status code’unun 201,
     ve content type’inin "application/json"
     ve Response Body'sinin id haric,
     {
     "title":"API",
     "body":"API ogrenmek ne guzel",
     "userId":10,
     }

     oldugunu test edin.
     */

        //1-Request URL ve Body olustur
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        RestAssured.basePath="/posts";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "API");
        requestBody.put("body", "API ogrenmek ne guzel");
        requestBody.put("userId", 10);
        //System.out.println("requestBody = " + requestBody);

        //2-Expected Data Olustur
        JSONObject expBody = new JSONObject();
        expBody.put("title", "API");
        expBody.put("body", "API ogrenmek ne guzel");
        expBody.put("userId", 10);
        //System.out.println("expBody = " + expBody);

        //3- Response kaydet
        Response response = given().
                            contentType(ContentType.JSON).
                            when().
                            body(requestBody.toString()).
                            post();

        //response.prettyPrint();

        JsonPath actBody = response.jsonPath();
        //System.out.println("actBody.get(\"title\") +actBody.get(\"body\")+actBody.get(\"userId\") = " + actBody.get("title") +actBody.get("body")+actBody.get("userId"));

        //4-Assertion

        response.
                then().
                assertThat().
                statusCode(201).
                contentType(ContentType.JSON);

        Assert.assertEquals(expBody.get("title"), actBody.get("title"));
        Assert.assertEquals(expBody.get("body"), actBody.get("body"));
        Assert.assertEquals(expBody.get("userId"), actBody.get("userId"));



    }
}
