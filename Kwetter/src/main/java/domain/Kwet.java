/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Jeroen
 */
@Entity
@NamedQueries({
@NamedQuery (name = "kwet.findByMessage", query = "SELECT k FROM Kwet k WHERE k.message = :message")})
public class Kwet implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    
    private Date placedDate;
    private String message;
    @ManyToMany
    private List<HashTag> hashTags;
    @ManyToMany
    private List<Profile> tagged;

    public Kwet(){
        hashTags = new ArrayList();
        tagged = new ArrayList();
    }
    
    public Kwet(String message){
        this.message = message;
        hashTags = new ArrayList();
        tagged = new ArrayList();
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters en setters">
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(List<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public List<Profile> getTagged() {
        return tagged;
    }

    public void setTagged(List<Profile> tagged) {
        this.tagged = tagged;
    }

    public long getId() {
        return id;
    }

    public Date getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(Date placedDate) {
        this.placedDate = placedDate;
    }

    public void setId(long id) {
        this.id = id;
    }
    // </editor-fold>
    
}
