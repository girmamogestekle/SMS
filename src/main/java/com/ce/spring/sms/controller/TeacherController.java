package com.ce.spring.sms.controller;

import com.ce.spring.sms.domain.entity.TeacherEntity;
import com.ce.spring.sms.domain.request.TeacherRequestModel;
import com.ce.spring.sms.domain.shared.TeacherDto;
import com.ce.spring.sms.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController(value = "teacherController")
@RequestMapping(value = "/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private static final Logger LOGGER = LogManager.getLogger(TeacherController.class);
    private final TeacherService teacherService;

    @PostMapping(value = "/add")
    public TeacherEntity addTeacher(@RequestBody TeacherRequestModel teacherRequestModel){
        LOGGER.info("Teacher request data received at controller layer for post mapping: {}", teacherRequestModel);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TeacherDto teacherDto = modelMapper.map(teacherRequestModel, TeacherDto.class);

        return teacherService.addTeacher(teacherDto);
    }

    @PutMapping(value = "/update")
    public TeacherEntity updateTeacher(@RequestBody TeacherEntity teacherEntity){
        LOGGER.info("Teacher request data received at controller layer for put mapping: {}", teacherEntity);
        return teacherService.updateTeacher(teacherEntity);
    }

    @GetMapping(value = "/update/endingDate/{teacherId}/{endingDate}")
    public TeacherEntity updateTeacherEndingDate(@PathVariable Long teacherId, @PathVariable String endingDate) throws ParseException {
        LOGGER.info("Teacher request data received at controller layer for put mapping: TeacherId:{}, EndingDate:{}", teacherId,endingDate);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
        Date newEndingDate = formatter.parse(endingDate);
        return teacherService.updateTeacherEndingDate(teacherId,newEndingDate);
    }

    @DeleteMapping(value = "/delete/{teacherId}")
    public void deleteTeacher(@PathVariable Long teacherId){
        LOGGER.info("Teacher request data received at controller layer for delete mapping: {}", teacherId);
        teacherService.deleteTeacher(teacherId);
    }

    @GetMapping(value = "/get/{teacherId}")
    public TeacherEntity getTeacher(@PathVariable Long teacherId){
        LOGGER.info("Teacher request data received at controller layer for get mapping: {}", teacherId);
        return teacherService.getTeacher(teacherId).orElse(null);
    }

    @GetMapping(value = {"/","/gets"})
    public List<TeacherEntity> getTeachers(){
        LOGGER.info("Request teachers at controller layer for get mapping");
        return teacherService.getTeachers();
    }

    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value="file") MultipartFile file){
        return new ResponseEntity<>(teacherService.uploadFile(file, "teacher"), HttpStatus.OK);
    }

    @GetMapping("/file/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName){
        byte[] data = teacherService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; fileName=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/file/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName){
        return new ResponseEntity<>(teacherService.deleteFile(fileName), HttpStatus.OK);
    }
}
