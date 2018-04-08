/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeroen
 */
@Entity
@XmlRootElement
public class AccountType implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Right> Rights;

    public AccountType() {
        this.Rights = new ArrayList();
    }

    public AccountType(String name) {
        this();
        this.name = name;        
    }

    // <editor-fold defaultstate="collapsed" desc="getters en setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Right> getRights() {
        return Rights;
    }

    public void setRights(List<Right> Rights) {
        this.Rights = Rights;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    // </editor-fold> 
}
