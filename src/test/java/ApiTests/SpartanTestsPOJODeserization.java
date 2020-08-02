package ApiTests;

import static io.restassured.RestAssured.*;

import com.google.gson.Gson;
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

    @Test
    public void gsonExample(){
        Gson gson=new Gson();

        String myJsonBody="{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        //Using gson method do de serialization our json body
        Spartan spartanMeta=gson.fromJson(myJsonBody, Spartan.class);

        System.out.println(spartanMeta.toString());

        //serialization Java Object --> JSON body

        Spartan spartan=new Spartan(101,"Mike","Male",321342123l);
        //converting custom class to json (serialization)
        String jsonBody=gson.toJson(spartan);

        System.out.println(jsonBody);
    }
}

