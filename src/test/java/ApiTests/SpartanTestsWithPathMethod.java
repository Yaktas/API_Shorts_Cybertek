package ApiTests;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.List;

public class SpartanTestsWithPathMethod {
    String URL_Base="http://54.160.71.107:8000";

    /*
    Given accept type is json
    And path param id is 10
    When users sends a get request to "/api/spartans/{id}"
    Then status code is 200
    And content-type is "application/json;charset=UTF-8"
    And response payload values match the following:

        id is 10,
        name is "Lorenza",
        gender is "Female",
        phone is 3312820936
     */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 10).
                when().get(URL_Base+"/api/spartans/{id}");

        //verify status code
        assertEquals(response.statusCode(),200);

        //verify content-type
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        //printing values of json keys
        System.out.println("Id:"+response.body().path("id").toString());
        System.out.println("Name:"+response.body().path("name").toString());
        System.out.println("gender:"+response.path("gender").toString());
        System.out.println("phone:"+response.path("phone").toString());

        int id=response.path("id");
        String name=response.body().path("name");
        String gender=response.body().path("gender");
        long phone=response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //verify json keys and values
        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone,3312820936l);
    }

    @Test
    public void test2(){
        Response response = get(URL_Base + "/api/spartans");

        //extracy first id
        int firstId=response.path("id[0]");
        System.out.println("firstId = " + firstId);

        //extracy name
        String first1stName=response.path("name[0]");
        System.out.println("first1stName = " + first1stName);

        //get the last firstName
        String last1stName=response.path("name[-1]");
        System.out.println("last1stName = " + last1stName);

        //extract all firstNames and print them
        List<String>names=response.path("name");
        System.out.println(names);
        System.out.println("names.size() = " + names.size());

        List<Object>phoneNumbers=response.path("phone");

        for ( Object phoneNumber: phoneNumbers) {
            System.out.println(phoneNumber);
        }
    }
}
