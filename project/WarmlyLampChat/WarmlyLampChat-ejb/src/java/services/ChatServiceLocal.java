package services;

import dao.ChatDAO;
import javax.ejb.Local;
import models.chat.ChatUser;
import models.chat.Message;
import models.chat.Room;

@Local
public interface ChatServiceLocal {
    ChatDAO getChatDAO();
    void enterToRoom(Room room, ChatUser user);
    void leaveRoom(Room room, ChatUser user);
    void sendMessage(Room room, Message message);
}
