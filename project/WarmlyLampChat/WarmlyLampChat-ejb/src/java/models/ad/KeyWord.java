package models.ad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "key_words")
public class KeyWord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique = true, nullable = false, name = "KEY_VALUE")
    private String value;
    @Column(nullable = false)
    private double emotionFactorIncrease;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getEmotionFactorIncrease() {
        return emotionFactorIncrease;
    }

    public void setEmotionFactorIncrease(double emotionFactorIncrease) {
        this.emotionFactorIncrease = emotionFactorIncrease;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
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
        final KeyWord other = (KeyWord) obj;
        return this.id == other.id;
    }
}