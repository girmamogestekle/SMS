package com.ce.spring.sms.domain.shared;

import com.ce.spring.sms.domain.entity.ParentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String username;
    private Integer studentAge;
    private String studentRollNo;
    private ParentEntity parentEntity;
}
