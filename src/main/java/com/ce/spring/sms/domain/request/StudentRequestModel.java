package com.ce.spring.sms.domain.request;

import com.ce.spring.sms.domain.entity.ParentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestModel {

    private String username;
    private Integer studentAge;
    private ParentEntity parentEntity;
}
