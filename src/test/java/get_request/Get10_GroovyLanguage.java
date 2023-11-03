package get_request;

import baseURLDeposu.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get10_GroovyLanguage extends GoRestBaseUrl {
    /*
    https://gorest.co.in/public/v1/users
    When
    User send GET Request
    Then
    The value of "pagination limit" is 10
    And
    The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
    The number of users should  be 10
    And
    We have at least one "active" status
            And
            "Kannan Ahluwalia", "The Hon. Tara Chaturvedi" and "Damayanti Dubashi" are among the users
    And
    The female users are less than or equals to male users
            (Kadın kullanıcı sayısı erkek kullanıcı sayısından küçük yada eşit olmalı)
     */

    @Test
    public void get10() {
        //Set the Url
        spec.pathParam("pp1","users");

        //Set the expected data
        //Bu test'imizde response'i miz cok uzun ve cok veri list seklinde geldigi icin expected data olusturamayiz.

        //Set the response
        Response response = given(spec).when().get("{pp1}");
        response.prettyPrint();

        //Do Assertion
        //Expected data olusturamadigimiz icin assertionlarimizi groovy language ile ve hamcrest Matchers Metodlari ile yapabiliriz.
        response
                .then()
                .body("meta.pagination.limit",equalTo(10))
                .body("meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"))
                .body("data",hasSize(10))
                .body("data.status",hasItem("active"))
                .body("data.name",hasItems("Abhisyanta Prajapat", "Bhargavi Panicker I", "Anang Kakkar"));

        //response'da pagination ve onunda icindeki limit
        //meta data'nin icinde idi. "meta": {
        //"pagination": {
        //"limit":10, seklinde bir sonucu console'da response olarak gormustuk. meta data ile baglantili olunca
        //body icinde dogrulama yaparken meta.pagination.limit yazdik ve bu limit'in 10'a esit olma durumunu dogrulamaya
        //calistik.

        //response'da pagination ve onunda icindeki links ve links icindeki current, meta data'nin icinde idi. "meta": {
        //"pagination": {
        //"links":{
        // "current":"https://gorest.co.in/public/v1/users?page=1", seklinde bir sonucu console'da response olarak gormustuk. meta data ile baglantili olunca
        //body icinde dogrulama yaparken meta.pagination.links.current yazdik ve bu current neye esit ise onu yazdik
        //body icinde bu defa meta yerine data ile basladik. Cunku task'a gore istenilenler data listi icinde

        //number of users should be 10. User isim ve user bilgilerini
        // iceren bir list icinde 10 data'miz oldugunu console'da gorduk. Neyin
        //size'ini almaliyiz? Data bir list oarada [] vardi ve bu listin size'ini almaliyiz. hasSize() metodu ile size'i alabiliriz
        //hasSize ile listenin boyutunu sorguluyor ve dogruluyoruz.

        //datalar icinde en az bir tane status:active
        //hasItem ile bir liste icindeki degerleri sorguluyor ve dogruluyoruz.

        //task icinde yazili isimler degismis, console'da actual olarak verilen isimler arasindan 3 ismi aldik hasItems sonrasina yazdik
        //Bu isimlerin olmasi aranmis. Response icinde bu isimlere baktik ve name: sonrasi isimleri gordugumuzden
        //data.name olarak body icine yazdik

        //Groovy language'de findAll ile basliyorduk. Bunu yapabilmemiz icin list en basta olmali. Burada ise data
        //isimli listimiz meta altinda kaliyor. Biz de data icindeki item'i bulmak icin bu defa data.findAll ile baslayacagiz
        //hangi liste icinde arayacaksak o listin adi ile baslariz
        //.body("data.findAll{it.gender=='female'}.size()", lessThanOrEqualTo("data.findAll{it.gender=='male'}.size()"));
        // Taskagore bu sekilde body'i yazdik ancak run edince hata aldik. Hamcrest Matchers metodlari icine Groovy language
        //yazamadigimizi anladik. Bu yol yerine asagiya baska bir yol bulduk. JsonPath icine bunlari yazabiliriz. Bu
        //nedenle JsonPath class'indan bir obje olusturduk.

        JsonPath json = response.jsonPath();
        List<String> femaleList = json.getList("data.findAll{it.gender=='female'}");
        System.out.println(femaleList.size());
        List<String> maleList = json.getList("data.findAll{it.gender=='male'}");
        System.out.println(maleList.size());
        // assertTrue() metodu içerisinde Hamcrest Matchers metotları kullanılamaz
        assertTrue(femaleList.size() <= maleList.size());
        // assertThat() metodu içerisinde Hamcrest Matchers metotları kullanılabilir
        assertThat(femaleList.size(), lessThanOrEqualTo(maleList.size()));




    }
}
