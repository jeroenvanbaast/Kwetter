/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import domain.AccountType;
import domain.Kwet;
import domain.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AccountTypeService;
import service.KwetService;
import service.UserService;

/**
 *
 * @author jeroen
 */
@Named(value = "adminBean")
@ViewScoped
public class AdminBean implements Serializable
{

    @Inject
    private UserService userService;
    @Inject
    private KwetService kwetService;
    @Inject
    private AccountTypeService accountTypeService;

    private List<AccountType> accountTypes;
    private List<Kwet> flaggedKwets;
    private List<User> users;
    
    public void init()
    {
        this.accountTypes = accountTypeService.getAll();
        this.flaggedKwets = kwetService.getAllFlagged();
        this.users = userService.getAll();
    }

    public void removeKwet()
    {

    }

    // <editor-fold defaultstate="collapsed" desc="getters en setters">
    public List<AccountType> getAccountTypes()
    {
        return accountTypes;
    }

    public void setAccountTypes(List<AccountType> accountTypes)
    {
        this.accountTypes = accountTypes;
    }

    public List<Kwet> getFlaggedKwets()
    {
        return flaggedKwets;
    }

    public void setFlaggedKwets(List<Kwet> flaggedKwets)
    {
        this.flaggedKwets = flaggedKwets;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }
    // </editor-fold>
}
