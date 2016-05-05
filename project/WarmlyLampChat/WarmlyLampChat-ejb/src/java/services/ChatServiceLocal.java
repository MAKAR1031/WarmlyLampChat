package services;

import java.util.List;
import javax.ejb.Local;
import models.chat.ChatUser;
import models.chat.Room;

@Local
public interface ChatServiceLocal {
    List<Room> getAllRooms();
    void createRoom(Room room);
    void enterToRoom(int idRoom, int idUser);
    void leaveRoom(int idRoom, int idUser);
    void sendMessage(Room room, int idSender, String message);
    ChatUser getUserById(int id);
    Room getRoomById(int id);
}
