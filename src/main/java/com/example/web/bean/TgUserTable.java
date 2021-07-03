package com.example.web.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "tg_user")
public class TgUserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "date_of_birthday")
    private Date dateOfBirthday;

   @ElementCollection(targetClass = UserRoles.class, fetch = FetchType.EAGER)
   @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
   @Enumerated(EnumType.STRING)
    private Set<UserRoles> roles;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="course_name")     // insertable=false, updatable=false)
    private  CourseTable courseUser;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="group_number") // insertable=false, updatable=false)
    private GroupTable groupUser;

    @Column(name = "block_user")
    private Boolean blockUser;

    @Column(name = "payment")
    private Boolean payment;

    @Column(name = "chat_id")
    private Long chatId;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "studentName", cascade = CascadeType.REMOVE)
    private HwFromStudentTable fromStudent;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userName", cascade = CascadeType.REMOVE)
    private UserAnswerTable usersAnswers;

}
