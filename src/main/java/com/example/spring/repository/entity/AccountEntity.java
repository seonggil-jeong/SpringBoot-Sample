package com.example.spring.repository.entity;

import com.example.spring.enums.RoleType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {
    @Id
    private String userId;

    @NonNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NonNull
    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @NonNull
    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    @NonNull
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private RoleType role;

    @NonNull
    @Column(name = "STATE", length = 2)
    private Integer state;


    @OneToMany(mappedBy = "createUserId")
    private List<PostEntity> posts;


    public Integer changeState(Integer state) {
        this.state = state;

        return state;
    }
}
