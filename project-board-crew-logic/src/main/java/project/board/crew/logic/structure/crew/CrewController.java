package project.board.crew.logic.structure.crew;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/crew")
public class CrewController {

    @Autowired
    private CrewService crewService;

    @PostMapping()
    public ResponseEntity<List<Crew>> getCrewsList()
    {
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(crewService.getAllCrews());
    }

    @PostMapping(value = "/read-crew", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Crew> getCrew(@ModelAttribute Crew crew)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.getCrew(crew));
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Crew> createCrew(@ModelAttribute Crew crew)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.add(crew));
    }

}
