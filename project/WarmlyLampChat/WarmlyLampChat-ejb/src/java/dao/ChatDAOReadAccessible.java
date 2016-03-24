package dao;

import java.util.List;
import models.chat.Room;

public interface ChatDAOReadAccessible {
    List<Room> getAllRooms();
    Room getRoomById(Integer id);
}
