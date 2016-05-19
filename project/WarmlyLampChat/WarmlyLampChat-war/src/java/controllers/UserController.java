package controllers;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import models.chat.ChatUser;
import models.chat.Room;
import services.AuthServiceLocal;
import services.ChatServiceLocal;

@Named
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private AuthServiceLocal authService;

    @EJB
    private ChatServiceLocal chatService;

    private Room currentRoom;
    private Room room;
    private String messageText;

    public ChatUser getCurrentUser() {
        return authService.getCurrentUser();
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

    public void updateRoom() {
        if (currentRoom != null) {
            currentRoom = chatService.getRoomById(currentRoom.getId());
        }
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
        chatService.enterToRoom(idRoom, authService.getCurrentUser().getId());
        currentRoom = chatService.getRoomById(idRoom);
        return "room";
    }

    public String leaveRoom() {
        chatService.leaveRoom(currentRoom.getId(), authService.getCurrentUser().getId());
        currentRoom = null;
        return "index";
    }

    public void sendMessage() {
        chatService.sendMessage(currentRoom, authService.getCurrentUser().getId(), messageText);
        currentRoom = chatService.getRoomById(currentRoom.getId());
        messageText = "";
    }
    
    public void removeRoom(int idRoom) {
        chatService.removeRoom(idRoom);
    }
}
