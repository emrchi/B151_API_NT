package apiCalismalari;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Api_BaseURL_TestDataKullanimi extends JsonPlaceHolderBaseURL {

            /*

         https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
          request yolladigimizda donen response’in status kodunun 200 ve
          response body’sinin asagida verilen ile ayni oldugunu test ediniz


           Response body :
        {
        "userId":3,
        "id":22,
        "title":"dolor sint quo a velit explicabo quia nam",
        "body":"eos qui et ipsum ipsam suscipit aut
        sed omnis non odio
        expedita earum mollitia molestiae aut atque rem suscipit
        nam impedit esse"
        }

             */

    @Test
    public void test01() {
        
        //1- URL ve Request Body olustur
        spec.pathParam("pp1",22);    //baseURL package'inde olusturdugumuz base url ye eklemek icin path parametresi olusturuyoruz.

        //2- Expected Data Olustur
        JsonPlaceHolderTestData jsonPlaceHolder = new JsonPlaceHolderTestData();
        JSONObject expBody = jsonPlaceHolder.expectedDataOlustur();
        //System.out.println("expBody = " + expBody);

        //3- Response kaydet
        Response response =given().spec(spec).when().get("{pp1}");
        //response.prettyPrint();

        //4- Assertion
        JsonPath responseJsonPath = response.jsonPath();   //actual result

        assertEquals(jsonPlaceHolder.statusOk, response.getStatusCode());
        assertEquals(expBody.getInt("userId"),responseJsonPath.getInt("userId"));
        assertEquals(expBody.getInt("id"),responseJsonPath.getInt("id"));
        assertEquals(expBody.get("title"),responseJsonPath.get("title"));
        assertEquals(expBody.get("body"),responseJsonPath.get("body"));


    }
}
