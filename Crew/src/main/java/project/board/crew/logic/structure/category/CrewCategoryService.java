package project.board.crew.logic.structure.category;

import org.springframework.stereotype.Service;

@Service
public class CrewCategoryService {

    private final CrewCategoryRepository crewCategoryRepository;

    public CrewCategoryService(CrewCategoryRepository crewCategoryRepository) {
        this.crewCategoryRepository = crewCategoryRepository;
    }

    public CrewCategory add(CrewCategory crewCategory)
    {
        return crewCategoryRepository.save(crewCategory);
    }
}
