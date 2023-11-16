package com.study.tassio.picpaychallenge.model.service;

import com.study.tassio.picpaychallenge.model.dto.request.BalanceRequest;
import com.study.tassio.picpaychallenge.model.dto.request.StoreRequest;
import com.study.tassio.picpaychallenge.model.dto.response.StoreResponse;
import com.study.tassio.picpaychallenge.model.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StoreService {
    @Autowired
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreResponse createStore(StoreRequest storeRequest){
        var store = storeRequest.toStore();

        System.out.println(store.toString());

        storeRepository.save(store);

        return new StoreResponse().responseOf(store);
    }

    public StoreResponse addMoneyToStore(String storeId, BalanceRequest balanceRequest) {
        System.out.println(balanceRequest);
        var store = storeRepository.findById(storeId);

        if (balanceRequest.getAmount() < 0) {
            throw new RuntimeException("O Valor a ser enviado não pode ser negativo.");
        }

        if (store.isPresent() && Objects.equals(balanceRequest.getId(), storeId)) {
            store.get().setBalance(store.get().getBalance() + balanceRequest.getAmount());

            storeRepository.save(store.get());

        }else {
            throw new RuntimeException("Usuário não encontrado.");
        }
        return new StoreResponse().responseOf(store.get());
    }
}
