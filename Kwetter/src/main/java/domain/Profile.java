/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeroen
 */
@Entity

@XmlRootElement
public class Profile implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private String name;
    private Boolean publicName;
    private File profilePicture;
    private Boolean publicProfilePicture;
    private String bio;
    private Boolean publicBio;
    private String locatie;
    private Boolean publicLocatie;
    private String website;
    private Boolean publicWebsite;
    
    @OneToMany
    private List<Kwet> kwets;
    @ManyToMany(mappedBy = "tagged")
    private List<Kwet> heartedKwets;
    @OneToMany
    private List<Profile> following;
    
    public Profile() {
        this.kwets = new ArrayList();
        this.heartedKwets = new ArrayList();
        this.following = new ArrayList();
    }

    public Kwet placeKwet(Kwet kwet) {
        this.kwets.add(kwet);
        return kwet;
    }
    
    public void follow(Profile profile){
        this.following.add(profile);
    }
    
    public void unFollow(Profile profile){
        this.following.remove(profile);
    }
    
    public void giveHearth(Kwet kwet){
        this.heartedKwets.add(kwet);
    }

    public void unGiveHearth(Kwet kwet){
        this.heartedKwets.remove(kwet);
    }
    
    public void removeKwet(Kwet kwet){
        this.kwets.remove(kwet);
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters en setters">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(File profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Kwet> getKwets() {
        return kwets;
    }

    public void setKwets(List<Kwet> kwets) {
        this.kwets = kwets;
    }

    public List<Kwet> getHeartedKwets() {
        return heartedKwets;
    }

    public void setHeartedKwets(List<Kwet> heartedKwets) {
        this.heartedKwets = heartedKwets;
    }

    public List<Profile> getFollowing() {
        return following;
    }

    public void setFollowing(List<Profile> following) {
        this.following = following;
    }

    public Boolean getPublicName() {
        return publicName;
    }

    public void setPublicName(Boolean publicName) {
        this.publicName = publicName;
    }

    public Boolean getPublicProfilePicture() {
        return publicProfilePicture;
    }

    public void setPublicProfilePicture(Boolean publicProfilePicture) {
        this.publicProfilePicture = publicProfilePicture;
    }

    public Boolean getPublicBio() {
        return publicBio;
    }

    public void setPublicBio(Boolean publicBio) {
        this.publicBio = publicBio;
    }

    public Boolean getPublicLocatie() {
        return publicLocatie;
    }

    public void setPublicLocatie(Boolean publicLocatie) {
        this.publicLocatie = publicLocatie;
    }

    public Boolean getPublicWebsite() {
        return publicWebsite;
    }

    public void setPublicWebsite(Boolean publicWebsite) {
        this.publicWebsite = publicWebsite;
    }
    // </editor-fold>

}
