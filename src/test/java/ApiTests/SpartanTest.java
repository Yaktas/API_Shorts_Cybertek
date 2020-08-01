package ApiTests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SpartanTest {

    String URL_Base="http://54.160.71.107:8000";
    @Test
    public void viewSpartanTest1(){
        Response response = get(URL_Base + "/api/spartans");
        //print the status code
        System.out.println(response.statusCode());

        //print body
        System.out.println(response.body().prettyPeek());
    }
    /*
    When user send GET request to /api/spartans end point
    Then status code must be 200
    And body should contains Allen
     */
    @Test
    public void viewSpartanTest2(){
      Response response = get(URL_Base + "/api/spartans");
      //verify status code 200
       assertEquals(response.statusCode(),200);

       //verify body contains Allen
        assertTrue(response.body().asString().contains("Allen"));
    }
    /*
    Given accept is Json
    When user sends a get request to spartanAllURL
    Then response status code is 200
    And response body should be json format
     */
    @Test
    public void viewSpartanTest3(){
        Response response = given().accept(ContentType.JSON).
                when().get(URL_Base + "/api/spartans");
        //verify status code
        assertEquals(response.statusCode(),200);

        //verify response body json format
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
    }
}
