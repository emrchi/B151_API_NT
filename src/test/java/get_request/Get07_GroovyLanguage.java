package get_request;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07_GroovyLanguage extends JsonPlaceHolderBaseURL {
    /*
        Given
           https://jsonplaceholder.typicode.com/todos
        When
           Kullanıcı URL'e bir GET request gönderir
        Then
           1) Status code 200 olmalı
           2) "Id"leri 190 dan büyük olanları konsola yazdırın
              "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
           3) "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
              "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
           4) "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
              "delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
     */

    @Test
    public void get07() {
        //Groovy Language
        //findAll{} bir tablo icinde arama yapmak icin kullaniriz.
        // {} icine item 'in kisaltmasi olan it yazilir ve hangi veriyi istiyorsak onun sartini ekleriz
        //{} icinde sarti koyunca sarta uyan tum veriler gelirken
        // {} den sonra . koyup neyi almak istiyorsak o veriyi yazarsak sadece o veriyi aliriz

        spec.pathParam("pp1","todos");

        Response response = given(spec).when().get("{pp1}");

        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        List<Object> idList = jsonPath.getList("findAll{it.id>190}.id");
        System.out.println("idList = " + idList);
        assertEquals(10, idList.size());

        List<Integer> userIdList = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("userIdList = " + userIdList);
        assertEquals(4,userIdList.size());

        List<String> titleList = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("titleList = " + titleList);
        assertTrue( titleList.contains("delectus aut autem"));

        List<Integer> id = jsonPath.getList("findAll{it.title=='quis ut nam facilis et officia qui'}.id"); //title 'i bilmem ne olanin id sini bana ver ..
        System.out.println("id = " + id);


    }
}
