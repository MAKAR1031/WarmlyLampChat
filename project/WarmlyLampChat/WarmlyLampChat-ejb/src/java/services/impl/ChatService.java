package services.impl;

import dao.ChatDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.chat.ChatUser;
import models.chat.Message;
import models.chat.Room;
import services.ChatServiceLocal;

@Stateless
public class ChatService implements ChatServiceLocal {

    @EJB
    private ChatDAO chatDAO;

    @Override
    public ChatDAO getChatDAO() {
        return chatDAO;
    }
    
    @Override
    public void enterToRoom(Room room, ChatUser user) {
        
    }

    @Override
    public void leaveRoom(Room room, ChatUser user) {
        
    }

    @Override
    public void sendMessage(Room room, Message message) {
        
    }
}