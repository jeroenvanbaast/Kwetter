/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Kwet;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Jeroen
 */
@Stateless
public class UserDao extends AbstractDao<User>{

      
     public User findByName(String userName){
     return  entityManager.createNamedQuery("user.findByUserName", User.class)
             .setParameter("userName", userName).getSingleResult();
        
     }   
}
