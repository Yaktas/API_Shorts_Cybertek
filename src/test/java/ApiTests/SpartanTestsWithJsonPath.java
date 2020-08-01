package ApiTests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SpartanTestsWithJsonPath {

    String URL_Base="http://54.160.71.107:8000";

    /*
    Given accept type is json
    And path param spartan id is 11
    When user sends a get request to /api/spartans/{id}
    Then status code is 200
    And content type is Json
    And "id": 11,
        "name": "Nona",
        "gender": "Female",
        "phone": 7959094216
     */
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 11).
                when().
                get(URL_Base+"/api/spartans/{id}");
        //verify status code
        assertEquals(response.statusCode(),200);
        int id=response.path("id");
        System.out.println("id = " + id);

        //How read value with JSONPATH?
        JsonPath jsonData=response.jsonPath();

        int id1=jsonData.getInt("id");
        String name=jsonData.getString("name");
        String gender=jsonData.getString("gender");
        long phone=jsonData.getLong("phone");

        System.out.println("id1 = " + id1);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(id1,11);
        assertEquals(name,"Nona");
        assertEquals(gender,"Female");
        assertEquals(phone, 7959094216l);

    }
}
