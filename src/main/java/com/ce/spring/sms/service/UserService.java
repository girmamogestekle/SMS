package com.ce.spring.sms.service;

import com.ce.spring.sms.domain.shared.UserDto;

public interface UserService {

    void register(UserDto userDto);
    boolean userEnabled(String username);
}
