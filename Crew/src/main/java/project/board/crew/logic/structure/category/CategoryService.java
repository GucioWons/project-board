package project.board.crew.logic.structure.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(Category category)
    {
        return categoryRepository.save(category);
    }
    public Category findCategoryById(Long id)
    {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category doesnt exist"));
    }
}
