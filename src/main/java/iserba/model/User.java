package iserba.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @NotEmpty(message = "нужно заполнить поле имя")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "пароль не должен быть пустым")
    @Length(min = 5, message = "длина пароля минимум 5 знаков")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "введите email")
    @NotEmpty(message = "нужно заполнить поле email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected List<UserQuotations> quotations;

    public User() {
    }

    public User(Integer id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    public List<UserQuotations> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<UserQuotations> quotations) {
        this.quotations = quotations;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ')';
    }
}