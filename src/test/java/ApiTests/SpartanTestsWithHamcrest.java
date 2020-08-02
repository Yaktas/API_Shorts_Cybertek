package ApiTests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class SpartanTestsWithHamcrest {
    String URL_Base="http://54.160.71.107:8000";

    /*
    Given accept type is JSON
    And path param id is 15
    When user sends a get request to /api/spartans/{id}
    Then status code is 200
    And content type is JSON
    And json data has following
            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106
     */
    @Test
    public void test1(){
        //request
       given().
                accept(ContentType.JSON).and().pathParam("id",15).
       when().
               get(URL_Base+"/api/spartans/{id}").
               //response
        then().statusCode(200).and().assertThat().contentType("application/json;charset=UTF-8");

    }

    @Test
    public void test2(){
        given().accept(ContentType.JSON).pathParam("id",15).
                when().get(URL_Base+"/api/spartans/{id}").
                then().assertThat().statusCode(200).
                and().assertThat().contentType("application/json;charset=UTF-8").
                and().assertThat().body("id", equalTo(15),"name",
                equalTo("Meta"),"gender",equalTo("Female"),"phone",equalTo(1938695106));
    }
}
