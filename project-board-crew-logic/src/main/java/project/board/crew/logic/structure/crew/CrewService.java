package project.board.crew.logic.structure.crew;


import org.springframework.stereotype.Service;

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

    public List<Crew> getAllCrews()
    {
        return crewRepository.findAll();
    }

    public Crew getCrew(Crew crew)
    {
        return getAllCrews().stream().filter(g -> g.getName().equals(crew.getName())
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
}
