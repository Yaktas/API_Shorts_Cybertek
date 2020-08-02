package ApiTests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class SpartanJsonToCollections {
    String URL_Base="http://54.160.71.107:8000";
    /*
    Given accept type is json
    And path param spartan id is 11
    When user sends a get request to /api/spartans/{id}
    Then status code is 200
    And Content type is Json
    And "id": 11,
       "name": "Nona"
       "gender": "Female",
       "phone": 7959094216
     */
@Test
    public void test1() {
    Response response = given().
            accept(ContentType.JSON).and().pathParam("id", 11).and().
            when().get(URL_Base + "/api/spartans/{id}");

    //convert Json response to Java Collections(Map)
    Map <String,Object> spartanMap = response.body().as(Map.class);

    System.out.println(spartanMap.get("name"));
    System.out.println(spartanMap.get("id"));

    //one example verification one side map/ expected value
    assertEquals(spartanMap.get("name"),"Nona");
}
@Test
    public void test2(){
    Response response = given().
            accept(ContentType.JSON).
            when().get(URL_Base+"/api/spartans");

    response.prettyPeek();
    //convert full json body to list of maps
    List <Map<String,Object>> listOfSpartans = response.body().as(List.class);

    //print all data of first spartan
    System.out.println(listOfSpartans.get(0));
    Map<String, Object> firstSpartan=listOfSpartans.get(0);
    System.out.println(firstSpartan.get("name"));

    int counter=1;
    for(Map<String, Object> map: listOfSpartans){
        System.out.println(counter+" - spartan "+map);
        counter++;
    }
}
}
