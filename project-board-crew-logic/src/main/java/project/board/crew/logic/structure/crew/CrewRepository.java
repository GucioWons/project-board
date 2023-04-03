package project.board.crew.logic.structure.crew;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface CrewRepository extends JpaRepository<Crew,Long> {

    @Override
    List<Crew> findAll();

    @Override
    Optional<Crew> findById(Long id);

    Optional<Crew> findByName(String name);

}
