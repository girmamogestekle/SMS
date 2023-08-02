package com.ce.spring.sms.domain.request;

import com.ce.spring.sms.domain.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentRequestModel {

    private String username;
    private String parentSSN;
    private AddressEntity addressEntity;
    private String parentPhoneNumber;
}
