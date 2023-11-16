package com.study.tassio.picpaychallenge.model.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String sentBy;
    private String sendTo;
    private String message;

}
