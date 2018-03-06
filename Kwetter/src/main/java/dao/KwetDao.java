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
    
     public List<Kwet> findByMessage(String message){
     return entityManager.createNamedQuery("kwet.findByMessage", Kwet.class)
             .setParameter("message", message).getResultList();
        
     }
     
//      public List<Kwet> findByPoster(String message){
//     return entityManager.createNamedQuery("SELECT k FROM Kwet k WHERE k.message = :message", Kwet.class)
//             .setParameter("message", message).getResultList();
//        
//     }
    
}
