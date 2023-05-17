package com.example.project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String title;
    private String company;
    private Integer postedBy;
    private String description;
    private LocalDateTime dateExpire;
    private Integer crew;

    public Project(){
        this.dateExpire = LocalDateTime.now().plusWeeks(2);
    }
}
