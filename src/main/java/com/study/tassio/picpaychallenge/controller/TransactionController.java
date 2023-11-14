package com.study.tassio.picpaychallenge.controller;

import com.study.tassio.picpaychallenge.model.dto.request.TransactionRequest;
import com.study.tassio.picpaychallenge.model.dto.response.TransactionResponse;
import com.study.tassio.picpaychallenge.model.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/{userId}")
    public TransactionResponse makeTransaction(@RequestBody TransactionRequest transactionRequest, @PathVariable String userId){
        return this.transactionService.makeTransaction(transactionRequest, userId);
    }

    @PatchMapping(value = "/{transactionId}/refund")
    public TransactionResponse refundTransaction(@PathVariable String transactionId){
        return this.transactionService.refundTransaction(transactionId);
    }
}
