package dao;

import java.util.List;
import javax.ejb.Local;
import models.chat.ChatUser;
import models.chat.Room;
import models.chat.UserRole;

@Local
public interface ChatDAOLocal {
    List<Room> getAllRooms();
    Room getRoomById(Integer id);
    void createRoom(Room room);
    void mergeRoom(Room room);
    void removeRoom(Room room);
    List<ChatUser> getAllUsers();
    ChatUser getUserById(Integer id);
    ChatUser getUserByNickName(String nick);
    void createUser(ChatUser user);
    void mergeUser(ChatUser user);
    void removeUser(ChatUser user);
    UserRole getRoleByName(String roleName);
    List<UserRole> getAllRoles();
}
