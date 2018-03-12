/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import domain.Kwet;
import domain.Profile;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.expect;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeroen
 */
public class restProfileTest {

    public restProfileTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void canGetProfiles() {
        expect().
                body("get(0).id", equalTo(2)).
                when().
                get("http://localhost:8080/Kwetter/api/profiles");
    }

    @Test
    public void canGetProfilesSize() {
        RestAssured.baseURI = "http://localhost:8080/Kwetter/api/profiles";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");

        JsonPath jsaonPathEvaluator = response.jsonPath();
        List<Profile> profiles = jsaonPathEvaluator.getList("profiles", Profile.class);
        assertEquals(profiles.size(), 1);
    }

    @Test
    public void canGetProfilesById() {
        RestAssured.get("http://localhost:8080/Kwetter/api/profiles/1")
                .then()
                .assertThat().
                body("id", equalTo(2));
    }
}
