package iserba.to;

/**
 * Created by ilnur on 07.03.17.
 */
public class UserQuotationsTo {
    private String userName;
    private String description;

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

    @Override
    public String toString() {
        return "UserQuotationsTo{" +
                "userName='" + userName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
