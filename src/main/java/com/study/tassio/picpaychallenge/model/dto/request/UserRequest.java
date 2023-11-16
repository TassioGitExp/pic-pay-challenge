package com.study.tassio.picpaychallenge.model.dto.request;

import com.study.tassio.picpaychallenge.model.entities.User;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@ToString
public class UserRequest {

    private String name;
    private String cpf;
    private String email;
    private String password;

    public String generateId(){
        String id = UUID.randomUUID().toString();

        return id.substring(0, 8);
    }

    public User toUser(){
        final User user = new User();
        final String id = generateId();

        user.setUserId(id);
        user.setName(this.name);
        user.setCpf(this.cpf);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setBalance(0.0);

        return user;
    }

}
