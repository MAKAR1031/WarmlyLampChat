package models.ad;

import models.chat.Mood;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "adblocks")
public class AdBlock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Size(min = 5, max = 30)
    private String title;
    
    @NotNull
    @Size(min = 10)
    private String content;
    
    @Temporal(TemporalType.DATE)
    private Date activationDate;
    
    private Integer daysOfActive;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Status status;

    private int targetMoodId;
    
    @Transient
    private Mood targetMood;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Integer getDaysOfActive() {
        return daysOfActive;
    }

    public void setDaysOfActive(Integer daysOfActive) {
        this.daysOfActive = daysOfActive;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTargetMoodId() {
        return targetMoodId;
    }

    public void setTargetMoodId(int targetMoodId) {
        this.targetMoodId = targetMoodId;
    }

    public Mood getTargetMood() {
        return targetMood;
    }

    public void setTargetMood(Mood targetMood) {
        this.targetMood = targetMood;
    }
}