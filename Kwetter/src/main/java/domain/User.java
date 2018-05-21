/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import service.UserService;

/**
 *
 * @author Jeroen
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "user.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName")
    ,
    @NamedQuery(name = "user.login", query = "SELECT u FROM User u WHERE u.userName = :userName AND u.passwordHash = :password"),
    @NamedQuery(name = "user.findByProfile", query = "SELECT u FROM User u WHERE u.profile = :profile"),
    @NamedQuery(name = "user.findFollowers", query = "SELECT u FROM User u WHERE :profile member of u.following")
})
@Table(name = "users")
@XmlRootElement
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(unique=true)
    private String userName;
    @XmlTransient
    private String passwordHash;
    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Profile> following;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "rolename", referencedColumnName = "name")
    private AccountType accountType;
    
    private String token;

    public User() {
        this.token = "";
        this.following = new ArrayList();
        this.profile = new Profile();
    }

    public User(String userName, String password) {
        this();
        this.userName = userName;
        this.passwordHash = UserService.encodeSHA256(password);

    }

    // <editor-fold defaultstate="collapsed" desc="getters en setters">
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Profile> getFollowing() {
        return following;
    }

    public void setFollowing(List<Profile> following) {
        this.following = following;
    }
    
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = UserService.encodeSHA256(passwordHash);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    

// </editor-fold>
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return userName + " accountType=" + accountType;
    }
    
     public JsonObject toJson(URI self) {
        return Json.createObjectBuilder()
                .add("userName", this.userName)     
                .add("profile", this.profile.toJson())
                .add("following", fillFollowers())
                .add("token", this.token)
                .add("_links", Json.createObjectBuilder()
                        .add("rel", "self")
                        .add("href", self.toString()))
                .build();
    }
     
        public JsonArrayBuilder fillFollowers(){
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for(Profile profile : following){
        jsonArrayBuilder.add(profile.toJson());
        }
        return jsonArrayBuilder;
    }

}
