///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package dao;
//
//import domain.*;
//import java.io.File;
//import javax.inject.Inject;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.container.test.api.RunAsClient;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.Archive;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.jboss.shrinkwrap.resolver.api.maven.Maven;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import service.KwetService;
//
///**
// *
// * @author Jeroen
// */
//@RunWith(Arquillian.class)
//public class AbstractDaoTest
//{
//
//    @Deployment
//    public static Archive<?> createDeployment()
//    {
//        return ShrinkWrap.create(WebArchive.class)
//                .addPackage("target/classes")
//                .addAsResource("persistence.xml", "META-INF/persistence.xml")
//                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
//
//    @Inject
//    private KwetService service;
//
//    public AbstractDaoTest()
//    {
//    }
//
//    @BeforeClass
//    public static void setUpClass()
//    {
//    }
//
//    @AfterClass
//    public static void tearDownClass()
//    {
//    }
//
//    @Before
//    public void setUp()
//    {
//    }
//
//    @After
//    public void tearDown()
//    {
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
//    @Test
//    public void saveKwet()
//    {
//        Kwet kwet = new Kwet("test");
//        kwet = service.create(kwet);
//        Kwet testKwet = (Kwet) service.getById(kwet.getId());
//        System.out.println(testKwet.getMessage());
//        assertEquals(kwet.getMessage().equalsIgnoreCase(testKwet.getMessage()), true);
//    }
//}
