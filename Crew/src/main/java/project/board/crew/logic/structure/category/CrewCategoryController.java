package project.board.crew.logic.structure.category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CrewCategoryController {

    @Autowired
    CrewCategoryService crewCategoryService;

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrewCategory> createCategory(@ModelAttribute CrewCategory category)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(crewCategoryService.add(category));
    }

}
