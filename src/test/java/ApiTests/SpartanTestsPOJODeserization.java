package ApiTests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SpartanTestsPOJODeserization {

    String URL_Base="http://54.160.71.107:8000";


    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).
                pathParam("id", 15).
                when().get(URL_Base+"/api/spartans/{id}");

        //GSON -->de-serialization
        //How to convert json response to our spartan class
        Spartan spartan1=response.body().as(Spartan.class);

       // System.out.println(spartan1.toString());
        //Verify each key with spartan object
        assertEquals(spartan1.getName(),"Meta");
        assertEquals(spartan1.getId(),15);
        assertEquals(spartan1.getGender(),"Female");
        assertEquals(spartan1.getPhone(),1938695106);
    }
}

