package ApiTests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SpartansDELETE {

    String URL_Base="http://54.160.71.107:8000";

    @Test
    public void test1(){
        given().pathParam("id",104).
                when().delete(URL_Base+"/api/spartans/{id}")
        .then().assertThat().statusCode(204);

        //verify part
        given().pathParam("id",104).
                when().delete(URL_Base+"/api/spartans/{id}")
                .then().assertThat().statusCode(404);
    }
}
