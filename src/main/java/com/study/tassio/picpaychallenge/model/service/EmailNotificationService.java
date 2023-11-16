package com.study.tassio.picpaychallenge.model.service;

import com.study.tassio.picpaychallenge.model.entities.Email;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailNotificationService {

    public void sendEmailNotification(Email email){
        String uri = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";
        RestTemplate restTemplate = new RestTemplate();

        var emailNotification = restTemplate.getForObject(uri, String.class);

        System.out.println(emailNotification);

    }
}
