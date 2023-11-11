package com.study.tassio.picpaychallenge.model.dto.response;

import com.study.tassio.picpaychallenge.model.entities.User;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class UserResponse extends RepresentationModel<UserResponse> {
    private String name;

    public UserResponse responseOf(final User user){
        this.name = user.getName();

        return this;
    }

}
