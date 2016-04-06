package dao;

import java.util.List;
import javax.ejb.Local;
import models.chat.ChatUser;
import models.chat.Room;

@Local
public interface ChatDAOLocal {
    List<Room> getAllRooms();
    Room getRoomById(Integer id);
    public void createRoom(Room room);
    public void mergeRoom(Room room);
    public void removeRoom(Room room);
    public List<ChatUser> getAllUsers();
    public ChatUser getUserById(Integer id);
    public void mergeUser(ChatUser user);
    public void removeUser(ChatUser user);
}
