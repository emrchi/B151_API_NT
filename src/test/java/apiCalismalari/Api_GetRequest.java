package apiCalismalari;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Api_GetRequest {

      /*
        https://jsonplaceholder.typicode.com/posts/44 url'ine bir
        GET request yolladigimizda donen Response’in

        status code'unun 200,
        ve content type'inin JSON,
        ve response body'sinde bulunan userId'nin 5,
        ve response body'sinde bulunan title'in "optio dolor molestias sit"
        			oldugunu test edin.
         */

    @Test
    public void get01() {

        // 1- Request URL ve Body olustur
        String url = "http://jsonplaceholder.typicode.com/posts/44";

        // 2- Expected Data olustur
        JSONObject expBody = new JSONObject();
        expBody.put("userId",5);
        expBody.put("title","optio dolor molestias sit");
        //System.out.println(expBody);

        // 3 - Responsu kaydet
        Response response = given().when().get(url);
        //response.prettyPrint();

        // 4 - Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);

        JsonPath actBody = response.jsonPath();

        Assert.assertEquals(expBody.get("userId"),actBody.get("userId"));
        Assert.assertEquals(expBody.get("title"),actBody.get("title"));

        //SoftAssert ile yapalim
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actBody.get("userId"),expBody.get("userId"));
        softAssert.assertEquals(actBody.get("title"),expBody.get("title"));

        softAssert.assertAll();
    }
}
