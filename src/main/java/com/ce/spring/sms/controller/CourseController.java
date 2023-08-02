package com.ce.spring.sms.controller;

import com.ce.spring.sms.domain.entity.CourseEntity;
import com.ce.spring.sms.domain.request.CourseRequestModel;
import com.ce.spring.sms.domain.shared.CourseDto;
import com.ce.spring.sms.service.CourseService;
import com.ce.spring.sms.utility.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "courseController")
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private static final Logger LOGGER = LogManager.getLogger(CourseEntity.class);
    private final CourseService courseService;

    @PostMapping(value="/add")
    public CourseEntity addCourse(@RequestBody CourseRequestModel courseRequestModel){
        LOGGER.info("CourseController: addCourse request payload {} ", Mapper.mapToJsonString(courseRequestModel));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CourseDto courseDto = modelMapper.map(courseRequestModel, CourseDto.class);
        CourseEntity courseEntity = courseService.addCourse(courseDto);
        LOGGER.info("CourseController: addCourse response {} ", Mapper.mapToJsonString(courseEntity));
        return courseEntity;
    }

    @PutMapping(value="/update")
    public CourseEntity updateCourse(@RequestBody CourseEntity courseEntity){
        LOGGER.info("CourseController: updateCourse request payload {} ", Mapper.mapToJsonString(courseEntity));
        CourseEntity updateCourseEntity = courseService.updateCourse(courseEntity);
        LOGGER.info("CourseController: updateCourse response {} ", Mapper.mapToJsonString(updateCourseEntity));
        return updateCourseEntity;
    }

    @DeleteMapping(value="/delete/{courseId}")
    public void deleteCourse(@PathVariable Long courseId){
        LOGGER.info("CourseController: deleteCourse delete course by courseId {} ", courseId);
        courseService.deleteCourse(courseId);
        LOGGER.info("CourseController: deleteCourse deleted course");
    }

    @GetMapping(value = "/get/{courseId}")
    public CourseEntity getCourse(@PathVariable Long courseId){
        LOGGER.info("CourseController: getCourse fetch course by courseId {} ", courseId);
        CourseEntity courseEntity = courseService.getCourse(courseId).orElse(null);
        LOGGER.info("CourseController: getCourse response {} ", courseEntity);
        return courseEntity;
    }

    @GetMapping(value = {"/","/gets"})
    public List<CourseEntity> getCourses(){
        LOGGER.info("CourseController: getCourses fetch courses");
        List<CourseEntity> courseEntityList = courseService.getCourses();
        LOGGER.info("CourseController: getCourses response {} ", courseEntityList);
        return courseEntityList;
    }
}
