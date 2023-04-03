package project.board.crew.logic.structure.crew;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.board.crew.logic.structure.user.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CrewService {

private final CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository)
    {

        this.crewRepository = crewRepository;
    }

    public List<Crew> getCrews()
    {
        return crewRepository.findAll();
    }

    public List<User> getUsers(Crew crew)
    {
        return crew.getUsers();
    }

    public void assignUser(Crew crew, User user)
    {
        if(crew.getUsers().contains(user))
            throw new IllegalArgumentException();
        else crew.getUsers().add(user);
        crewRepository.save(crew);
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
        if (crewRepository.existsById(crew.getId()))
            throw new IllegalArgumentException();
        return crewRepository.save(crew);
    }

    public void delete(Crew crew)
    {
        if (crewRepository.existsById(crew.getId()))
                crewRepository.delete(crew);
        else throw new IllegalArgumentException();
    }
}
