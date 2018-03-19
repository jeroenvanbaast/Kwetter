/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Jeroen
 */
@Singleton
@Startup
public class StartUp {

    @Inject
    private KwetService kwetService;
    @Inject
    private ProfileService profileService;
    @Inject
    private UserService userService;
    @Inject
    private HashTagService hashTagService;
    
    public StartUp() {
    }

    @PostConstruct
    public void test() {
  
    }
}
