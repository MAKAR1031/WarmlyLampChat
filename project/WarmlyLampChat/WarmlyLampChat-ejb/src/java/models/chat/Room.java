package models.chat;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import models.ad.AdBlock;

@Entity
@Table(name = "rooms")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @OneToMany
    private List<ChatUser> users;

    @ManyToOne(cascade = CascadeType.ALL)
    private Mood currentMood;

    @Transient
    private List<Message> messages;
    
    @Transient
    private AdBlock currentAdBlock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChatUser> getUsers() {
        return users;
    }

    public void setUsers(List<ChatUser> users) {
        this.users = users;
    }

    public Mood getCurrentMood() {
        return currentMood;
    }

    public void setCurrentMood(Mood currentMood) {
        this.currentMood = currentMood;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public AdBlock getCurrentAdBlock() {
        return currentAdBlock;
    }

    public void setCurrentAdBlock(AdBlock currentAdBlock) {
        this.currentAdBlock = currentAdBlock;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        return this.id == other.id;
    }
}
