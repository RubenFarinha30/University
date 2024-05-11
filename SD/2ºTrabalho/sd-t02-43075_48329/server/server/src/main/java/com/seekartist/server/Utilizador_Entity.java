package com.seekartist.server;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="utilizador")
public class Utilizador_Entity {

    @Id
    private String username;
    private String password;
    private String email;
    private String type;

    // Constructors, getters, setters

    public Utilizador_Entity() {
        this.username = "";
        this.password = "";
        this.email = "";
        this.type = "geral";
    }

    public Utilizador_Entity(String username, String password, String email, String type) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = "geral";
    }

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}