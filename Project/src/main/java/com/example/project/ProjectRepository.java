package com.example.project;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
    List<Project> findAllByDateExpireBefore(LocalDateTime dateExpire);
}
