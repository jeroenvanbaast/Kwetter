/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Profile;
import javax.ejb.Stateless;

/**
 *
 * @author Jeroen
 */
public abstract class ProfileDao extends AbstractDao<Profile>{

    public Profile getByName(String name){
                return entityManager.createNamedQuery("profile.findByName", Profile.class)
                .setParameter("name", name).getSingleResult();
    }
}
