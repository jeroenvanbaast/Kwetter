/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import domain.Kwet;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import org.junit.After;
import org.junit.AfterClass;
import io.restassured.path.json.JsonPath;
import org.junit.Before;
import org.junit.BeforeClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.ArrayList;
import java.util.List;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.FixMethodOrder;

/**
 *
 * @author Jeroen
 */
@FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class RestKwetT {
    
    public RestKwetT() {
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
    public void canPutProfile() {
        io.restassured.RestAssured.put("http://localhost:8080/Kwetter/api/kwets?message=testMessage");
         RestAssured.get("http://localhost:8080/Kwetter/api/kwets/1")
                .then()
                .assertThat().
                body("message", equalTo("testMessage"));
    }

    @Test
    public void canUpdateProfile() {
        post("http://localhost:8080/Kwetter/api/kwets/1?message=updateTest");
         RestAssured.get("http://localhost:8080/Kwetter/api/kwets/1")
                .then()
                .assertThat().
                body("message", equalTo("updateTest"));
    }

    @Test
    public void delteProfile() {
        delete("http://localhost:8080/Kwetter/api/kwets/1");
    }


   
}
