package project.board.crew.logic.structure;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/crew")
public class CrewController {

    @Autowired
    private CrewService crewService;

    @PostMapping(value = "/list")
    public ResponseEntity<List<Crew>> getCrewsList()
    {
       return ResponseEntity
               .status(200)
               .body(crewService.getAllCrews());
    }

    @PostMapping(value = "/read-crew/{name}")
    public ResponseEntity<Optional<Crew>> getCrewByName(@PathVariable(name = "name") String name)
    {
        return ResponseEntity
                .status(200)
                .body(crewService.getByName(name));
    }

    @RequestMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCrew(@ModelAttribute Crew crew)
    {
        String response = crewService.add(crew);
        return ResponseEntity
                .status(200)
                .body(response);
    }

}
