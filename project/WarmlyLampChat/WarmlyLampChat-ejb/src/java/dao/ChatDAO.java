package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import models.chat.ChatUser;
import models.chat.Message;
import models.chat.Room;
import models.chat.UserRole;

@LocalBean
@Stateless
public class ChatDAO {

    @PersistenceContext(unitName = "WarmlyLampChat-ejbPUChat")
    private EntityManager em;

    public List<Room> getAllRooms() {
        Query query = em.createQuery("SELECT r FROM Room r", Room.class);
        return query.getResultList();
    }

    public Room getRoomById(Integer id) {
        return em.find(Room.class, id);
    }

    public void addRoom(Room room) {
        em.persist(room);
    }

    public void mergeRoom(Room room) {
        em.merge(room);
    }

    public void removeRoom(Room room) {
        em.remove(em.merge(room));
    }

    public List<ChatUser> getAllUsers() {
        Query query = em.createQuery("SELECT u FROM ChatUser u", ChatUser.class);
        return query.getResultList();
    }

    public ChatUser getUserById(Integer id) {
        return em.find(ChatUser.class, id);
    }

    public ChatUser getUserByNickName(String nick) {
        try {
            Query query = em.createQuery("SELECT u FROM ChatUser u "
                    + "WHERE u.nickName=?1",
                    ChatUser.class);
            query.setParameter(1, nick);
            return (ChatUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void addUser(ChatUser user) {
        em.persist(user);
    }

    public void mergeUser(ChatUser user) {
        em.merge(user);
    }

    public void removeUser(ChatUser user) {
        em.remove(em.merge(user));
    }

    public UserRole getRoleByName(String roleName) {
        Query query = em.createQuery("SELECT r FROM UserRole r WHERE r.name=?1",
                UserRole.class);
        query.setParameter(1, roleName);
        return (UserRole) query.getSingleResult();
    }

    public List<UserRole> getAllRoles() {
        Query query = em.createQuery("SELECT r FROM UserRole r",
                UserRole.class);
        return query.getResultList();
    }

    public void addMesage(Message message) {
        em.persist(message);
    }

    public List<Message> getMessagesByRoom(Room room) {
        Query query = em.createQuery("SELECT m FROM Message m WHERE m.room=?1",
                Message.class);
        query.setParameter(1, room);
        query.setMaxResults(100);
        return query.getResultList();
    }

    public void removeMessage(Message message) {
        em.remove(em.merge(message));
    }
}
