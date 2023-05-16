package project.board.crew.logic.structure.crew;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import project.board.crew.logic.structure.category.Category;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull
    @Column(unique = true, nullable = false)
    private Integer leader;

    @ManyToMany
    private Set<Category> categories;
    @ElementCollection
    private Set<Integer> members = new HashSet<>();

    public Crew() {}

}
