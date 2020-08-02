package ApiTests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanPUT {
    String URL_Base="http://54.160.71.107:8000";

    @Test
    public void PUTRequest(){
        //Different ways to send json body
        //-String
        //-Using collection(Map)
        //-POJO

        //Using Map
        Map<String,Object>putMap=new HashMap<>();
        putMap.put("name","Alex");
        putMap.put("gender","Male");
        putMap.put("phone",8844330023l);

        //we gonna send request body with updated value, and content type header
        given().contentType(ContentType.JSON)
                .and().pathParam("id",101)
                .and().body(putMap).when().put(URL_Base+"/api/spartans/{id}")
                 .then().assertThat().statusCode(204);
    }

    @Test
    public void PATChRequest(){
        //Different ways to send json body
        //-String
        //-Using collection(Map)
        //-POJO

        //Using Map
        Map<String,Object>patchMap=new HashMap<>();
        patchMap.put("name","Alex");


        //we gonna send request body with updated value, and content type header
        given().contentType(ContentType.JSON)
                .and().pathParam("id",101)
                .and().body(patchMap).when().patch(URL_Base+"/api/spartans/{id}")
                .then().assertThat().statusCode(204);
    }
}
