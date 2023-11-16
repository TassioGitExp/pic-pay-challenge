package com.study.tassio.picpaychallenge.model.dto.request;

import com.study.tassio.picpaychallenge.model.entities.Store;
import lombok.Setter;

import java.util.UUID;

@Setter
public class StoreRequest {

    private String name;
    private String cnpj;
    private String email;
    private String password;

    public String generateId(){
        String id = UUID.randomUUID().toString();

        return id.substring(0, 8);
    }

    public Store toStore(){
        final Store store = new Store();

        store.setStoreId(generateId());
        store.setName(this.name);
        store.setCnpj(this.cnpj);
        store.setBalance(0.0);
        store.setEmail(this.email);
        store.setPassword(this.password);

        return store;
    }

}
