package iserba.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = UserQuotations.GET, query = "SELECT m FROM UserQuotations m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = UserQuotations.ALL_SORTED, query = "SELECT m FROM UserQuotations m WHERE m.user.id=:userId ORDER BY m.dateTime DESC"),
        @NamedQuery(name = UserQuotations.DELETE, query = "DELETE FROM UserQuotations m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = UserQuotations.GET_BETWEEN, query = "SELECT m FROM UserQuotations m "+
                "WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
})

@Entity
@Table(name = "quotations", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "quotas_unique_user_datetime_idx")})
public class UserQuotations {
    public static final String GET = "UserQuotations.get";
    public static final String ALL_SORTED = "UserQuotations.getAll";
    public static final String DELETE = "UserQuotations.delete";
    public static final String GET_BETWEEN = "UserQuotations.getBetween";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false, length = 4096)
    @NotEmpty
    private String description;

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserQuotations() {
    }

    public UserQuotations(int id, String description, LocalDateTime dateTime) {
        this.id = id;
        this.description = description;
        this.dateTime = dateTime;
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