package project.board.crew.logic.structure.categories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.board.crew.logic.structure.crew.Crew;

@RestController
@RequestMapping("/api/category")
public class CrewCategoriesController {

    @Autowired
    private CrewCategoriesService crewCategoriesService;


    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrewCategories> createCategory(@ModelAttribute CrewCategories category)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(crewCategoriesService.add(category));
    }

}
