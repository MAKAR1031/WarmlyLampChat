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
    public Room enterToRoom(int idRoom, int idUser) {
        Room room = chatDAO.getRoomById(idRoom);
        ChatUser user = chatDAO.getUserById(idUser);
        room.getUsers().add(user);
        return room;
    }

    @Override
    public void leaveRoom(int idRoom, int idUser) {
        Room room = chatDAO.getRoomById(idRoom);
        ChatUser user = chatDAO.getUserById(idUser);
        room.getUsers().remove(user);
    }

    @Override
    public void sendMessage(Room room, Message message) {
        room.getMessages().add(message);
        chatDAO.mergeRoom(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return chatDAO.getAllRooms();
    }

    @Override
    public void createRoom(Room room) {
        chatDAO.createRoom(room);
    }

    @Override
    public ChatUser getUserById(int id) {
        return chatDAO.getUserById(id);
    }
    
    
}