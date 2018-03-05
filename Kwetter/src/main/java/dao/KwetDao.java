/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Kwet;
import domain.Profile;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jeroen
 */
@Stateless
public class KwetDao extends AbstractDao<Kwet>{
    
     public List<Kwet> findByPoster(Profile poster){
     return entityManager.createNamedQuery("Select k FROM kwet k WHERE k.profile = :poster", Kwet.class)
             .setParameter("poster", poster).getResultList();
        
     }
    
}
