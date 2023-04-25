package project.board.crew.logic.structure.crew;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CrewRepository extends JpaRepository<Crew,Integer> {
    Optional<Crew> findCrewByName(String name);
}
