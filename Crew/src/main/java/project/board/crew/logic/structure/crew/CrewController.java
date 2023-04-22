package project.board.crew.logic.structure.crew;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.board.crew.logic.structure.category.Category;
import project.board.crew.logic.structure.member.Member;



import java.util.List;


@RestController
@RequestMapping("/api/crews")
public class CrewController {

    @Autowired
    private CrewService crewService;

    @GetMapping()
    public ResponseEntity<List<Crew>> getCrewsList()
    {
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(crewService.getCrews());
    }

    @GetMapping(value = "/crew-members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> getCrewMembers(@RequestParam Long crewId)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.getMembers(crewId));
    }

    @GetMapping(value = "/crew-categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCrewCategories(@RequestParam Long crewId)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.getCategories(crewId));
    }

    @GetMapping(value = "/id/{crewId}")
    public ResponseEntity<Crew> getCrewById(@PathVariable Long crewId)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.getCrewById(crewId));
    }

    @GetMapping(value = "/name/{crewName}")
    public ResponseEntity<Crew> getCrewByName(@PathVariable String crewName)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.getCrewByName(crewName));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Crew> createCrew(@ModelAttribute Crew crew)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(crewService.create(crew));
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Crew> deleteCrew(@RequestParam Long crewId)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.delete(crewId));

    }

    @PostMapping(value = "/assign-member", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Crew> assignMember(@RequestParam String crewName, @RequestParam String memberName)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.assignMember(crewName,memberName));
    }

    @PostMapping(value = "/assign-category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Crew> assignCategory(@RequestParam String crewName, @RequestParam Long categoryId)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.assignCategory(crewName,categoryId));
    }
}
