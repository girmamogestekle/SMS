package com.ce.spring.sms.service.impl;

import com.amazonaws.services.connect.model.UserNotFoundException;
import com.ce.spring.sms.domain.entity.TeacherEntity;
import com.ce.spring.sms.domain.shared.TeacherDto;
import com.ce.spring.sms.repository.TeacherRepository;
import com.ce.spring.sms.service.StorageService;
import com.ce.spring.sms.service.TeacherService;
import com.ce.spring.sms.service.UserService;
import com.ce.spring.sms.utility.GeneratedSequenceNumber;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service(value = "teacherService")
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private static final Logger LOGGER = LogManager.getLogger(ParentServiceImpl.class);
    private final TeacherRepository teacherRepository;
    private final UserService userService;
    private final StorageService storageService;

    @Override
    public TeacherEntity addTeacher(TeacherDto teacherDto) {

        if(!userService.userEnabled(teacherDto.getUsername())){
            throw new UserNotFoundException(teacherDto.getUsername() + " not found!");
        }

        LOGGER.info("Teacher request data received at service layer for adding: {}", teacherDto);
        teacherDto.setTeacherRollNo(GeneratedSequenceNumber.getTeacherSequenceNumber());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TeacherEntity teacherEntity = teacherRepository.save(modelMapper.map(teacherDto, TeacherEntity.class));
        LOGGER.info("Save teacher data after hit database: {}", teacherEntity);

        return teacherEntity;
    }

    @Override
    public TeacherEntity updateTeacher(TeacherEntity teacherEntity) {

        if(!userService.userEnabled(teacherEntity.getUsername())){
            throw new UserNotFoundException(teacherEntity.getUsername() + " not found!");
        }

        LOGGER.info("Teacher request data received at service layer for updating: {}", teacherEntity);
        TeacherEntity upadateTeacherEntity = teacherRepository.save(teacherEntity);
        LOGGER.info("Update teacher data after hit database: {}", upadateTeacherEntity);
        return upadateTeacherEntity;
    }

    @Override
    public TeacherEntity updateTeacherEndingDate(Long teacherId, Date teacherEndingDate) {
        LOGGER.info("Teacher request data received at service layer for updating using ending date: {}", teacherId);
        TeacherEntity teacherEntity = getTeacher(teacherId).orElse(null);
        teacherEntity.setTeacherEndingDate(teacherEndingDate);
        TeacherEntity upadateTeacherEntity = teacherRepository.save(teacherEntity);
        LOGGER.info("Update teacher data after hit database: {}", upadateTeacherEntity);
        return upadateTeacherEntity;
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        LOGGER.info("Teacher request data received at service layer for deleting: {}", teacherId);
        teacherRepository.deleteById(teacherId);
        LOGGER.info("Delete teacher data after hit database: {}", teacherId);
    }

    @Override
    public Optional<TeacherEntity> getTeacher(Long teacherId) {
        LOGGER.info("Teacher request data received at service layer for get teacher: {}", teacherId);
        Optional<TeacherEntity> teacherEntity = teacherRepository.findById(teacherId);
        LOGGER.info("Get teacher data after hit database: {}", teacherEntity);
        return teacherEntity;
    }

    @Override
    public List<TeacherEntity> getTeachers() {
        LOGGER.info("Teacher request data received at service layer for get teachers");
        return teacherRepository.findAll();
    }

    @Override
    public String uploadFile(MultipartFile file, String key) {
        return storageService.uploadFile(file,key);
    }

    @Override
    public byte[] downloadFile(String fileName) {
        return storageService.downloadFile(fileName);
    }

    @Override
    public String deleteFile(String fileName) {
        return storageService.deleteFile(fileName);
    }


}
