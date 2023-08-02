package com.ce.spring.sms.service.impl;

import com.amazonaws.services.connect.model.UserNotFoundException;
import com.ce.spring.sms.domain.entity.TeacherCourseSectionEntity;
import com.ce.spring.sms.domain.request.TeacherCourseSectionRequestModel;
import com.ce.spring.sms.repository.TeacherCourseSectionRepository;
import com.ce.spring.sms.service.TeacherCourseSectionService;
import com.ce.spring.sms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "teacherCourseSectionService")
@RequiredArgsConstructor
public class TeacherCourseSectionServiceImpl implements TeacherCourseSectionService {

    private static final Logger LOGGER = LogManager.getLogger(ParentServiceImpl.class);
    private final TeacherCourseSectionRepository teacherCourseSectionRepository;
    private final UserService userService;

    @Override
    public TeacherCourseSectionEntity addTeacherCourseSection(TeacherCourseSectionRequestModel teacherCourseSectionRequestModel) {

        if(!userService.userEnabled(teacherCourseSectionRequestModel.getTeacherEntity().getUsername())){
            throw new UserNotFoundException(teacherCourseSectionRequestModel.getTeacherEntity().getUsername() + " not found!");
        }

        LOGGER.info("TeacherCourseSection request data received at service layer for adding: {}", teacherCourseSectionRequestModel);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TeacherCourseSectionEntity teacherCourseSectionEntity = teacherCourseSectionRepository.save(modelMapper.map(teacherCourseSectionRequestModel, TeacherCourseSectionEntity.class));
        LOGGER.info("Save teacher course section data after hit database: {}", teacherCourseSectionEntity);

        return teacherCourseSectionEntity;
    }

    @Override
    public TeacherCourseSectionEntity updateTeacherCourseSection(TeacherCourseSectionEntity teacherCourseSectionEntity) {

        if(!userService.userEnabled(teacherCourseSectionEntity.getTeacherEntity().getUsername())){
            throw new UserNotFoundException(teacherCourseSectionEntity.getTeacherEntity().getUsername() + " not found!");
        }

        LOGGER.info("TeacherCourseSection request data received at service layer for updating: {}", teacherCourseSectionEntity);
        TeacherCourseSectionEntity updateTeacherCourseSectionEntity = teacherCourseSectionRepository.save(teacherCourseSectionEntity);
        LOGGER.info("Update teacher course section data after hit database: {}", updateTeacherCourseSectionEntity);
        return updateTeacherCourseSectionEntity;
    }

    @Override
    public void deleteTeacherCourseSection(Long teacherCourseSectionId) {
        LOGGER.info("TeacherCourseSection request data received at service layer for deleting: {}", teacherCourseSectionId);
        teacherCourseSectionRepository.deleteById(teacherCourseSectionId);
        LOGGER.info("Delete teacher course section data after hit database: {}", teacherCourseSectionId);
    }

    @Override
    public Optional<TeacherCourseSectionEntity> getTeacherCourseSection(Long teacherCourseSectionId) {
        LOGGER.info("TeacherCourseSection request data received at service layer for get teacher course section: {}", teacherCourseSectionId);
        Optional<TeacherCourseSectionEntity> teacherCourseSectionEntity = teacherCourseSectionRepository.findById(teacherCourseSectionId);
        LOGGER.info("Get teacher course section data after hit database: {}", teacherCourseSectionEntity);
        return teacherCourseSectionEntity;
    }

    @Override
    public List<TeacherCourseSectionEntity> getTeacherCourseSectionList() {
        LOGGER.info("TeacherCourseSection request data received at service layer for get teacher course sections");
        return teacherCourseSectionRepository.findAll();
    }
}
