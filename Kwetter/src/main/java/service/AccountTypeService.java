/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.AccountTypeDao;
import domain.AccountType;
import domain.User;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Jeroen
 */
@Stateless
public class AccountTypeService extends AccountTypeDao{
    
    public ArrayList<AccountType> getAll()
    {
        Query query = this.entityManager.createQuery("SELECT a FROM AccountType a");
        return new ArrayList<>(query.getResultList());
    }
}
