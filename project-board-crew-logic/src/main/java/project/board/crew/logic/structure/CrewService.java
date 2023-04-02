package project.board.crew.logic.structure;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Crew> getById(Long id)
    {
        return crewRepository.findById(id);
    }

    public Optional<Crew> getByName(String name)
    {
        return crewRepository.findByName(name);
    }

    public String add(Crew crew)
    {
        Optional<Crew> createdCrew = crewRepository.findByName(crew.getName());
        if(createdCrew.isPresent())
            return "Załoga o nazwie " + crew.getName() + " już istnieje";
        else
        {
            crewRepository.save(crew);
            return "Utworzono załogę o nazwie: " + crew.getName();
        }
    }
}
