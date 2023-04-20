package project.board.crew.logic.structure.crew;
import org.springframework.stereotype.Service;
import project.board.crew.logic.structure.category.CrewCategory;
import project.board.crew.logic.structure.member.Member;



import java.util.List;
import java.util.Optional;

@Service
public class CrewService {


private final CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public List<Crew> getCrews()
    {
        return crewRepository.findAll();
    }

    public List<Member> getMembers(Crew crew)
    {
        return crew.getMembers();
    }

    public List<CrewCategory> getCategories(Crew crew)
    {
        return crew.getCrewCategories();
    }

    public void assignUser(Crew crew, Member member)
    {
        Optional<Crew> updateCrew = crewRepository.findById(crew.getId());
        boolean usersStream = updateCrew.get().getMembers().stream().anyMatch(g -> g.getMemberId().equals(member.getMemberId()));
        if(!usersStream)
        {
            updateCrew.get().getMembers().add(member);
            crewRepository.save(updateCrew.get());
        }
        else throw new IllegalArgumentException();
    }

    public void assignCategory(Crew crew, CrewCategory crewCategory)
    {
        Optional<Crew> updateCrew = crewRepository.findById(crew.getId());
        boolean categoriesStream = updateCrew.get().getCrewCategories().stream().anyMatch(g -> g.getCategoryId().equals(crewCategory.getCategoryId()));
        if(!categoriesStream)
        {
            updateCrew.get().getCrewCategories().add(crewCategory);
            crewRepository.save(updateCrew.get());
        }
        else throw new IllegalArgumentException();
    }

    public Crew getCrew(Crew crew)
    {
        return getCrews().stream().filter(g -> g.getName().equals(crew.getName())
                || g.getId().equals(crew.getId()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Crew add(Crew crew)
    {
        return crewRepository.save(crew);
    }

    public void delete(Crew crew)
    {
        crewRepository.delete(crew);
    }
}
