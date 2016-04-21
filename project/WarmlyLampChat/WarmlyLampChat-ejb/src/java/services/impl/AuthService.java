package services.impl;

import dao.ChatDAOLocal;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import models.chat.ChatUser;
import models.chat.UserRole;
import services.AuthServiceLocal;

@Stateless
public class AuthService implements AuthServiceLocal {

    @EJB
    private ChatDAOLocal chatDAO;

    @Resource
    private SessionContext sc;

    @Override
    public List<UserRole> getAllRoles() {
        return chatDAO.getAllRoles();
    }
    
    @Override
    public ChatUser getCurrentUser() {
        return chatDAO.getUserByNickName(sc.getCallerPrincipal().getName());
    }

    @Override
    public void registerUser(String fio, String nickName, String password, String roleName) {
        UserRole role = chatDAO.getRoleByName(roleName);
        ChatUser user = new ChatUser();
        user.setFio(fio);
        user.setNickName(nickName);
        user.setPassword(password);
        user.setRole(role);
        chatDAO.createUser(user);
    }
}
