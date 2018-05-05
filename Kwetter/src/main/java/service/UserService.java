/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;
import domain.User;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.Query;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.inject.Inject;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Jeroen
 */
@Stateless
public class UserService extends UserDao {

    @Inject
    private ProfileService profileService;
    
    public ArrayList<User> getAll() {
        Query query = this.entityManager.createQuery("SELECT u FROM User u");
        return new ArrayList<>(query.getResultList());
    }

    public User login(String userName, String password){
        String passwordHash = encodeSHA256(password);
        return super.checkLogin(userName, passwordHash);
    }
    
    public User findByProfileName(String name){
       return super.findByProfile(profileService.getByName(name));
    }
    
    /**
     * Returns SHA-256 encoded string
     *
     * @param password - the string to be encoded
     * @return SHA-256 encoded string
     * @throws UnsupportedEncodingException if UTF-8 is not supported by the
     * system
     * @throws NoSuchAlgorithmException if SHA-256 is not supported by the
     * system
     */
    public static String encodeSHA256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            return DatatypeConverter.printBase64Binary(digest).toString();
        } catch (UnsupportedEncodingException ex) {
        } catch (NoSuchAlgorithmException ex) {
        }
        return "";
    }

}
