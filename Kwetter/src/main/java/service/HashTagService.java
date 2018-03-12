/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.HashTagDao;
import domain.HashTag;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Jeroen
 */
@Stateless
public class HashTagService extends HashTagDao {

    public ArrayList<HashTag> getAll() {
        Query query = this.entityManager.createQuery("SELECT h FROM HashTag h");
        return new ArrayList<>(query.getResultList());
    }
}
