package services.impl;

import dao.ChatDAOLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.chat.ChatUser;
import models.chat.Message;
import models.chat.Room;
import services.ChatServiceLocal;

@Stateless
public class ChatService implements ChatServiceLocal {
    @EJB
    private ChatDAOLocal chatDAO;

    @Override
    public void enterToRoom(Room room, ChatUser user) {
        
    }

    @Override
    public void leaveRoom(Room room, ChatUser user) {
        
    }

    @Override
    public void sendMessage(Room room, Message message) {
        
    }

    @Override
    public List<Room> getAllRooms() {
        return chatDAO.getAllRooms();
    }
}