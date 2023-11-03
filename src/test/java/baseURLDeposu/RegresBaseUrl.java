package baseURLDeposu;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RegresBaseUrl {
    protected RequestSpecification spec;

    @Before
    public void Setup()  {
        spec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/").
                setContentType(ContentType.JSON).
                build();

        //Accep Type olarak ContentType.JSON ekledik. Bunu her zaman isteyebilir bu nedenle baseUrls'e ekledik
        //build() action daki perform gibidir en sonda olmazsa calismaz


    }
}
