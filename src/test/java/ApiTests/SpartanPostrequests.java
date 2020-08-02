package ApiTests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpartanPostrequests {

    String URL_Base="http://54.160.71.107:8000";

    @Test
    public void PostWithString(){
        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON).
                body("{\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"name\": \"Mike\",\n" +
                        "  \"phone\": 2025478854\n" +
                        "}").when().post(URL_Base + "/api/spartans/");

        response.prettyPeek();

        //validations
        //verify status code is 201
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        //verify success message
        assertEquals(response.path("success"),"A Spartan is Born!");

        //verify request body
        JsonPath json=response.jsonPath();

        assertEquals(json.getString("data.name"),"Mike");
        assertEquals(json.getString("data.gender"),"Male");
        assertEquals(json.getLong("data.phone"),2025478854L);
    }

    @Test
    public void PostMethodWithMap(){
        //create a map to be use a body for request
        Map<String,Object>requstMap=new HashMap<>();
        requstMap.put("name","MikeMap");
        requstMap.put("gender","Male");
        requstMap.put("phone",2025478854L);

        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON).
                body(requstMap).
                when().post(URL_Base + "/api/spartans/");

        assertEquals(response.statusCode(),201);
        response.prettyPeek();
    }
    @Test
    public void PostWithPojo(){
        //create Spartan object and used as a body for post request
        Spartan spartan=new Spartan();
        spartan.setName("MikePOJO");
        spartan.setGender("Male");
        spartan.setPhone(543256123412l);

        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .and().body(spartan).when().post(URL_Base+"/api/spartans/");

        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        response.prettyPeek();

        System.out.println("=============GET==REQUEST=====");

        Response response1 = given().accept(ContentType.JSON).
                and().pathParam("id", 107).
                and().when().get(URL_Base+"/api/spartans/{id}");

        Spartan spartanResponse=response1.body().as(Spartan.class);

        System.out.println(spartanResponse.toString());
    }
}
