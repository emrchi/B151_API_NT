package post_request;

import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03_PojoClass extends JsonPlaceHolderBaseURL {

    /*
Payload Olusturma (Map)
Payload Olusturma (Pojo Class)
Pojo classlar variable'lari private yapip getter, setter methodlariyla cagirip degisiklik yaptigimiz class'lardir.

Ic ice json veriler gelirse herbiri icin ayri bir pojo class olusturmaliyiz.


 */





/*
    Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
    When
        I send POST Request to the Url
    Then
        Status code is 201
    And
        response body is like
        {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false,
            "id": 201
        }
     */

    @Test
    public void post03() {
        //Set the Url
        spec.pathParam("pp1","todos");

        //Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55,"Tidy your room",false);

        //JsonPLaceHolderPojo class ismi ile obje olusturduk ve parametreli bir obje olusturduk. Bu sayede verilerimizi
        // de olusturduk. Obje icerisine body icindeki verileri girdik. JsonPlaceHolderPojo class'taki constructor
        //parametrelerine gore parametreleri siraladik

        //Set the response
        Response response = given(spec).body(expectedData).when().post("{pp1}");
        response.prettyPrint();

        //Do Assertion
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());

        //hem expectedData hem de actualData icinde JsonPlaceHolderPojo class verileri var. Ayni userID, title, completed'a
        //hem actualData hem expectedData'dan veriler kaydediyoruz.expectedData ve actualData her ikisi de object.
        //request gonderince bize gelen response'dan gelen datayi yine JsonPlaceHolderPojo'ya kaydediyoruz.
        //Bunun ne demek oldugu uzerinde dusunduk. Object'lerin ozellikleri uzerinde durduk. Her seferinde ayni
        //kalibi kullaniyoruz ancak object'i degistiriyoruz. Kalip dedigimiz sey: JsonPlaceHolderPojo class'ta
        //olusturdugumuz uc private deger(userId, title ve completed). Bu kaliplardan expectedData ve actualData isimli
        //iki object olusturunca her ikisinin de userId, title ve completed datalari farkli olur. Biz bu kaliplari static
        //yapsaydik objectler hep ayni degeri gorurdu. Objectlerin kendilerine has kaliplari, variable'lari olabilir.
    }
}
