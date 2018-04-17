/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.KwetDao;
import domain.Kwet;
import domain.Profile;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author Jeroen
 */
@Stateless
public class KwetService extends KwetDao {

    @Inject
    private ProfileService profileSerivce;
    
    public ArrayList<Kwet> getAll() {
        Query query = this.entityManager.createQuery("SELECT k FROM Kwet k");
        return new ArrayList<>(query.getResultList());
    }
    
    @Override
    public void remove(Kwet kwet){
        Profile profile = profileSerivce.getById(kwet.getProfileId());
        profile.getKwets().remove(kwet);
        profileSerivce.update(profile);
        super.remove(kwet);
    }
}
