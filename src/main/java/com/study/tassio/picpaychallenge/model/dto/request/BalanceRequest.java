package com.study.tassio.picpaychallenge.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BalanceRequest {
    String id;
    Double amount;


}
