package iserba.to;

import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;

/**
 * Created by ilnur on 07.03.17.
 */
public class UserQuotationsTo {
    private Integer id;

    private String userName;
    @NotEmpty(message = "сообщение не может быть пустым")
    private String description;

    private LocalDateTime dateTime = LocalDateTime.now();

    public UserQuotationsTo(String userName, String description) {
        this.userName = userName;
        this.description = description;
    }

    public UserQuotationsTo() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserQuotationsTo{" +
                "userName='" + userName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
