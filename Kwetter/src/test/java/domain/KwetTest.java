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
public class KwetTest {

    public KwetTest() {
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
    public void testConnections() {
        Kwet kwet = new Kwet();
        HashTag hashTag = new HashTag("#test");
        kwet.setMessage("test");
        kwet.getHashTags().add(hashTag);        
        Profile profile1 = new Profile();
        Profile profile2 = new Profile();
        kwet.getTagged().add(profile2);
        profile1.placeKwet(kwet);
        assertEquals(kwet,profile1.getKwets().get(0));
        assertEquals(hashTag,profile1.getKwets().get(0).getHashTags().get(0));
        assertEquals(profile2,profile1.getKwets().get(0).getTagged().get(0));
    }
}
