package controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import models.chat.ChatUser;
import models.chat.Message;
import models.chat.Room;
import services.ChatServiceLocal;

@Named
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private ChatServiceLocal chatService;

    private ChatUser currentUser;
    private Room currentRoom;
    private Room room;
    private String messageText;
    
    @PostConstruct
    private void onCreate() {
        currentUser = chatService.getUserById(1);
    }

    public ChatUser getCurrentUser() {
        return currentUser;
    }
    
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Room getRoom() {
        return room;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public List<Room> getAllRooms() {
        return chatService.getAllRooms();
    }
    
    public String createRoom() {
        this.room = new Room();
        return "create_room";
    }

    public String createRoomConfirm() {
        chatService.createRoom(room);
        room = null;
        return "index";
    }

    public String enterToRoom(int idRoom) {
        currentRoom = chatService.enterToRoom(idRoom, currentUser.getId());
        return "room";
    }

    public String leaveRoom() {
        chatService.leaveRoom(currentRoom.getId(), currentUser.getId());
        currentRoom = null;
        return "index";
    }
    
    public void sendMessage() {
        Message message = new Message();
        message.setContent(messageText);
        message.setSendDate(new Date());
        message.setSender(currentUser);
        chatService.sendMessage(currentRoom, message);
        messageText = "";
    }
}