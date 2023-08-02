package com.ce.spring.sms.service.impl;

import com.amazonaws.services.connect.model.UserNotFoundException;
import com.ce.spring.sms.domain.entity.StudentEntity;
import com.ce.spring.sms.domain.shared.StudentDto;
import com.ce.spring.sms.repository.StudentRepository;
import com.ce.spring.sms.service.StudentService;
import com.ce.spring.sms.service.UserService;
import com.ce.spring.sms.utility.GeneratedSequenceNumber;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Optional;

@Service(value = "studentService")
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LogManager.getLogger(ParentServiceImpl.class);
    private final StudentRepository studentRepository;
    private final UserService userService;


    @Override
    public StudentEntity addStudent(StudentDto studentDto) {

        if(!userService.userEnabled(studentDto.getUsername())){
            throw new UserNotFoundException(studentDto.getUsername() + " not found!");
        }

        LOGGER.info("Student request data received at service layer for adding: {}", studentDto);
        studentDto.setStudentRollNo(GeneratedSequenceNumber.getStudentSequenceNumber());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        StudentEntity studentEntity = modelMapper.map(studentDto, StudentEntity.class);
        LOGGER.info("Save student data after hit database: {}", studentEntity);

        return studentRepository.save(studentEntity);
    }

    @Override
    public StudentEntity updateStudent(StudentEntity studentEntity) {

        if(!userService.userEnabled(studentEntity.getUsername())){
            throw new UserNotFoundException(studentEntity.getUsername() + " not found!");
        }

        LOGGER.info("Student request data received at service layer for updating: {}", studentEntity);
        StudentEntity updateStudentEntity = studentRepository.save(studentEntity);
        LOGGER.info("Update student data after hit database: {}", updateStudentEntity);
        return updateStudentEntity;
    }

    @Override
    public void deleteStudent(Long studentId) {
        LOGGER.info("Student request data received at service layer for deleting: {}", studentId);
        studentRepository.deleteById(studentId);
        LOGGER.info("Delete student data after hit database: {}", studentId);
    }

    @Override
    public Optional<StudentEntity> getStudent(Long studentId) {
        LOGGER.info("Student request data received at service layer for get student: {}", studentId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        LOGGER.info("Get student data after hit database: {}", studentEntity);
        return studentEntity;
    }

    @Override
    public List<StudentEntity> getStudents() {
        LOGGER.info("Student request data received at service layer for get students");
        return studentRepository.findAll();
    }

}
