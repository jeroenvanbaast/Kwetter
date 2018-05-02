/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Kwet;
import domain.Profile;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jeroen
 */
public abstract class KwetDao extends AbstractDao<Kwet> {

    public List<Kwet> findByMessage(String message) {
        return entityManager.createNamedQuery("kwet.findByMessage", Kwet.class)
                .setParameter("message", message).getResultList();

    }

    public List<Kwet> getAllFlagged() {
        return entityManager.createNamedQuery("kwet.getAllFlagged", Kwet.class)
                .getResultList();

    }
    
    @Override
    public Kwet create(Kwet kwet){
        kwet.setPlacedDate(new Date());
        return super.create(kwet);
    }

}
