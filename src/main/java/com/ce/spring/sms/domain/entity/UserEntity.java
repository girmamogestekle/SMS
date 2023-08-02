package com.ce.spring.sms.domain.entity;

import com.ce.spring.sms.domain.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="Id")
    private long id;
    @Indexed(unique = true)
    @NotNull
    @Column(name="User_Name", nullable = false)
    private String username;
    @NotNull
    @Column(name="First_Name", nullable = false)
    private String firstName;
    @Column(name="Middle_Name")
    private String middleName;
    @NotNull
    @Column(name="Last_Name", nullable = false)
    private String lastName;
    @Column(unique = true,nullable = false)
    @Email
    private String email;
    @NotNull
    @Column(name="Password", nullable = false)
    private String password;
    @NotNull
    @Column(name="Enabled", nullable = false)
    private boolean enabled;

    @NotNull
    @Column(name="Full_Name", nullable = false)
    private String fullName;

    @NotNull
    @Column(name="CreatedAt", nullable = false)
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private LocalDateTime createdAt;

    @Column(name="ModifiedAt")
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private LocalDateTime modifiedAt;

    @NotNull
    @Column(name="Role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity(String username, String firstName, String middleName, String lastName, String email, String password, boolean enabled, LocalDateTime createdAt, Role role) {
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.role = role;
    }

    public String getFullName(){
        return firstName + " " + middleName + " " + lastName;
    }

}
