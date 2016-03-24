package dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import models.chat.ChatUser;
import models.chat.Room;

@Stateless
@LocalBean
@Local(ChatDAOReadAccessible.class)
public class ChatDAO implements ChatDAOReadAccessible {

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        Room room = new Room();
        room.setId(1);
        room.setName("Тестовая комната");
        rooms.add(room);
        return rooms;
    }

    @Override
    public Room getRoomById(Integer id) {
        return new Room();
    }
    
    public void createRoom(Room room) {
        
    }
    
    public void updateRoom(Room room) {
        
    }
    
    public void removeRoom(Room room) {
        
    }
    
    public List<ChatUser> getAllUsers() {
        return new ArrayList<>();
    }
    
    public ChatUser getUserById(Integer id) {
        return new ChatUser();
    }
    
    public void createUser(ChatUser user) {
        
    }
    
    public void updateUser(ChatUser user) {
        
    }
    
    public void removeUser(ChatUser user) {
        
    }
}
