package com.ce.spring.sms.service.impl;

import com.ce.spring.sms.domain.entity.*;
import com.ce.spring.sms.domain.request.EmailRequestModel;
import com.ce.spring.sms.repository.*;
import com.ce.spring.sms.service.EmailService;
import com.ce.spring.sms.utility.GeneratedSequenceNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@RequiredArgsConstructor
public class DbInit implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final SectionRepository departmentRepository;
    private final AddressRepository addressRepository;
    private final CourseRepository courseRepository;
    private final ParentRepository parentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentSectionRepository studentSectionRepository;
    private final TeacherCourseSectionRepository teacherCourseSectionRepository;
    private final EmailService emailService;

    @Override
    public void run(String... args) throws Exception {

        AddressEntity address1 = new AddressEntity("Ethiopia", "Addis Ababa", "Addis Ababa");
        AddressEntity address2 = new AddressEntity("Ethiopia", "Amhara", "Gondar");
        AddressEntity address3 = new AddressEntity("Ethiopia", "Tigray", "Mekele");
        AddressEntity address4 = new AddressEntity("Ethiopia", "Oromia", "Jima");
        AddressEntity address5 = new AddressEntity("Ethiopia", "Somalia", "Jijga");

        List<AddressEntity> addressEntities = Arrays.asList(address1,address2,address3,address4,address5);
        addressRepository.saveAll(addressEntities);

        ParentEntity p1 = new ParentEntity("TestParent", "1234567890", address1, "1251911234567");
        ParentEntity p2 = new ParentEntity("TestParent", "7892581234", address1, "1251911569874");
        ParentEntity p3 = new ParentEntity("TestParent", "4589634567", address2, "1251911657425");
        ParentEntity p4 = new ParentEntity("TestParent", "7849571426", address5, "1251911751536");
        ParentEntity p5 = new ParentEntity("TestParent", "7589863217", address4, "1251911858486");

        List<ParentEntity> parentEntities = Arrays.asList(p1,p2,p3,p4,p5);
        parentRepository.saveAll(parentEntities);

        SectionEntity s1 = new SectionEntity("3A");
        SectionEntity s2 = new SectionEntity("3B");
        SectionEntity s3 = new SectionEntity("4A");
        SectionEntity s4 = new SectionEntity("4B");
        SectionEntity s5 = new SectionEntity("5A");
        SectionEntity s6 = new SectionEntity("5B");
        SectionEntity s7 = new SectionEntity("6");

        List<SectionEntity> sectionEntities = Arrays.asList(s1,s2,s3,s4,s5,s6,s7);
        departmentRepository.saveAll(sectionEntities);

        CourseEntity c1 = new CourseEntity("Biology",GeneratedSequenceNumber.getCourseSequenceNumber("BIO"));
        CourseEntity c2 = new CourseEntity("Chemistry",GeneratedSequenceNumber.getCourseSequenceNumber("CHE"));
        CourseEntity c3 = new CourseEntity("Physics",GeneratedSequenceNumber.getCourseSequenceNumber("PHY"));
        CourseEntity c4 = new CourseEntity("Mathematics",GeneratedSequenceNumber.getCourseSequenceNumber("MAT"));
        CourseEntity c5 = new CourseEntity("English",GeneratedSequenceNumber.getCourseSequenceNumber("ENG"));
        CourseEntity c6 = new CourseEntity("Amharic",GeneratedSequenceNumber.getCourseSequenceNumber("AMH"));

        List<CourseEntity> courseEntities = Arrays.asList(c1,c2,c3,c4,c5,c6);
        courseRepository.saveAll(courseEntities);

        StudentEntity stu1 = new StudentEntity("TestStudent", 35, GeneratedSequenceNumber.getStudentSequenceNumber(),p3);
        StudentEntity stu2 = new StudentEntity("TestStudent",34, GeneratedSequenceNumber.getStudentSequenceNumber(), p2);
        StudentEntity stu3 = new StudentEntity("TestStudent", 28, GeneratedSequenceNumber.getStudentSequenceNumber(), p1);
        StudentEntity stu4 = new StudentEntity("TestStudent", 29, GeneratedSequenceNumber.getStudentSequenceNumber(), p2);
        StudentEntity stu5 = new StudentEntity("TestStudent", 25, GeneratedSequenceNumber.getStudentSequenceNumber(), p4);
        StudentEntity stu6 = new StudentEntity("TestStudent", 26, GeneratedSequenceNumber.getStudentSequenceNumber(), p2);
        StudentEntity stu7 = new StudentEntity("TestStudent", 28, GeneratedSequenceNumber.getStudentSequenceNumber(), p1);
        StudentEntity stu8 = new StudentEntity("TestStudent", 28, GeneratedSequenceNumber.getStudentSequenceNumber(), p5);

        List<StudentEntity> studentEntities = Arrays.asList(stu1,stu2,stu3,stu4,stu5,stu6,stu7,stu8);
        studentRepository.saveAll(studentEntities);

        TeacherEntity t1 = new TeacherEntity("TestTeacher",GeneratedSequenceNumber.getTeacherSequenceNumber(),30, new Date(), "1234567890", "1251911234567",address1);
        TeacherEntity t2 = new TeacherEntity("TestTeacher",GeneratedSequenceNumber.getTeacherSequenceNumber(),33, new Date(), "7892581234", "1251911569874",address2);
        TeacherEntity t3 = new TeacherEntity("TestTeacher",GeneratedSequenceNumber.getTeacherSequenceNumber(),35, new Date(), "4589634567", "1251911657425",address3);
        TeacherEntity t4 = new TeacherEntity("TestTeacher",GeneratedSequenceNumber.getTeacherSequenceNumber(),28, new Date(), "7849571426", "1251911751536",address2);
        TeacherEntity t5 = new TeacherEntity("TestTeacher",GeneratedSequenceNumber.getTeacherSequenceNumber(),36, new Date(), "7589863217", "1251911858486",address4);

        List<TeacherEntity> teacherEntities = Arrays.asList(t1,t2,t3,t4,t5);
        teacherRepository.saveAll(teacherEntities);

        StudentSectionEntity sse1 = new StudentSectionEntity(stu1, s1, new Date(),2021);
        StudentSectionEntity sse2 = new StudentSectionEntity(stu2, s1, new Date(),2021);
        StudentSectionEntity sse3 = new StudentSectionEntity(stu3, s1, new Date(),2021);
        StudentSectionEntity sse4 = new StudentSectionEntity(stu1, s2, new Date(),2020);
        StudentSectionEntity sse5 = new StudentSectionEntity(stu2, s2, new Date(),2020);
        StudentSectionEntity sse6 = new StudentSectionEntity(stu3, s2, new Date(),2020);
        StudentSectionEntity sse7 = new StudentSectionEntity(stu4, s3, new Date(),2019);
        StudentSectionEntity sse8 = new StudentSectionEntity(stu5, s4, new Date(),2018);
        StudentSectionEntity sse9 = new StudentSectionEntity(stu6, s5, new Date(),2017);

        List<StudentSectionEntity> studentSectionEntities = Arrays.asList(sse1,sse2,sse3,sse4,sse5,sse6,sse7,sse8,sse9);
        studentSectionRepository.saveAll(studentSectionEntities);

        TeacherCourseSectionEntity tcs1 = new TeacherCourseSectionEntity(t1,c1,s1);
        TeacherCourseSectionEntity tcs2 = new TeacherCourseSectionEntity(t1,c1,s2);
        TeacherCourseSectionEntity tcs3 = new TeacherCourseSectionEntity(t1,c1,s3);
        TeacherCourseSectionEntity tcs4 = new TeacherCourseSectionEntity(t2,c2,s1);
        TeacherCourseSectionEntity tcs5 = new TeacherCourseSectionEntity(t2,c2,s2);
        TeacherCourseSectionEntity tcs6 = new TeacherCourseSectionEntity(t3,c1,s4);
        TeacherCourseSectionEntity tcs7 = new TeacherCourseSectionEntity(t3,c1,s5);
        TeacherCourseSectionEntity tcs8 = new TeacherCourseSectionEntity(t4,c4,s1);
        TeacherCourseSectionEntity tcs9 = new TeacherCourseSectionEntity(t4,c5,s1);

        List<TeacherCourseSectionEntity> teacherCourseSectionEntities = Arrays.asList(tcs1,tcs2,tcs3,tcs4,tcs5,tcs6,tcs7,tcs8,tcs9);
        teacherCourseSectionRepository.saveAll(teacherCourseSectionEntities);

//        EmailRequestModel e1 = new EmailRequestModel("js@test.com","mt@test.com","Test Email 1","Sending email from John Smith to Moges Teklemariam");
//        emailService.sendingEmail(e1);
    }
}
