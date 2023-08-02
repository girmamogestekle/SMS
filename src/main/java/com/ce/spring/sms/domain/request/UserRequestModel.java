package com.ce.spring.sms.domain.request;

import com.ce.spring.sms.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel {

    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
