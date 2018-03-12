/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import domain.Kwet;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
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
import org.junit.Test;
import static org.junit.Assert.*;

import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Assert;

/**
 *
 * @author Jeroen
 */
public class restKwetTest {
    
    public restKwetTest() {
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
    public void canGetKwets() {
        expect().
                body("get(0).id", equalTo(5)).
                when().
                get("http://localhost:8080/Kwetter/api/kwets");
    }
    
    @Test
    public void canGetKwetsSize(){
    	RestAssured.baseURI = "http://localhost:8080/Kwetter/api/kwets";
	RequestSpecification httpRequest = RestAssured.given();
	Response response = httpRequest.get("");
        
        JsonPath jsaonPathEvaluator = response.jsonPath();
        List<Kwet> kwets = jsaonPathEvaluator.getList("kwets", Kwet.class);
        assertEquals(kwets.size(), 2);
    }

    @Test
    public void canGetKwetById() {
        RestAssured.get("http://localhost:8080/Kwetter/api/kwets/5")
                .then()
                .assertThat().
                body("id", equalTo(5));
    }
}
