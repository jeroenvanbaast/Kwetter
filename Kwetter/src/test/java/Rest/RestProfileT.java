/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import domain.Kwet;
import domain.Profile;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import static org.hamcrest.core.IsEqual.equalTo;
import static io.restassured.RestAssured.post;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;

/**
 *
 * @author Jeroen
 */
@FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class RestProfileT
{

    public RestProfileT()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void canPutProfile()
    {
        io.restassured.RestAssured.put("http://localhost:8080/Kwetter/api/profiles?name=testNaam&bio=testBio");
        RestAssured.get("http://localhost:8080/Kwetter/api/profiles/1")
                .then()
                .assertThat().
                body("name", equalTo("testNaam"));
    }

    @Test
    public void canUpdateProfile()
    {
        post("http://localhost:8080/Kwetter/api/profiles/1?name=updateTest&bio=testBio&locatie=locatie&website=website");
        RestAssured.get("http://localhost:8080/Kwetter/api/profiles/1")
                .then()
                .assertThat().
                body("name", equalTo("updateTest"));
    }

    @Test
    public void follow()
    {
        io.restassured.RestAssured.put("http://localhost:8080/Kwetter/api/profiles?name=test2&bio=test2");
        post("http://localhost:8080/Kwetter/api/profiles/1/follow/2");
        JsonPath jsonPath = get("http://localhost:8080/Kwetter/api/profiles/1").jsonPath();
        List<Profile> profiles = jsonPath.getList("", Profile.class);
        assertEquals(profiles.get(0).getFollowing().size(), 1);
        delete("http://localhost:8080/Kwetter/api/profiles/1/follow/2");
        JsonPath jsonPath2 = get("http://localhost:8080/Kwetter/api/profiles/1").jsonPath();
        List<Profile> profiles2 = jsonPath2.getList("", Profile.class);
        assertEquals(profiles2.get(0).getFollowing().size(), 1);
    }

    @Test
    public void heart()
    {
        io.restassured.RestAssured.put("http://localhost:8080/Kwetter/api/kwets?message=testMessage");
        post("http://localhost:8080/Kwetter/api/profiles/1/heart/1");

        JsonPath jsonPath = get("http://localhost:8080/Kwetter/api/profiles/1").jsonPath();
        List<Profile> profiles = jsonPath.getList("", Profile.class);
        assertEquals(profiles.get(0).getHeartedKwets().size(), 1);
        delete("http://localhost:8080/Kwetter/api/profiles/1/heart/1");
        JsonPath jsonPath2 = get("http://localhost:8080/Kwetter/api/profiles/1").jsonPath();
        List<Profile> profiles2 = jsonPath2.getList("", Profile.class);
        assertEquals(profiles2.get(0).getHeartedKwets().size(), 0);
        delete("http://localhost:8080/Kwetter/api/kwets/1");
    }
    
    @Test
    public void delteProfile()
    {
        delete("http://localhost:8080/Kwetter/api/profiles/1");
        given().when().get("http://localhost:8080/Kwetter/api/profiles/1")
                .then().statusCode(204);
    }
}
