package com.ce.spring.sms.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Parent")
@Data
@NoArgsConstructor
public class ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long parentId;

    @NotNull
    @Column(name="Parent_Username", nullable = false)
    private String username;

    @NotNull
    @Column(name = "SSN", unique = true, length = 10, nullable = false)
    private String parentSSN;

    @NotNull
    @ManyToOne
    @JoinColumn(name="Address_Id")
    private AddressEntity addressEntity;

    @NotNull
    @Column(name="Phone_Number", unique = true, length = 13, nullable = false)
    private String parentPhoneNumber;

    public ParentEntity(String username, String parentSSN, AddressEntity addressEntity, String parentPhoneNumber) {
        this.username = username;
        this.parentSSN = parentSSN;
        this.addressEntity = addressEntity;
        this.parentPhoneNumber = parentPhoneNumber;
    }

}
