package com.ce.spring.sms.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestModel {

    private String fromEmail;
    private String toEmail;
    private String subject;
    private String content;
}
