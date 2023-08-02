package com.ce.spring.sms.controller;

import com.ce.spring.sms.domain.entity.StudentEntity;
import com.ce.spring.sms.domain.request.StudentRequestModel;
import com.ce.spring.sms.domain.shared.StudentDto;
import com.ce.spring.sms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "studentController")
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private static final Logger LOGGER = LogManager.getLogger(StudentController.class);
    private final StudentService studentService;

    @PostMapping(value = "/add")
    public StudentEntity saveStudent(@RequestBody StudentRequestModel studentRequestModel){
        LOGGER.info("Student request data received at controller layer for post mapping: {}", studentRequestModel);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        StudentDto studentDto = modelMapper.map(studentRequestModel, StudentDto.class);

        return studentService.addStudent(studentDto);
    }

    @PutMapping(value = "/update")
    public StudentEntity updateStudent(@RequestBody StudentEntity studentEntity){
        LOGGER.info("Student request data received at controller layer for put mapping: {}", studentEntity);
        return studentService.updateStudent(studentEntity);
    }

    @DeleteMapping(value = "/delete/{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        LOGGER.info("Student request data received at controller layer for delete mapping: {}", studentId);
        studentService.deleteStudent(studentId);
    }

    @GetMapping(value = "/get/{studentId}")
    public StudentEntity getStudent(@PathVariable Long studentId){
        LOGGER.info("Student request data received at controller layer for get mapping: {}", studentId);
        return studentService.getStudent(studentId).orElse(null);
    }

    @GetMapping(value = {"/", "/gets"})
    public List<StudentEntity> getStudents(){
        LOGGER.info("Request students at controller layer for get mapping");
        return studentService.getStudents();
    }

}
