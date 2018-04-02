/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import domain.Kwet;
import domain.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.KwetService;
import service.ProfileService;
import service.UserService;

/**
 *
 * @author jeroen
 */
@Named(value = "admin")
@ViewScoped
public class adminBean implements Serializable
{
    @Inject
    private UserService userService;
    @Inject
    private KwetService kwetService;
    @Inject
    private ProfileService profileService;
    
    @PostConstruct
    public void init(){
    
    }

    public List<User> getUserData(){
       return userService.getAll();
    }
    
    public List<Kwet> getKwetData(){
        return kwetService.getAllFlagged();
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters en setters">

      // </editor-fold>
    
}
