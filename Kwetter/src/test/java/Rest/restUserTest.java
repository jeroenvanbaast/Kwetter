/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import domain.User;
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
public class restUserTest {

    public restUserTest() {
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
    public void canGetUsers() {
        expect().
                body("get(0).id", equalTo(2)).
                when().
                get("http://localhost:8080/Kwetter/api/users");
    }

    @Test
    public void canGetUsersSize() {
        RestAssured.baseURI = "http://localhost:8080/Kwetter/api/users";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");

        JsonPath jsaonPathEvaluator = response.jsonPath();
        List<User> users = jsaonPathEvaluator.getList("users", User.class);
        int size = 0;
        if(users != null){
            size = users.size();
        }
        assertEquals(size, 1);
    }

    @Test
    public void canGetUsersById() {
        RestAssured.get("http://localhost:8080/Kwetter/api/users/1")
                .then()
                .assertThat().
                body("id", equalTo(2));
    }
}
