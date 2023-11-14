package com.study.tassio.picpaychallenge.model.dto.request;

import com.study.tassio.picpaychallenge.model.entities.Store;
import com.study.tassio.picpaychallenge.model.entities.Transaction;
import com.study.tassio.picpaychallenge.model.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class TransactionRequest {
    private String userId;
    private String storeId;
    private Double amount;

    public String generateId(){
        String id = UUID.randomUUID().toString();

        return id.substring(0, 8);
    }
    public Transaction toTransaction(User user, Store store){
        var transaction = new Transaction();
        transaction.setId(generateId());

        transaction.setAmount(amount);
        transaction.setStore(store);
        transaction.setUser(user);

        return transaction;
    }
}
