package com.ce.spring.sms.service.impl;

import com.amazonaws.services.connect.model.UserNotFoundException;
import com.ce.spring.sms.domain.entity.StudentSectionEntity;
import com.ce.spring.sms.domain.request.StudentSectionRequestModel;
import com.ce.spring.sms.repository.StudentSectionRepository;
import com.ce.spring.sms.service.StudentSectionService;
import com.ce.spring.sms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "studentSectionService")
@RequiredArgsConstructor
public class StudentSectionServiceImpl implements StudentSectionService {

    private static final Logger LOGGER = LogManager.getLogger(ParentServiceImpl.class);
    private final StudentSectionRepository studentSectionRepository;
    private final UserService userService;

    @Override
    public StudentSectionEntity addStudentSection(StudentSectionRequestModel studentSectionRequestModel) {

        if(!userService.userEnabled(studentSectionRequestModel.getStudentEntity().getUsername())){
            throw new UserNotFoundException(studentSectionRequestModel.getStudentEntity().getUsername() + " not found!");
        }

        LOGGER.info("StudentSection request data received at service layer for adding: {}", studentSectionRequestModel);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        StudentSectionEntity studentSectionEntity = studentSectionRepository.save(modelMapper.map(studentSectionRequestModel, StudentSectionEntity.class));
        LOGGER.info("Save student section data after hit database: {}", studentSectionEntity);

        return studentSectionEntity;
    }

    @Override
    public StudentSectionEntity updateStudentSection(StudentSectionEntity studentSectionEntity) {

        if(!userService.userEnabled(studentSectionEntity.getStudentEntity().getUsername())){
            throw new UserNotFoundException(studentSectionEntity.getStudentEntity().getUsername() + " not found!");
        }

        LOGGER.info("StudentSection request data received at service layer for updating: {}", studentSectionEntity);
        StudentSectionEntity updateStudentSectionEntity = studentSectionRepository.save(studentSectionEntity);
        LOGGER.info("Update student section data after hit database: {}", updateStudentSectionEntity);
        return updateStudentSectionEntity;
    }

    @Override
    public void deleteStudentSection(Long studentSectionEntityId) {
        LOGGER.info("StudentSection request data received at service layer for deleting: {}", studentSectionEntityId);
        studentSectionRepository.deleteById(studentSectionEntityId);
        LOGGER.info("Delete student section data after hit database: {}", studentSectionEntityId);
    }

    @Override
    public Optional<StudentSectionEntity> getStudentSection(Long studentSectionEntityId) {
        LOGGER.info("StudentSection request data received at service layer for get student section: {}", studentSectionEntityId);
        Optional<StudentSectionEntity> studentSectionEntity = studentSectionRepository.findById(studentSectionEntityId);
        LOGGER.info("Get student section data after hit database: {}", studentSectionEntity);
        return studentSectionEntity;
    }

    @Override
    public List<StudentSectionEntity> getStudentSectionList() {
        LOGGER.info("StudentSection request data received at service layer for get student sections");
        return studentSectionRepository.findAll();
    }
}
