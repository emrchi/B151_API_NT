package get_request;


import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestResponse {
    /*
    Notlar:
    1. Manual testler icin Postman kullanacagiz
    2. API otomasyon testleri icin REST Assured kutuphanesini kullanacagiz.
    3. Otomasyon kodlarini yazarken su adimlari takip edecegiz:
        a.Gereksinimleri anlama
        b.Test senaryolarini yazma
            i. Test senaryolarini yazarken Gherkin dilini kullanacagiz.
                -Given: On kosullar: Endpoint, body
                -When: Islemler: Get, Post, Put, Delete ...
                -Then: Donutler: Assertion, Close ...
                -And: Coklu islemlerin yapilacagi zaman kullanilir.
        c.Otomasyon kodlarini yazarken su adimlari takip ederiz:
            i. Set the URL
            ii. Set the expected data
            iii. Send the request and get the response
            iv. Do assertion


            i. Set the URL
            ii. Set the expected data
            iii. Send the request and get the response
            iv. Do assertion

       */

        //UI Testi----> API Testi ------> Database Testi
        //Postman'de API testi yapabiliriz ancak UI ve DataBase Testlerini yapamayiz.
        //Bir veri gonderiyoruz, register islemi yapiyoruz. Bunu UI'da yaptik ve bilgileri intellij'de bir pojo class'ta kaydedebiliriz
        //Bu kayitli bilgileri UI ile DataBase Testleri ile kontrol edebiliriz. UI, DataBase ve API testlerini yapabildigimizde
        //full stack Otomation Engineer olabiliriz. Bu testleri intellij'de yapabiliriz.
        /*Biz intellij de hem UI(Selenium ile) - Api(Rest Assured ile ) - Database(JDBC ile) testi yapabiliriz

        Postman da otomasyon yapilabilir. Lakin postman de sadece Api Testi yaparken Intellij de E2E testi yapabiliriz
        UI Testi ---> API Testi ---> Database Testi === E2E TEST
        */

    public static void main(String[] args) {
        String url = "https://petstore.swagger.io/v2/pet/9898";
        Response response = given().when().get(url);

        // Body nasil yazdirilir
        response.prettyPrint();

        // Status Code nasil yazdirilir
        System.out.println("Status Code = " + response.statusCode());

        //Content Type nasil yazdirilir
        System.out.println("Content Type = " + response.contentType());

        //Status Line nasil yazdirilir
        System.out.println("Status Line = " + response.statusLine());

        //Header bolumundeki bir veri nasil yazdirilir
        System.out.println("Header | Server = " + response.header("Server"));

        //Headers bolumu nasil yazdirilir
        System.out.println("Headers = " + response.headers());

        //Time bilgisi nasil yazdirilir
        System.out.println("Time = " + response.time() + " ms");

    }
}
