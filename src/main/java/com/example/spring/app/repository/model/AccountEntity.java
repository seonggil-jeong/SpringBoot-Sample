package com.example.spring.app.repository.model;

import com.example.spring.enums.RoleType;
import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
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


    public static Builder builder() {
        return new Builder();
    }

    private AccountEntity(Builder builder) {
        userId = builder.userId;
        password = builder.password;
        userName = builder.userName;
        nickname = builder.nickname;
        createDate = builder.createDate;
        role = builder.role;
        state = builder.state;
        posts = builder.posts;
    }


    public static final class Builder {
        private String userId;
        private @NonNull String password;
        private @NonNull String userName;
        private @NonNull String nickname;
        private @NonNull Date createDate;
        private @NonNull RoleType role;
        private @NonNull Integer state;
        private List<PostEntity> posts;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder password(@NonNull String val) {
            password = val;
            return this;
        }

        public Builder userName(@NonNull String val) {
            userName = val;
            return this;
        }

        public Builder nickname(@NonNull String val) {
            nickname = val;
            return this;
        }

        public Builder createDate(@NonNull Date val) {
            createDate = val;
            return this;
        }

        public Builder role(@NonNull RoleType val) {
            role = val;
            return this;
        }

        public Builder state(@NonNull Integer val) {
            if (! (val.equals(1) || val.equals(0))) {
                throw new RuntimeException("state must be 1 or 0");
            }
            state = val;
            return this;
        }

        public Builder posts(List<PostEntity> val) {
            posts = val;
            return this;
        }

        public AccountEntity build() {
            return new AccountEntity(this);
        }
    }
}
