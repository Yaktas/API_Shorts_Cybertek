package ApiTests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeClass;

public class SpartanTestsWithPathParameters {

//@BeforeClass
//        public void setUpClass(){
    String URL_Base="http://54.160.71.107:8000";
//}


    /*
    Given accept type is Json
    And Id parameter value is 18
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content-type:application/json;charset=UTF-8
    And "Allen" should be in response payload
     */
@Test
    public void pathTest1(){
    Response response = given().
            accept(ContentType.JSON).
            and().
            pathParam("id", 18).
            when().
            get(URL_Base+"/api/spartans/{id}");
    //verify status code
    assertEquals(response.statusCode(),200);

    //verify content type
    assertEquals(response.contentType(),"application/json;charset=UTF-8");

    //verify "Allen" exists
    assertTrue(response.body().asString().contains("Allen"));

    response.body().prettyPeek();
}
    /*
    Given accept type is Json
    And Id parameter value is 500
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 404
    And response content-type:application/json;charset=UTF-8
    And "Spartan Not Found" message should be in response payload
     */
    @Test
    public void pathTest2(){
        Response response = given().
                accept(ContentType.JSON).
                and().
                pathParam("id", 500).
                when().
                get(URL_Base+"/api/spartans/{id}");
        //verify status code
        assertEquals(response.statusCode(),404);

        //verify content type
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        //verify "Allen" exists
        assertTrue(response.body().asString().contains("Spartan Not Found"));

        response.body().prettyPeek();

    }
}
