package com.study.tassio.picpaychallenge.model.dto.response;

import com.study.tassio.picpaychallenge.model.entities.User;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class UserResponse extends RepresentationModel<UserResponse> {
    private String name;
    private String email;
    private Double balance;

    public UserResponse responseOf(final User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.balance = user.getBalance();

        return this;
    }

}
