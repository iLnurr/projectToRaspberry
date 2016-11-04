package iserba.model;

import java.time.LocalDateTime;

public class UserQuotations {
    private static int globalSequenceQuota = 50000;
    private boolean sequenceUsed = false;
    private Integer id=globalSequenceQuota;
    private String description;
    private LocalDateTime dateTime;
    private User user;

    public UserQuotations() {
        globalSequenceQuota++;
        sequenceUsed=true;
    }

    public UserQuotations(int id, String description, LocalDateTime dateTime) {
        this.id = id;
        this.description = description;
        this.dateTime = dateTime;
        if (!sequenceUsed){
            globalSequenceQuota++;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isNew() {
        return (this.id == null);
    }
}
