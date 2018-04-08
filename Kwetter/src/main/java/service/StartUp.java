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
    @Inject
    private AccountTypeService accountTypeService;

    public StartUp() {
    }

    @PostConstruct
    public void test() {
        putDummyData();

    }

    public void putDummyData() {
        AccountType admin = new AccountType("admin");
        AccountType userType = new AccountType("gebruiker");
        this.accountTypeService.create(admin);
        this.accountTypeService.create(userType);
        User user = new User("root", "toor");
        Profile profile = new Profile("superadmin", "Super coole bio");
        user.setProfile(profile);

        Kwet kwet = new Kwet("Dit is een test kwet ****", profile);
        kwet.setFlagged(true);
        profile.placeKwet(kwet);
        user.setAccountType(admin);
        User henk = new User("Henk", "wachtwoord");
        user.setProfile(new Profile("Henk", "Henk zijn bio"));
        henk.setAccountType(userType);
        User jan = new User("Jan", "wachtwoord");
        jan.setProfile(new Profile("Jan", "Jantje"));
        this.userService.create(user);
        this.userService.create(henk);
        this.userService.create(jan);
    }

}
