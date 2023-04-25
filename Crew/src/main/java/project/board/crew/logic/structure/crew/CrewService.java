package project.board.crew.logic.structure.crew;

import org.springframework.stereotype.Service;
import project.board.crew.logic.structure.category.Category;
import project.board.crew.logic.structure.category.CategoryService;
import java.util.List;
import java.util.Set;
@Service
public class CrewService {

    private final CrewRepository crewRepository;
    private final CategoryService categoryService;

    public CrewService(CrewRepository crewRepository, CategoryService categoryService) {
        this.crewRepository = crewRepository;
        this.categoryService = categoryService;
    }

    public List<Crew> getCrews() {
        return crewRepository.findAll();
    }

    public Set<Integer> getMembers(Integer crewId) {
        return crewRepository.findById(crewId).map(
                Crew::getMembers
        ).orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));
    }

    public Set<Category> getCategories(Integer crewId) {
        return crewRepository.findById(crewId).map(
                Crew::getCategories
        ).orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));
    }

    public Crew assignMember(Integer crewId, Integer memberId) {
        return crewRepository.findById(crewId).map(crew -> {
                    crew.getMembers().add(memberId);
                    return crewRepository.save(crew);
                }).orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));

    }

    public Crew assignCategory(Integer crewId, Long categoryId) {
        return crewRepository.findById(crewId).map(crew -> {
                            crew.getCategories().add(categoryService.findCategoryById(categoryId));
                            return crewRepository.save(crew);
                        }
                ).orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));
    }

    public Crew getCrewById(Integer crewId)
    {
        return crewRepository.findById(crewId)
                .orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));
    }

    public Crew getCrewByName(String crewName) {
        return crewRepository.findCrewByName(crewName)
                .orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));
    }

    public Crew create(Crew crew)
    {
        return crewRepository.save(crew);
    }

    public boolean delete(Integer crewId) {
        return crewRepository.findById(crewId).map(crew -> {
                    crewRepository.delete(crew);
                    return true;
                }).orElseThrow(IllegalArgumentException::new);
    }
}
