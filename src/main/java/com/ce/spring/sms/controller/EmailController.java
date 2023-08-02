package com.ce.spring.sms.controller;

import com.ce.spring.sms.domain.request.EmailRequestModel;
import com.ce.spring.sms.service.EmailService;
import com.ce.spring.sms.utility.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "emailController")
@RequestMapping(value = "/api/v1/email")
@RequiredArgsConstructor
public class EmailController {

    private final static Logger LOGGER = LogManager.getLogger(EmailController.class);
    private final EmailService emailService;

    @PostMapping(value = "/sending")
    public void sendingEmail(@RequestBody EmailRequestModel emailRequestModel){
        LOGGER.info("EmailController: sendingEmail request payload {} ", Mapper.mapToJsonString(emailRequestModel));
        emailService.sendingEmail(emailRequestModel);
        LOGGER.info("EmailController: sendingEmail sent email");
    }

}
