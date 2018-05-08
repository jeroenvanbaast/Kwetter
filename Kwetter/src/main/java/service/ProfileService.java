/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProfileDao;
import domain.Profile;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author Jeroen
 */
@Stateless
public class ProfileService extends ProfileDao {

    @Inject
    private UserService userService;
    
    public ArrayList<Profile> getAll() {
        Query query = this.entityManager.createQuery("SELECT p FROM Profile p");
        return new ArrayList<>(query.getResultList());
    }
    
    public List<Profile> getProfilesFromUsers(List<User> users){
        ArrayList<Profile> profiles = new ArrayList();
        for(User user : users){
            profiles.add(user.getProfile());
        }
        return profiles;
    }
}
