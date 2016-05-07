package controllers;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import models.chat.ChatUser;
import models.chat.UserRole;
import services.AdminServiceLocal;
import services.AuthServiceLocal;

@Named
@SessionScoped
public class AdminController implements Serializable {

    @EJB
    private AdminServiceLocal adminService;
    @EJB
    private AuthServiceLocal authService;

    private ChatUser user;
    private String role;

    public ChatUser getUser() {
        return user;
    }

    public void setUser(ChatUser user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ChatUser> getAllUsers() {
        return adminService.getAllUsers();
    }

    public List<UserRole> getAllRoles() {
        return authService.getAllRoles();
    }

    public String addUser() {
        user = new ChatUser();
        return "add_user";
    }

    public String addUserConfirm() {
        adminService.addUser(user, role);
        user = null;
        return "index";
    }

    public String updateUser(int id) {
        user = adminService.getUserById(id);
        role = user.getRole().getName();
        return "update_user";
    }

    public String updateUserConfirm() {
        adminService.mergeUser(user, role);
        user = null;
        return "index";
    }

    public String removeUser(int id) {
        user = adminService.getUserById(id);
        return "remove_user";
    }
    
    public String removeUserConfirm() {
        adminService.removeUser(user);
        user = null;
        return "index";
    }
}
