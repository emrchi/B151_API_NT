package baseURLDeposu;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utils.AuthenticationGmiBank;

import static utils.AuthenticationHerokuApp.generateToken;

public class GmiBankBaseUrl {
   protected RequestSpecification spec;

    @Before
    public void Setup()  {
        spec = new RequestSpecBuilder().
                setBaseUri("https://gmibank.com").
                setContentType(ContentType.JSON).
                addHeader("Authorization","Bearer " + AuthenticationGmiBank.generateToken()).
                build();

        //Content Type olarak ContentType.JSON ekledik. Bunu her zaman isteyebilir bu nedenle baseUrls'e ekledik
        //addHeader() ile token ekliyoruz
        //build() action daki perform gibidir en sonda olmazsa calismaz


    }

}
//Her sorguda tekrar eden datalari buraya girecegiz