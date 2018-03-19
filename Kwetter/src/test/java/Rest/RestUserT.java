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
import static io.restassured.RestAssured.put;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import service.UserService;

/**
 *
 * @author Jeroen
 */
@FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class RestUserT {

    public RestUserT() {

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
    public void canPutUser() {
        io.restassured.RestAssured.put("http://localhost:8080/Kwetter/api/users?userName=test&passwordHash=test");

      RestAssured.get("http://localhost:8080/Kwetter/api/users/1")
                .then()
                .assertThat().
                body("userName", equalTo("test"));
    }

    @Test
    public void canUpdateUser() {
        post("http://localhost:8080/Kwetter/api/users/1?userName=test2&passwordHash=test");
            RestAssured.get("http://localhost:8080/Kwetter/api/users/1")
                .then()
                .assertThat().
                body("userName", equalTo("test2"));
    }
    
    @Test
    public void deleteUser() {
        delete("http://localhost:8080/Kwetter/api/users/1");
        given().when().get("http://localhost:8080/Kwetter/api/users/1")
                .then().statusCode(204);
    }

}
