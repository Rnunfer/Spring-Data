package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    //@Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tutorial", nullable = false, foreignKey = @ForeignKey(name = "FK_TUTORIAL"))
    @JsonIgnore
    @ToString.Exclude
    private Tutorial tutorial;
}
