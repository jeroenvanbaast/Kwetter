/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;
import domain.User;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Jeroen
 */
@Stateless
public class UserService extends UserDao{

      public ArrayList<User> getAll() {
        Query query = this.entityManager.createQuery("SELECT u FROM User u");
        return new ArrayList<>(query.getResultList());
    }
}
