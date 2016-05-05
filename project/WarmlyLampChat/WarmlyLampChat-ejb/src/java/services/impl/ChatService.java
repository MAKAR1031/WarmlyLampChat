package services.impl;

import dao.AdDAOLocal;
import dao.ChatDAOLocal;
import interceptors.MessageInterceptor;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import models.chat.ChatUser;
import models.chat.Message;
import models.chat.Mood;
import models.chat.Room;
import services.ChatServiceLocal;

@Stateless
public class ChatService implements ChatServiceLocal {
    @EJB
    private ChatDAOLocal chatDAO;
    
    @EJB
    private AdDAOLocal adDAO;

    @Override
    public void enterToRoom(int idRoom, int idUser) {
        Room room = chatDAO.getRoomById(idRoom);
        room.setMessages(chatDAO.getMessagesByRoom(room));
        ChatUser user = chatDAO.getUserById(idUser);
        if (!room.getUsers().contains(user)) {
            room.getUsers().add(user);
        }
    }

    @Override
    public void leaveRoom(int idRoom, int idUser) {
        Room room = chatDAO.getRoomById(idRoom);
        ChatUser user = chatDAO.getUserById(idUser);
        room.getUsers().remove(user);
    }

    @Override
    @Interceptors(MessageInterceptor.class)
    public void sendMessage(Room room, int idSender, String messageText) {
        Message message = new Message();
        message.setSender(chatDAO.getUserById(idSender));
        message.setSendDate(new Date());
        message.setRoom(room);
        message.setContent(messageText);
        chatDAO.addMesage(message);
    }

    @Override
    public List<Room> getAllRooms() {
        return chatDAO.getAllRooms();
    }

    @Override
    public void createRoom(Room room) {
        Mood mood = new Mood();
        mood.setEmotionalFactor(0);
        room.setCurrentMood(mood);
        chatDAO.createRoom(room);
    }

    @Override
    public ChatUser getUserById(int id) {
        return chatDAO.getUserById(id);
    }

    @Override
    public Room getRoomById(int id) {
        Room room = chatDAO.getRoomById(id);
        room.setMessages(chatDAO.getMessagesByRoom(room));
        room.setCurrentAdBlock(adDAO.getAdBlockByEmotionFactor(room.getCurrentMood().getEmotionalFactor()));
        return room;
    }
}