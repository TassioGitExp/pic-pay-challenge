package com.study.tassio.picpaychallenge.controller;

import com.study.tassio.picpaychallenge.model.dto.request.BalanceRequest;
import com.study.tassio.picpaychallenge.model.dto.request.StoreRequest;
import com.study.tassio.picpaychallenge.model.dto.response.StoreResponse;
import com.study.tassio.picpaychallenge.model.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Store", description = "Store documentation")
@RequestMapping(value = "/store")
public class StoreController {
    @Autowired
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @Operation(summary = "Creates a new store.")
    @PostMapping()
    public StoreResponse createStore(@RequestBody StoreRequest storeRequest){
        return this.storeService.createStore(storeRequest);
    }

    @Operation(summary = "Deposits money into an store account.")
    @PatchMapping(value = "/{storeId}")
    public StoreResponse addMoneyToStore(@PathVariable String storeId, @RequestBody BalanceRequest balanceRequest){
        return this.storeService.addMoneyToStore(storeId, balanceRequest);
    }
}
