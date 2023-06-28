package com.example.spring.app.repository.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    @Column
    private String content;

    @Column
    private Date createDate;

    private String userId;

    private Long postSeq;

}
