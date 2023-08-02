package com.ce.spring.sms.configuration;

import com.ce.spring.sms.exception.SMSAuthenticationEntryPoint;
import com.ce.spring.sms.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SMSAuthenticationEntryPoint smsAuthenticationEntryPoint;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    private static final String[] unAuthenticatedList = {
//            "/api/v1/authentication/register",
            "/h2-console/**",
            "/api/v1/course/", "/api/v1/course/gets", "/api/v1/course/get/{courseId}",
            "/api/v1/address/", "/api/v1/address/gets", "/api/v1/address/get/{addressId}",
            "/api/v1/course/","/api/v1/course/gets", "/api/v1/course/get/{courseId}",
            "/api/v1/parent/", "/api/v1/parent/gets", "/api/v1/parent/get/{parentId}",
            "/api/v1/section/", "/api/v1/section/gets", "/api/v1/section/get/{sectionId}",
            "/api/v1/student/section/", "/api/v1/student/section/gets", "/api/v1/student/section/get/{studentSectionId}",
            "/api/v1/teacher/course/section/", "/api/v1/teacher/course/section/gets", "/api/v1/teacher/course/section/get/{teacherCourseSectionId}",
            "/api/v1/teacher/", "/api/v1/teacher/gets", "/api/v1/teacher/get/{teacherId}",
            "/api/v1/student/", "/api/v1/student/gets", "/api/v1/student/get/{studentId}",
            "/_appmap/**"
    };

    private static final String[] adminAuthenticatedList = {
            "/api/v1/authentication/register",
            "/api/v1/address/add", "/api/v1/address/update", "/api/v1/address/delete/{addressId}",
            "/api/v1/student/add", "/api/v1/student/update", "/api/v1/student/delete/{studentId}",
            "/api/v1/section/add", "/api/v1/section/update", "/api/v1/section/delete/{sectionId}",
            "/api/v1/parent/add","/api/v1/parent/delete/{parentId}",
            "/api/v1/teacher/add", "/api/v1/teacher/update", "/api/v1/teacher/delete/{teacherId}", "/api/v1/teacher/update/endingDate/{teacherId}/{endingDate}"
    };

    private static final String[] adminTeacherAuthenticateList = {
            "/api/v1/course/add", "/api/v1/course/update", "/api/v1/course/delete/{courseId}",
            "/api/v1/student/section/add", "/api/v1/student/section/update", "/api/v1/student/section/delete/{studentSectionId}",
            "/api/v1/teacher/course/section/add", "/api/v1/teacher/course/section/update", "/api/v1/teacher/course/section/delete/{teacherCourseSectionId}"
    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(unAuthenticatedList).permitAll()
                .antMatchers(adminAuthenticatedList).hasAnyAuthority("ADMIN")
                .antMatchers(adminTeacherAuthenticateList).hasAnyAuthority("ADMIN","TEACHER")
                .antMatchers("/api/v1/parent/update").hasAnyAuthority("ADMIN","PARENT")
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .httpBasic().realmName("SMS").authenticationEntryPoint(smsAuthenticationEntryPoint);

    }

    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

}
