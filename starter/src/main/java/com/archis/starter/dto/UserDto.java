package com.archis.starter.dto;

import com.archis.starter.entity.Comment;
import com.archis.starter.entity.Product;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private List<Comment> comments;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
