package com.ce.spring.sms.service.impl;

import com.ce.spring.sms.domain.entity.CourseEntity;
import com.ce.spring.sms.domain.shared.CourseDto;
import com.ce.spring.sms.repository.CourseRepository;
import com.ce.spring.sms.service.CourseService;
import com.ce.spring.sms.utility.GeneratedSequenceNumber;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "courseService")
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LogManager.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;

    @Override
    public CourseEntity addCourse(CourseDto courseDto) {
        LOGGER.info("Course request data received at service layer for adding: {}", courseDto);
        courseDto.setCourseRollNumber(GeneratedSequenceNumber.getCourseSequenceNumber(courseDto.getCourseName().substring(0,3).toUpperCase()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CourseEntity courseEntity = courseRepository.save(modelMapper.map(courseDto, CourseEntity.class));
        LOGGER.info("Save course data after hit database: {}", courseEntity);

        return courseEntity;
    }

    @Override
    public CourseEntity updateCourse(CourseEntity courseEntity) {
        LOGGER.info("Course request data received at service layer for updating: {}", courseEntity);
        CourseEntity updateCourseEntity = courseRepository.save(courseEntity);
        LOGGER.info("Update course data after hit database: {}", updateCourseEntity);
        return updateCourseEntity;
    }

    @Override
    public void deleteCourse(Long courseId) {
        LOGGER.info("Course request data received at service layer for deleting: {}", courseId);
        courseRepository.deleteById(courseId);
        LOGGER.info("Delete course data after hit database: {}", getCourse(courseId));
    }

    @Override
    public Optional<CourseEntity> getCourse(Long courseId) {
        LOGGER.info("Course request data received at service layer for get address: {}", courseId);
        Optional<CourseEntity> courseEntity = courseRepository.findById(courseId);
        LOGGER.info("Get course data after hit database: {}", courseEntity);
        return courseEntity;
    }

    @Override
    public List<CourseEntity> getCourses() {
        LOGGER.info("Course request data received at service layer for get courses");
        return courseRepository.findAll();
    }
}
