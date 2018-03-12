/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.KwetDao;
import domain.Kwet;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Jeroen
 */
@Stateless
public class KwetService extends KwetDao{
    
    
        public ArrayList<Kwet> getAll() {
        Query query = this.entityManager.createQuery("SELECT k FROM Kwet k");
        return  new ArrayList<>(query.getResultList());
    }
}
