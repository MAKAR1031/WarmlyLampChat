package services.impl;

import dao.ChatDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.chat.ChatUser;
import models.chat.UserRole;
import services.AdminServiceLocal;

@Stateless
public class AdminService implements AdminServiceLocal {

    @EJB
    private ChatDAO chatDAO;

    @Override
    public void addUser(ChatUser user, String role) {
        UserRole userRole = chatDAO.getRoleByName(role);
        user.setRole(userRole);
        chatDAO.addUser(user);
    }

    @Override
    public ChatUser getUserById(int id) {
        return chatDAO.getUserById(id);
    }

    @Override
    public List<ChatUser> getAllUsers() {
        return chatDAO.getAllUsers();
    }

    @Override
    public void mergeUser(ChatUser user, String role) {
        UserRole userRole = chatDAO.getRoleByName(role);
        user.setRole(userRole);
        chatDAO.mergeUser(user);
    }

    @Override
    public void removeUser(ChatUser user) {
        chatDAO.removeUser(user);
    }
}
