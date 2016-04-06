package services;

import java.util.List;
import javax.ejb.Local;
import models.chat.ChatUser;
import models.chat.Message;
import models.chat.Room;

@Local
public interface ChatServiceLocal {
    List<Room> getAllRooms();
    void enterToRoom(Room room, ChatUser user);
    void leaveRoom(Room room, ChatUser user);
    void sendMessage(Room room, Message message);
}
