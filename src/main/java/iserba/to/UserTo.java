package iserba.to;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ilnur on 23.07.17.
 */
public class UserTo {
    private Integer id;

    @NotEmpty(message = "нужно заполнить поле имя")
    private String name;

    @NotEmpty(message = "пароль не должен быть пустым")
    @Length(min = 5, message = "длина пароля минимум 5 знаков")
    private String password;

    @Email(message = "введите email")
    @NotEmpty(message = "нужно заполнить поле email")
    private String email;

    public UserTo() {
    }

    public UserTo(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserTo (" +
                "email=" + email +
                ", name=" + name +
                ')';
    }
}
