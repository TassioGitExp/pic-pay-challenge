package com.study.tassio.picpaychallenge.model.dto.request;

import com.study.tassio.picpaychallenge.model.entities.Email;
import lombok.Setter;

@Setter
public class EmailRequest {
    public Email writeEmail(String userEmail, String storeEmail, String message){
        Email email = new Email();

        email.setSentBy(storeEmail);
        email.setSendTo(userEmail);
        email.setMessage(message);

        return email;
    }

}
