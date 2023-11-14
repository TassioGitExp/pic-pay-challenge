package com.study.tassio.picpaychallenge.model.dto.response;

import com.study.tassio.picpaychallenge.model.entities.Transaction;
import lombok.Getter;

@Getter
public class TransactionResponse {
    private String userId;
    private String storeId;
    private Double amount;

    public TransactionResponse responseOf(final Transaction transaction){
        this.userId = transaction.getUser().getUserId();
        this.storeId = transaction.getStore().getStoreId();
        this.amount = transaction.getAmount();

        return this;
    }
}
