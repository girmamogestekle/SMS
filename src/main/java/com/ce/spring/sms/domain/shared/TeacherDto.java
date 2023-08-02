package com.ce.spring.sms.domain.shared;

import com.ce.spring.sms.domain.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {

    private String username;
    private String teacherRollNo;
    private int teacherAge;
    private Date teacherStartingDate;
    private String teacherSSN;
    private String teacherPhoneNum;
    private AddressEntity addressEntity;
}
