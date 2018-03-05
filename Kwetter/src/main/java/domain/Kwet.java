/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Jeroen
 */
@Entity
public class Kwet implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String message;
    @ManyToMany
    private List<HashTag> hashTags;
    @ManyToMany
    private List<Profile> tagged;

    public Kwet(){
    
    }
    
    public Kwet(String message){
        this.message = message;
    }
    
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

    public void setId(long id) {
        this.id = id;
    }
    
    
}
