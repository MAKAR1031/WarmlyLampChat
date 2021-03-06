package models.chat;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "moods")
public class Mood implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double emotionalFactor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getEmotionalFactor() {
        return emotionalFactor;
    }

    public void setEmotionalFactor(double emotionalFactor) {
        this.emotionalFactor = emotionalFactor;
    }

    public void addEmotionalFactor(double value) {
        this.emotionalFactor += value;
        if (emotionalFactor > 100d) {
            emotionalFactor = 100d;
        }
        if (emotionalFactor < -100d) {
            emotionalFactor = -100d;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
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
        final Mood other = (Mood) obj;
        return this.id == other.id;
    }

}
