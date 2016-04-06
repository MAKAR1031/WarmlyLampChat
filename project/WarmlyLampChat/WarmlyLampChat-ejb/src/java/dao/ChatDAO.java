package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import models.chat.ChatUser;
import models.chat.Room;

@Stateless
public class ChatDAO implements ChatDAOLocal {
    
    @PersistenceContext(unitName = "WarmlyLampChat-ejbPU-Chat")
    private EntityManager em;

    @Override
    public List<Room> getAllRooms() {
        Query query = em.createQuery("SELECT r FROM Room r", Room.class);
        return query.getResultList();
    }

    @Override
    public Room getRoomById(Integer id) {
        return em.find(Room.class, id);
    }

    @Override
    public void createRoom(Room room) {
        em.persist(room);
    }

    @Override
    public void mergeRoom(Room room) {
        em.merge(room);
    }

    @Override
    public void removeRoom(Room room) {
        em.remove(em.merge(room));
    }

    @Override
    public List<ChatUser> getAllUsers() {
        Query query = em.createQuery("SELECT u FROM ChatUser u", ChatUser.class);
        return query.getResultList();
    }

    @Override
    public ChatUser getUserById(Integer id) {
        return em.find(ChatUser.class, id);
    }

    @Override
    public void mergeUser(ChatUser user) {
        em.merge(user);
    }

    @Override
    public void removeUser(ChatUser user) {
        em.remove(em.merge(user));
    }
}
