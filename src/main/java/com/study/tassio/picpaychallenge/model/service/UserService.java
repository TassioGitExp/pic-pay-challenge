package com.study.tassio.picpaychallenge.model.service;

import com.study.tassio.picpaychallenge.model.dto.request.BalanceRequest;
import com.study.tassio.picpaychallenge.model.dto.request.UserRequest;
import com.study.tassio.picpaychallenge.model.dto.response.UserResponse;
import com.study.tassio.picpaychallenge.model.entities.Transaction;
import com.study.tassio.picpaychallenge.model.entities.User;
import com.study.tassio.picpaychallenge.model.repository.StoreRepository;
import com.study.tassio.picpaychallenge.model.repository.TransactionRepository;
import com.study.tassio.picpaychallenge.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest){
        var user = userRequest.toUser();

        System.out.println(user.toString());

        userRepository.save(user);

        return new UserResponse().responseOf(user);
    }

    public  UserResponse addMoneyToUser(String userId, BalanceRequest balanceRequest) {
        System.out.println(balanceRequest);
        var user = userRepository.findById(userId);

        if (balanceRequest.getAmount() < 0) {
            throw new RuntimeException("O Valor a ser enviado não pode ser negativo.");
        }

        if (user.isPresent() && Objects.equals(balanceRequest.getId(), userId)) {
            user.get().setBalance(user.get().getBalance() + balanceRequest.getAmount());

            userRepository.save(user.get());

        }else {
            throw new RuntimeException("Usuário não encontrado.");
        }
        return new UserResponse().responseOf(user.get());
    }
}