package project.board.crew.logic.structure.crew;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.board.crew.logic.exceptions.*;
import project.board.crew.logic.structure.category.Category;
import project.board.crew.logic.structure.category.CategoryService;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CrewService {

    private final CrewRepository crewRepository;
    private final CategoryService categoryService;

    public List<Crew> getCrews() {
        return crewRepository.findAll();
    }

    public Set<Integer> getMembers(Integer crewId) {
        return crewRepository.findById(crewId).map(
                Crew::getMembers
        ).orElseThrow(() -> new NoCrewException("Crew doesnt exist"));
    }

    public Set<Category> getCategories(Integer crewId) {
        return crewRepository.findById(crewId).map(
                Crew::getCategories
        ).orElseThrow(() -> new NoCrewException("Crew doesnt exist"));
    }

    public Crew assignMember(Integer crewId, Integer memberId) {
        return crewRepository.findById(crewId)
                .map(crew -> addMember(crew, memberId))
                .orElseThrow(() -> new NoCrewException("Crew doesnt exist"));

    }

    private Crew addMember(Crew crew, Integer memberId){
        if(!crew.getLeader().equals(memberId)){
            crew.getMembers().add(memberId);
            return crewRepository.save(crew);
        }else{
            throw new UserIsLeaderException("That user is already leader of this crew");
        }
    }

    public Crew assignCategory(Integer crewId, Long categoryId) {
        return crewRepository.findById(crewId).map(crew -> {
                            crew.getCategories().add(categoryService.findCategoryById(categoryId));
                            return crewRepository.save(crew);
                        }
                ).orElseThrow(() -> new NoCrewException("Crew doesnt exist"));
    }

    public Crew getCrewById(Integer crewId) {
        return crewRepository.findById(crewId)
                .orElseThrow(() -> new NoCrewException("Crew doesnt exist"));
    }

    public Crew getCrewByName(String crewName) {
        return crewRepository.findCrewByName(crewName)
                .orElseThrow(() -> new NoCrewException("Crew doesnt exist"));
    }

    public Crew create(Crew crew) {
        return crewRepository.save(crew);
    }

    public boolean delete(Integer crewId) {
        return crewRepository.findById(crewId).map(crew -> {
                    crewRepository.delete(crew);
                    return true;
                }).orElseThrow(() -> new NoCrewException("Crew doesnt exist"));
    }

    public Crew updateLeader(Integer crewId, Integer userId){
        return crewRepository.findById(crewId).map(
                crew -> changeLeader(crew, userId))
                .orElseThrow(() -> new NoCrewException("Crew doesn't exist!"));
    }

    private Crew changeLeader(Crew crew, Integer userId){
        if(!crew.getLeader().equals(userId)){
            return promoteMemberToLeader(crew, userId);
        }else{
            throw new SameLeaderException("Leader is the same!");
        }
    }

    private Crew promoteMemberToLeader(Crew crew, Integer userId){
        if (crew.getMembers().contains(userId)) {
            crew.getMembers().remove(userId);
            crew.getMembers().add(crew.getLeader());
            crew.setLeader(userId);
            return crewRepository.save(crew);
        }else{
            throw new UserIsNotMemberException("That user is not member of this crew!");
        }
    }
}
