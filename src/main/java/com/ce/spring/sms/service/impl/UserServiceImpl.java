package com.ce.spring.sms.service.impl;

import com.ce.spring.sms.domain.entity.UserEntity;
import com.ce.spring.sms.domain.shared.UserDto;
import com.ce.spring.sms.repository.UserRepository;
import com.ce.spring.sms.service.UserService;
import com.ce.spring.sms.utility.GeneratedSequenceNumber;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GeneratedSequenceNumber generatedSequenceNumber;
    @Override
    public void register(UserDto userDto) {

        userDto.setEnabled(true);
        userDto.setFullName(userDto.getFirstName(), userDto.getMiddleName(),userDto.getLastName());
        userDto.setCreatedAt(LocalDateTime.now());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setId(generatedSequenceNumber.generateMongoSequence());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(userEntity);
    }
    @Override
    public boolean userEnabled(String username){
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        return userEntity.get().isEnabled();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsernameAndEnabled(username,true);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userEntity.get().getRole().toString());
        return new User(userEntity.get().getUsername(), userEntity.get().getPassword(), Collections.singletonList(grantedAuthority));
    }
}
