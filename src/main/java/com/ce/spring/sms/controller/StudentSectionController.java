package com.ce.spring.sms.controller;

import com.ce.spring.sms.domain.entity.StudentSectionEntity;
import com.ce.spring.sms.domain.request.StudentSectionRequestModel;
import com.ce.spring.sms.service.StudentSectionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "studentSectionController")
@RequestMapping(value = "/api/v1/student/section")
@RequiredArgsConstructor
public class StudentSectionController {

    private static final Logger LOGGER = LogManager.getLogger(StudentSectionController.class);
    private final StudentSectionService studentSectionService;

    @PostMapping(value = "/add")
    public StudentSectionEntity addStudentSection(@RequestBody StudentSectionRequestModel studentSectionRequestModel){
        LOGGER.info("StudentSection request data received at controller layer for post mapping: {}", studentSectionRequestModel);
        return studentSectionService.addStudentSection(studentSectionRequestModel);
    }

    @PutMapping(value = "/update")
    public StudentSectionEntity updateStudentSection(@RequestBody StudentSectionEntity studentSectionEntity){
        LOGGER.info("StudentSection request data received at controller layer for put mapping: {}", studentSectionEntity);
        return studentSectionService.updateStudentSection(studentSectionEntity);
    }

    @DeleteMapping(value = "/delete/{studentSectionId}")
    public void deleteStudentSection(@PathVariable Long studentSectionId){
        LOGGER.info("StudentSection request data received at controller layer for delete mapping: {}", studentSectionId);
        studentSectionService.deleteStudentSection(studentSectionId);
    }

    @GetMapping(value = "/get/{studentSectionId}")
    public StudentSectionEntity getStudentSection(@PathVariable Long studentSectionId){
        LOGGER.info("StudentSection request data received at controller layer for get mapping: {}", studentSectionId);
        return studentSectionService.getStudentSection(studentSectionId).orElse(null);
    }

    @GetMapping(value = {"/","/gets"})
    public List<StudentSectionEntity> getStudentSectionList(){
        LOGGER.info("Request students sections at controller layer for get mapping");
        return studentSectionService.getStudentSectionList();
    }
}
