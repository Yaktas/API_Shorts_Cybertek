package ApiTests;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanTestWithQueryParams {
    String URL_Base="http://54.160.71.107:8000";


    /*
    Given accept type is JSON
    And query parameter value are :
    gender| Female
    nameContains|J
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content-type:application/json;charset=UTF-8
    And "Female" should be in response payload
    And "Janette" should be in response payload
     */
    @Test
    public void QueryParam1(){
        Response response = given().accept(ContentType.JSON).
                and().queryParams("gender", "Female").
                and().queryParams("nameContains", "J").
                when().get(URL_Base + "/api/spartans/search");
        //verify status code
        assertEquals(response.statusCode(),200);
        //verify content type
        assertEquals(response.contentType(), "application/json;charset=UTF-8");
        //verify Female
        assertTrue(response.body().asString().contains("Female"));

        //verify Male not exists
        assertFalse(response.body().asString().contains("Male"));

        //verify Janette
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPeek();

    }
    @Test
    public void queryParams2(){
        //creating map for query Params

        Map<String, Object>paramsMap=new HashMap<>();
        paramsMap.put("gender","Female");
        paramsMap.put("nameContains","J");

        //send request
        Response response = given().accept(ContentType.JSON).
                and().queryParams(paramsMap).
                when().get(URL_Base+"/api/spartans/search");

        //verify status code
        assertEquals(response.statusCode(),200);
        //verify content type
        assertEquals(response.contentType(), "application/json;charset=UTF-8");
        //verify Female
        assertTrue(response.body().asString().contains("Female"));

        //verify Male not exists
        assertFalse(response.body().asString().contains("Male"));

        //verify Janette
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPeek();
    }
}
