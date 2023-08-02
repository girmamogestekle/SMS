package com.ce.spring.sms.service.impl;

import com.ce.spring.sms.domain.entity.EmailEntity;
import com.ce.spring.sms.domain.request.EmailRequestModel;
import com.ce.spring.sms.repository.EmailRepository;
import com.ce.spring.sms.service.EmailService;
import com.ce.spring.sms.utility.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service(value = "emailService")
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LogManager.getLogger(EmailServiceImpl.class);
    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public void sendingEmail(EmailRequestModel emailRequestModel) {

        LOGGER.info("EmailService: sendingEmail request payload {} ", Mapper.mapToJsonString(emailRequestModel));

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        EmailEntity emailEntity = mapRequestModelToEntity(emailRequestModel);

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(emailEntity.getFromEmail());
            mimeMessageHelper.setTo(emailEntity.getToEmail());
            mimeMessageHelper.setSubject(emailEntity.getSubject());
            mimeMessageHelper.setText(emailEntity.getContent());

            // Sending email
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
//            LOGGER.info("EmailService: sendingEmail, sent email load data {} ", Mapper.mapToJsonStringForMimeMessageHelper(mimeMessageHelper));

            // Save sending email into mongodb
            EmailEntity saveEmailEntity = emailRepository.save(emailEntity);
            LOGGER.info("EmailService: sendingEmail, save email data in database {} ", Mapper.mapToJsonString(saveEmailEntity));
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }

    private EmailEntity mapRequestModelToEntity(EmailRequestModel emailRequestModel){
        LOGGER.info("EmailService: mapRequestModelToEntity, request payload {} ", Mapper.mapToJsonString(emailRequestModel));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmailEntity emailEntity = modelMapper.map(emailRequestModel, EmailEntity.class);

        emailEntity.setSendingDate(new Date());
        emailEntity.setName(emailEntity.getSubject() + "_"+emailEntity.getSendingDate());
        LOGGER.info("EmailService: mapRequestModelToEntity, email entity data {} ", Mapper.mapToJsonString(emailEntity));

        return emailEntity;
    }
}
