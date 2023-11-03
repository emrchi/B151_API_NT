package get_request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01_Intro {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/55
    When
        Kullanıcı URL'e bir GET request gönderir
    Then
        HTTP Status Code 200 olmalı
    And
        Content Type "application/json" olmalı
    And
        Status Line "HTTP/1.1 200 OK" olmalı
*/
    @Test
    public void get01() {
        //Birinci yontem for url
        //String url = "http://restful-booker.herokuapp.com/booking/1";
        //given().when().get(url);

        //Ikinci yontem for url
        RestAssured.baseURI = "http://restful-booker.herokuapp.com";
        RestAssured.basePath = "/booking/1";
        Response response = given().when().get();
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 OK");

        //Assert.assertEquals(200, response.statusCode());
        //Assert.assertEquals("application/json", response.contentType());
        //Assert.assertEquals("HTTP/1.1 200 OK", response.statusLine());


        /*
    API'lerde en onemli kisim gonderdiginiz veri ile gelen veri birbiri ile ortusup ortusmedigidir.
    Biz farkli yollarla (Hamcrest Matchers,assertion vs.) bunu dogrulariz.

    Swagger Dokumani:
    Api dokumantasyonu icin Swagger i kullaniriz.
    Kullanacagimiz end pointleri, methodlari (get, put, post) bunlari nasil ve hangi petlerle kullanacagimizi anlatir.
    Icine girince testlerimizi orda da yapabiliriz
    Bize gelebilecek ornek response datayi da saglar

    Postman:
    API test araci. Hem manuel hem otomasyon ile test yapilabilir.

    Serialization = Java Map objesinin Json objesine donusturulmesidir.
    Deserialization = Json Objesinin Java Map Objesine donusturulmesidir.

     */


    }
}
