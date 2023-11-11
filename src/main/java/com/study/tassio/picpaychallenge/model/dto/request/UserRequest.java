package com.study.tassio.picpaychallenge.model.dto.request;

import com.study.tassio.picpaychallenge.model.entities.User;
import lombok.Setter;

@Setter
public class UserRequest {

    private String name;
    private String cpf;
    private Double balance;

    public User toUser(){
        final User user = new User();

        user.setName(this.name);
        user.setCpf(this.cpf);
        user.setBalance(this.balance);

        return user;
    }

}
