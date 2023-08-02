package com.ce.spring.sms.domain.shared;

import com.ce.spring.sms.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
    private String fullName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Role role;
    public void setFullName(String firstName, String middleName, String lastName){
        fullName =  firstName + " " + middleName + " " + lastName;
    }
}
