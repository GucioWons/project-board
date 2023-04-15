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
        if (crewCategoryRepository.existsById(crewCategory.getCategoryId()))
            throw new IllegalArgumentException();
        return crewCategoryRepository.save(crewCategory);
    }
}
