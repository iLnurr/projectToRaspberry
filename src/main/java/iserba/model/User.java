package iserba.model;

import java.util.List;

public class User {
    private static int globalUserSequence = 10000;
    private boolean seqUsed = false;
    private Integer id=globalUserSequence;
    private String name;
    private String email;
    private String password;
    protected List<UserQuotations> quotations;

    public User() {
        globalUserSequence++;
        seqUsed=true;
    }

    public User(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        if (!seqUsed){
            globalUserSequence++;
        }
    }

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

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ')';
    }
}