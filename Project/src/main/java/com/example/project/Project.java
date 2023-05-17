package com.example.project;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String title;

    private String company;

    @NotNull
    @Column(nullable = false)
    private Integer postedBy;

    @NotEmpty
    @Column(nullable = false)
    private String description;

    private LocalDateTime dateExpire;
    private Integer crew;

    public Project(){
        this.dateExpire = LocalDateTime.now().plusWeeks(2);
    }
}
