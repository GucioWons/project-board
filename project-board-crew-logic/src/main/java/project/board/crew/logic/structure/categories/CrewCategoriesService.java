package project.board.crew.logic.structure.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrewCategoriesService {

    private final CrewCategoriesRepository crewCategoriesRepository;

    public CrewCategoriesService(CrewCategoriesRepository crewCategoriesRepository) {
        this.crewCategoriesRepository = crewCategoriesRepository;
    }

    public CrewCategories add(CrewCategories crewCategories)
    {
        if (crewCategoriesRepository.existsById(crewCategories.getCategoryId()))
            throw new IllegalArgumentException();
        return crewCategoriesRepository.save(crewCategories);
    }
}
