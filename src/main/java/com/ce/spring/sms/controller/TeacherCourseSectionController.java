package com.ce.spring.sms.controller;

import com.ce.spring.sms.domain.entity.TeacherCourseSectionEntity;
import com.ce.spring.sms.domain.request.TeacherCourseSectionRequestModel;
import com.ce.spring.sms.service.TeacherCourseSectionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "teacherCourseSectionController")
@RequestMapping(value = "/api/v1/teacher/course/section")
@RequiredArgsConstructor
public class TeacherCourseSectionController {

    private static final Logger LOGGER = LogManager.getLogger(TeacherCourseSectionController.class);
    private final TeacherCourseSectionService teacherCourseSectionService;

    @PostMapping(value = "/add")
    public TeacherCourseSectionEntity addTeacherCourseSection(@RequestBody TeacherCourseSectionRequestModel teacherCourseSectionRequestModel){
        LOGGER.info("TeacherCourseSection request data received at controller layer for post mapping: {}", teacherCourseSectionRequestModel);
        return teacherCourseSectionService.addTeacherCourseSection(teacherCourseSectionRequestModel);
    }

    @PutMapping(value = "/update")
    public TeacherCourseSectionEntity updateTeacherCourseSection(@RequestBody TeacherCourseSectionEntity teacherCourseSectionEntity){
        LOGGER.info("TeacherCourseSection request data received at controller layer for put mapping: {}", teacherCourseSectionEntity);
        return teacherCourseSectionService.updateTeacherCourseSection(teacherCourseSectionEntity);
    }

    @DeleteMapping(value = "/delete/{teacherCourseSectionId}")
    public void deleteTeacherCourseSection(@PathVariable Long teacherCourseSectionId){
        LOGGER.info("TeacherCourseSection request data received at controller layer for delete mapping: {}", teacherCourseSectionId);
        teacherCourseSectionService.deleteTeacherCourseSection(teacherCourseSectionId);
    }

    @GetMapping(value = "/get/{teacherCourseSectionId}")
    public TeacherCourseSectionEntity getTeacherCourseSection(@PathVariable Long teacherCourseSectionId){
        LOGGER.info("TeacherCourseSection request data received at controller layer for get mapping: {}", teacherCourseSectionId);
        return teacherCourseSectionService.getTeacherCourseSection(teacherCourseSectionId).orElse(null);
    }

    @GetMapping(value = {"/","/gets"})
    public List<TeacherCourseSectionEntity> getTeacherCourseSectionList(){
        LOGGER.info("Request teachers courses sections at controller layer for get mapping");
        return teacherCourseSectionService.getTeacherCourseSectionList();
    }
}
