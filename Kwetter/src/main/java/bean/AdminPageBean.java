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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import service.AccountTypeService;
import service.KwetService;
import service.UserService;

/**
 *
 * @author Jeroen
 */
@Named
@SessionScoped
public class AdminPageBean implements Serializable {

    @Inject
    private UserService userService;
    @Inject
    private KwetService kwetService;
    @Inject
    private AccountTypeService accountTypeService;

    private User selectedUser;
    private List<AccountType> accountTypes;
    private List<Kwet> flaggedKwets;
    private List<User> users;
    private AccountType selectedAccountType;

    @PostConstruct
    public void init() {
        this.accountTypes = accountTypeService.getAll();
        this.flaggedKwets = kwetService.getAllFlagged();
        this.users = userService.getAll();
    }

    public void removeKwet(Kwet kwet) {
        kwetService.remove(kwet);
    }

    public void onChangeAccountType(ValueChangeEvent event) {
        System.out.println(event);
    }

    public void makeAdmin() {
        if (isSelectedUserValid()) {
            for (AccountType accType : this.accountTypes) {
                if (accType.getName().equalsIgnoreCase("admin")) {
                    this.selectedUser.setAccountType(accType);
                    this.userService.update(selectedUser);
                }
            }
        }
    }

    public void makeUser() {
        if (isSelectedUserValid()) {
            for (AccountType accType : this.accountTypes) {
                if (accType.getName().equalsIgnoreCase("users")) {
                    this.selectedUser.setAccountType(accType);
                    this.userService.update(selectedUser);
                }
            }
        }
    }

    public Boolean isSelectedUserValid() {
        if (this.selectedUser == null) {
            addMessage("Error", "Kies een gebruiker");
            return false;
        }
        return true;
    }

    public void addMessage(String title, String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(title,message));
    }

    public void onRowSelect(SelectEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg1').show();");
    }

    // <editor-fold defaultstate="collapsed" desc="getters en setters">
    public List<AccountType> getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(List<AccountType> accountTypes) {
        this.accountTypes = accountTypes;
    }

    public List<Kwet> getFlaggedKwets() {
        return flaggedKwets;
    }

    public void setFlaggedKwets(List<Kwet> flaggedKwets) {
        this.flaggedKwets = flaggedKwets;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    // </editor-fold>

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public AccountType getSelectedAccountType() {
        return selectedAccountType;
    }

    public void setSelectedAccountType(AccountType selectedAccountType) {
        this.selectedAccountType = selectedAccountType;
    }

}
