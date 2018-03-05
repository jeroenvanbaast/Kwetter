/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
public class ProfileTest {
    
    Profile profile;
    public ProfileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        profile = new Profile();
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
    public void testPlaceKwet(){
        Kwet kwet = new Kwet("test");
        profile.placeKwet(kwet);
        
        assertEquals(kwet,profile.getKwets().get(0));
    }
    
    @Test
    public void testFollow(){
        Profile profile2 = new Profile();
        profile.follow(profile2);
        assertEquals(profile2,profile.getFollowing().get(0));
    }
   
}
