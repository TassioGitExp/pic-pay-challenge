package com.study.tassio.picpaychallenge.model.dto.request;

import com.study.tassio.picpaychallenge.model.entities.Store;
import lombok.Setter;

@Setter
public class StoreRequest {

    private String name;
    private String cnpj;
    private Double balance;

    public Store toStore(){
        final Store store = new Store();

        store.setName(this.name);
        store.setCnpj(this.cnpj);
        store.setBalance(this.balance);

        return store;
    }

}
