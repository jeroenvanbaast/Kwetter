/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProfileDao;
import domain.Profile;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Jeroen
 */
@Stateless
public class ProfileService extends ProfileDao {

    public ArrayList<Profile> getAll() {
        Query query = this.entityManager.createQuery("SELECT p FROM Profile p");
        return new ArrayList<>(query.getResultList());
    }
}
