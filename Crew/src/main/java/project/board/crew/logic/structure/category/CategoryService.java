package project.board.crew.logic.structure.category;

import org.springframework.stereotype.Service;
import project.board.crew.logic.structure.crew.Crew;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category create(Category category)
    {
        return categoryRepository.save(category);
    }
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category doesnt exist"));
    }
}
