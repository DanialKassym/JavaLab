package com.springproject.Spring.Users;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username ;

    @Column(nullable = false)
    private String password_hash;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created_user_at;


    public Users() {}

    public Users(Long id, String username,String password_hash, Date created_user_at) {
        this.id = id;
        this.username = username;
        this.password_hash = password_hash;
        this.created_user_at = created_user_at;
    }

    public Users(String username,String password_hash, Date created_user_at) {
        this.username = username;
        this.password_hash = password_hash;
        this.created_user_at = created_user_at;
    }

    public Users(String username, Date created_user_at) {
        this.username = username;
        this.created_user_at = created_user_at;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Date getCreated_user_at() {
        return created_user_at;
    }

}
