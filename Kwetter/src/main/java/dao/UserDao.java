/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Profile;
import domain.User;
import java.util.List;

/**
 *
 * @author Jeroen
 */
public abstract class UserDao extends AbstractDao<User> {

    public User findByName(String userName) {
        return entityManager.createNamedQuery("user.findByUserName", User.class)
                .setParameter("userName", userName).getSingleResult();
    }

    public User checkLogin(String userName, String password) {
        List<User> users =  entityManager.createNamedQuery("user.login", User.class)
                .setParameter("userName", userName)
                .setParameter("password", password).getResultList();
        if(users != null && users.size() != 0){
            return users.get(0);
        }
        return null;
    }
    
    public User findByProfile(Profile profile){
          return entityManager.createNamedQuery("user.findByProfile", User.class)
                .setParameter("profile", profile).getSingleResult();
    }
    
    public List<User> findFollowers(Profile profile){
        return entityManager.createNamedQuery("user.findFollowers", User.class)
                .setParameter("profile", profile).getResultList();
    }

}
