package com.ce.spring.sms.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Student")
@Data
@NoArgsConstructor
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long studentId;

    @Column(name="Student_Username")
    private String username;

    @Column(name="Age", nullable = false)
    private Integer studentAge;

    @Column(name="Roll_No", unique = true, nullable = false)
    private String studentRollNo;

    @ManyToOne
    @JoinColumn(name="Parent_Id")
    private ParentEntity parentEntity;

    public StudentEntity(String username, Integer studentAge, String studentRollNo, ParentEntity parentEntity){
        this.username = username;
        this.studentAge = studentAge;
        this.studentRollNo = studentRollNo;
        this.parentEntity = parentEntity;
    }
}
