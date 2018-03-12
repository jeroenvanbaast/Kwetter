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
        User user = new User();
        Profile profile = new Profile();
        profile.setName("coolenaam");
        Kwet kwet = new Kwet("test");
        Kwet kwet2 = new Kwet("test2");
        HashTag hashTag = new HashTag();
        kwet.getHashTags().add(hashTag);
        profile.placeKwet(kwet);
        profile.placeKwet(kwet2);
        user.setProfile(profile);
        userService.create(user);
        hashTagService.create(hashTag);
        profileService.create(profile);
        kwetService.create(kwet);
        kwetService.create(kwet2);
        List<Kwet> test = kwetService.findByMessage("test");
    }
}
