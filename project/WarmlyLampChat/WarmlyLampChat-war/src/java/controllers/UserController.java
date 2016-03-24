package controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    private String message;

    public ChatUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(ChatUser currentUser) {
        this.currentUser = currentUser;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Room> getAllRooms() {
        return chatService.getChatDAO().getAllRooms();
    }
    
    public String createRoom() {
        this.room = new Room();
        return "create_room";
    }

    public String createRoomConfirm() {
        //?
        return "index";
    }

    public String enterToRoom(int idRoom) {
        this.currentRoom = chatService.getChatDAO().getAllRooms().get(0);
        return "room";
    }

    public String leaveRoom() {
        this.currentRoom = null;
        return "index";
    }
    
    public void sendMessage() {
        Message newMessage = new Message();
        newMessage.setContent(message);
        newMessage.setSender(currentUser);
        newMessage.setSendDate(new Date());
        chatService.sendMessage(room, newMessage);
        message = "";
    }
}