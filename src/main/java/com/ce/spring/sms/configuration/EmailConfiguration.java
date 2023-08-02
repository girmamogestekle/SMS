package com.ce.spring.sms.configuration;

import com.ce.spring.sms.domain.shared.EmailConfigDto;
import com.ce.spring.sms.utility.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@Lazy
public class EmailConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(EmailConfiguration.class);

    @Autowired
    private EmailConfigDto emailConfigDto;

    // Create a mail sender bean
    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();

        javaMailSenderImpl.setHost(emailConfigDto.getHost());
        javaMailSenderImpl.setPort(emailConfigDto.getPort());
        javaMailSenderImpl.setUsername(emailConfigDto.getUsername());
        javaMailSenderImpl.setPassword(emailConfigDto.getPassword());

        //LOGGER.info("EmailConfiguration: getMailSender email config load data {} ", Mapper.mapToJsonString(javaMailSenderImpl));
        return javaMailSenderImpl;
    }
}
