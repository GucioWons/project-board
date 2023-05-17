package com.example.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project getProjectById(Integer projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("There is no such project"));
    }

    public List<Project> getActiveProjects() {
        return projectRepository.findAllByDateExpireAfter(LocalDateTime.now());
    }

    public Project assignCrew(Integer projectId, Integer crewId) {
        return projectRepository.findById(projectId)
                .map(project -> assignCrewToProject(project, crewId))
                .orElseThrow(() -> new RuntimeException("There is no such project"));
    }

    private Project assignCrewToProject(Project project, Integer crewId){
        project.setCrew(crewId);
        return projectRepository.save(project);
    }
}
