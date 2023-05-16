package project.board.crew.logic.structure.crew;


import lombok.Getter;
import lombok.Setter;
import project.board.crew.logic.structure.category.Category;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer leader;
    @ManyToMany
    private Set<Category> categories;
    @ElementCollection
    private Set<Integer> members = new HashSet<>();

    public Crew() {}

}
