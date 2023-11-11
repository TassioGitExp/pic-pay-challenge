package com.study.tassio.picpaychallenge.model.dto.response;

import com.study.tassio.picpaychallenge.model.entities.Store;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class StoreResponse extends RepresentationModel<StoreResponse> {
    private String name;
    private String cnpj;

    public StoreResponse responseOf(final Store store){
        this.name = store.getName();
        this.cnpj = store.getCnpj();

        return this;
    }

}