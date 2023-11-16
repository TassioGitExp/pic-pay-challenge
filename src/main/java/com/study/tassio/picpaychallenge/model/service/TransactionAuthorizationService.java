package com.study.tassio.picpaychallenge.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.tassio.picpaychallenge.model.entities.TransactionAuthorization;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionAuthorizationService {

    public Boolean transactionAuthorization() throws JsonProcessingException {
        String uri = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        var authorizationString = restTemplate.getForObject(uri, String.class);

        TransactionAuthorization authorizationMapped = mapper.readValue(authorizationString, TransactionAuthorization.class);

        System.out.println(authorizationString);
        
        return authorizationMapped.getMessage().equals("Autorizado");
    }
}
