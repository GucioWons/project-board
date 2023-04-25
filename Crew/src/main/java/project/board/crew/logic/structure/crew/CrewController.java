package project.board.crew.logic.structure.crew;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.board.crew.logic.structure.category.Category;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/crews")
public class CrewController {
    private final CrewService crewService;
    public CrewController(CrewService crewService)
    {
        this.crewService = crewService;
    }

    @GetMapping()
    public ResponseEntity<List<Crew>> getCrewsList() {
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(crewService.getCrews());
    }

    @GetMapping(value = "/crew-members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Integer>> getCrewMembers(@RequestParam Integer crewId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.getMembers(crewId));
    }

    @GetMapping(value = "/crew-categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Category>> getCrewCategories(@RequestParam Integer crewId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.getCategories(crewId));
    }

    @GetMapping(value = "/id/{crewId}")
    public ResponseEntity<Crew> getCrewById(@PathVariable Integer crewId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.getCrewById(crewId));
    }

    @GetMapping(value = "/name/{crewName}")
    public ResponseEntity<Crew> getCrewByName(@PathVariable String crewName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.getCrewByName(crewName));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Crew> createCrew(@RequestBody Crew crew) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(crewService.create(crew));
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteCrew(@RequestParam Integer crewId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.delete(crewId));

    }

    @PostMapping(value = "/assign-member", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Crew> assignMember(@RequestParam Integer crewId, @RequestParam Integer memberId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.assignMember(crewId,memberId));
    }

    @PostMapping(value = "/assign-category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Crew> assignCategory(@RequestParam Integer crewId, @RequestParam Long categoryId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewService.assignCategory(crewId,categoryId));
    }
}
