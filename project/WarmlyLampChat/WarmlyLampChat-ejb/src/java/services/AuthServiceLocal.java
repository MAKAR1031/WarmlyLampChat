package services;

import java.util.List;
import javax.ejb.Local;
import models.chat.ChatUser;
import models.chat.UserRole;

@Local
public interface AuthServiceLocal {
    List<UserRole> getAllRoles();
    ChatUser getCurrentUser();
    void registerUser(String fio, String nickName, String password, String roleName);
}
