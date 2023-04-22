package project.board.crew.logic.structure.crew;
import org.springframework.stereotype.Service;
import project.board.crew.logic.structure.category.Category;
import project.board.crew.logic.structure.category.CategoryService;
import project.board.crew.logic.structure.member.Member;
import project.board.crew.logic.structure.member.MemberService;


import java.util.List;

@Service
public class CrewService {

    private final CrewRepository crewRepository;

    private final MemberService memberService;

    private final CategoryService categoryService;

    public CrewService(CrewRepository crewRepository, MemberService memberService, CategoryService categoryService) {
        this.crewRepository = crewRepository;
        this.memberService = memberService;
        this.categoryService = categoryService;
    }

    public List<Crew> getCrews() {
        return crewRepository.findAll();
    }

    public List<Member> getMembers(Long crewId) {
        return crewRepository.findById(crewId).map(
                Crew::getMembers
        ).orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));
    }

    public List<Category> getCategories(Long crewId) {
        return crewRepository.findById(crewId).map(
                Crew::getCategories
        ).orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));
    }

    public Crew assignMember(String crewName, String memberEmail) {
        return crewRepository.findCrewByName(crewName)
                .map(
                    crew -> {
                    Member member = memberService.findMemberByEmail(memberEmail);
                    if(!crew.getMembers().contains(member))
                        crew.getMembers().add(member);
                    else throw new IllegalArgumentException("Member already assigned");
                    return crewRepository.save(crew);
                    }
                ).orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));
    }


    public Crew assignCategory(String crewName, Long categoryId) {
        return crewRepository.findCrewByName(crewName)
                .map(
                        crew -> {
                            Category category = categoryService.findCategoryById(categoryId);
                            if(!crew.getCategories().contains(category))
                                crew.getCategories().add(category);
                            else throw new IllegalArgumentException("Category already assigned");
                            return crewRepository.save(crew);
                        }
                ).orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));
    }


    public Crew getCrewById(Long crewId)
    {
        return crewRepository.findById(crewId)
                .orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));
    }

    public Crew getCrewByName(String crewName)
    {
        return crewRepository.findCrewByName(crewName)
                .orElseThrow(() -> new IllegalArgumentException("Crew doesnt exist"));
    }

    public Crew create(Crew crew)
    {
        return crewRepository.save(crew);
    }

    public Crew delete(Long crewId)
    {
        return crewRepository.findById(crewId)
                .map(crew -> {
                    crewRepository.delete(crew);
                    return crew;
                }).orElseThrow(IllegalArgumentException::new);
    }
}
