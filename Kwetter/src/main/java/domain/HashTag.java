/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeroen
 */
@Entity
@XmlRootElement
public class HashTag implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    private String hashTagText;
    @ManyToMany(mappedBy = "hashTags")
    private List<Kwet> kwets;
    
    public HashTag() {

    }

    public HashTag(String hashTagText) {
        this.hashTagText = hashTagText;
    }

    // <editor-fold defaultstate="collapsed" desc="getters en setters">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHashTagText() {
        return hashTagText;
    }

    public void setHashTagText(String hashTagText) {
        this.hashTagText = hashTagText;
    }
    
    // </editor-fold>
}
