/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeroen
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "kwet.findByMessage", query = "SELECT k FROM Kwet k WHERE k.message = :message")
    ,
@NamedQuery(name = "kwet.getAllFlagged", query = "SELECT k FROM Kwet k WHERE k.flagged = TRUE")
})

@XmlRootElement
public class Kwet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String profileName;
    private Date placedDate;
    private String message;
    private boolean flagged;
    @ManyToMany()
    private List<HashTag> hashTags;
    @ManyToMany()
    private List<Profile> tagged;
    private int likes;

    public Kwet() {
        hashTags = new ArrayList();
        tagged = new ArrayList();
        this.setPlacedDate(new Date());
    }

    public Kwet(String message, Profile profile) {
        this();
        this.message = message;
        this.profileName = profile.getName();
        hashTags = new ArrayList();
        tagged = new ArrayList();
    }

    public void Like() {
        this.likes = likes + 1;
    }

    public void UnLike() {
        this.likes = likes - 1;
    }

    public JsonObject toJson(URI self) {               
        return Json.createObjectBuilder()
                .add("profileName", this.profileName)
                .add("placedDate", this.placedDate.toString())
                .add("message", this.message)
                .add("flagged", this.flagged)
                .add("tagged", fillTagged())
                .add("hashTags", fillHashTag())                
                .add("likes", this.likes)
                .add("_links", Json.createObjectBuilder()
                        .add("rel", "self")
                        .add("href", self.toString()))
                .build();
    }
    
     public JsonObject toJson() {               
        return Json.createObjectBuilder()
                .add("profileName", this.profileName)
                .add("placedDate", this.placedDate.toString())
                .add("message", this.message)
                .add("flagged", this.flagged)
                .add("tagged", fillTagged())
                .add("hashTags", fillHashTag())                
                .add("likes", this.likes)
                .build();
    }
    
    
    public JsonArrayBuilder fillTagged(){
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for(Profile profile : tagged){
        jsonArrayBuilder.add(profile.toJson());
        }
        return jsonArrayBuilder;
    }
    
      public JsonArrayBuilder fillHashTag(){
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for(HashTag hashtag : hashTags){
        jsonArrayBuilder.add(hashtag.toJson());
        }
        return jsonArrayBuilder;
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

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
    // </editor-fold>
}
