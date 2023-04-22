package project.board.crew.logic.structure.crew;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface CrewRepository extends JpaRepository<Crew,Long> {
    Optional<Crew> findCrewByName(String name);
}
