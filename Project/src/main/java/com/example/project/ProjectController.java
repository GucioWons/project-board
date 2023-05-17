package com.example.project;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.createProject(project));
    }

    @GetMapping
    public ResponseEntity<Project> getProjectById(@RequestParam Integer projectId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getProjectById(projectId));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Project>> getActiveProjects(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getActiveProjects());
    }
}
