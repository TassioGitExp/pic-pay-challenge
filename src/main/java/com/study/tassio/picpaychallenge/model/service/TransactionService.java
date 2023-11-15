package com.study.tassio.picpaychallenge.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.tassio.picpaychallenge.model.dto.request.TransactionRequest;
import com.study.tassio.picpaychallenge.model.dto.response.TransactionResponse;
import com.study.tassio.picpaychallenge.model.entities.Transaction;
import com.study.tassio.picpaychallenge.model.entities.TransactionAuthorization;
import com.study.tassio.picpaychallenge.model.repository.StoreRepository;
import com.study.tassio.picpaychallenge.model.repository.TransactionRepository;
import com.study.tassio.picpaychallenge.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class TransactionService {
    @Autowired
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              StoreRepository storeRepository)
    {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    public String authorizeTransaction() throws JsonProcessingException {
        String uri = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        var authorizationString = restTemplate.getForObject(uri, String.class);

        TransactionAuthorization authorizationMapped = mapper.readValue(authorizationString, TransactionAuthorization.class);

        return authorizationMapped.getMessage();
    }

    public TransactionResponse makeTransaction(TransactionRequest transactionRequest, String userId){
        String authorization = "";
        try {
            authorization = authorizeTransaction();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if (!Objects.equals(authorization, "Autorizado")) {
            throw new RuntimeException("Transação: " + authorization + ".\nTente novamente mais tarde.");
        }

        System.out.println(authorization);

        var user = userRepository.findById(transactionRequest.getUserId());
        System.out.println(user.toString());

        var store = storeRepository.findById(transactionRequest.getStoreId());
        System.out.println(store.toString());

        var transaction = new Transaction();
        System.out.println(transaction);

        if (user.isPresent() && store.isPresent()
                && Objects.equals(transactionRequest.getUserId(), userId)
                && Objects.equals(transactionRequest.getStoreId(), store.get().getStoreId()))
        {
            transaction = transactionRequest.toTransaction(user.get(), store.get());
            System.out.println(transaction);

            if (user.get().getBalance() - transaction.getAmount() < 0) {
                throw new RuntimeException("O usuário não possui saldo suficiente na conta.");
            }

            if (transaction.getAmount() <= 0) {
                throw new RuntimeException("O valor a ser transferido não pode ser 0.0 ou negativo.");
            }

            user.get().setBalance(user.get().getBalance() - transaction.getAmount());
            userRepository.save(user.get());
            System.out.println(user.toString());

            store.get().setBalance(store.get().getBalance() + transaction.getAmount());
            storeRepository.save(store.get());
            System.out.println(store.toString());

            transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Algo deu errado.");
        }

        return new TransactionResponse().responseOf(transaction);
    }

    public TransactionResponse refundTransaction (String transactionId){
        var transaction = transactionRepository.findById(transactionId);

        if (transaction.isPresent()){
            var user = userRepository.findById(transaction.get().getUser().getUserId());
            var store = storeRepository.findById(transaction.get().getStore().getStoreId());

            user.get().setBalance(user.get().getBalance() + transaction.get().getAmount());
            store.get().setBalance(store.get().getBalance() - transaction.get().getAmount());

            transaction.get().setAmount(0.0);

            userRepository.save(user.get());
            storeRepository.save(store.get());
            transactionRepository.save(transaction.get());
        } else {
            throw  new RuntimeException("Transação não encontrada.");
        }

        return new TransactionResponse().responseOf(transaction.get());
    }
}
