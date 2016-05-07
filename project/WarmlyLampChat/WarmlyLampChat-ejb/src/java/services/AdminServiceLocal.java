package services;

import java.util.List;
import javax.ejb.Local;
import models.chat.ChatUser;

@Local
public interface AdminServiceLocal {
    void  addUser(ChatUser user, String role);
    ChatUser getUserById(int id);
    List<ChatUser> getAllUsers();
    void mergeUser(ChatUser user, String role);
    void removeUser(ChatUser user);
}
