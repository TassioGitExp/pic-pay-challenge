package com.study.tassio.picpaychallenge.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.tassio.picpaychallenge.model.dto.request.EmailRequest;
import com.study.tassio.picpaychallenge.model.dto.request.TransactionRequest;
import com.study.tassio.picpaychallenge.model.dto.response.TransactionResponse;
import com.study.tassio.picpaychallenge.model.entities.Transaction;
import com.study.tassio.picpaychallenge.model.repository.StoreRepository;
import com.study.tassio.picpaychallenge.model.repository.TransactionRepository;
import com.study.tassio.picpaychallenge.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionService {
    @Autowired
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final TransactionAuthorizationService authorizationService;
    private final EmailNotificationService emailService;

    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              StoreRepository storeRepository,
                              TransactionAuthorizationService authorizationService, EmailNotificationService emailService)
    {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.authorizationService = authorizationService;
        this.emailService = emailService;
    }

    public TransactionResponse makeTransaction(TransactionRequest transactionRequest, String userId){
        Boolean authorization = false;

        try {
            authorization = authorizationService.transactionAuthorization();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if (!authorization) {
            throw new RuntimeException("Transação não autorizada. Tente novamente mais tarde.");
        }

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

            var email = new EmailRequest().writeEmail(
                    user.get().getEmail(),
                    store.get().getEmail(),
                    "Transferencia para "+ store.get().getName() +" realizada com sucesso.");

            emailService.sendEmailNotification(email);
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
