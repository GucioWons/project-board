package project.board.crew.logic.structure.crew;



import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

import project.board.crew.logic.structure.category.Category;
import project.board.crew.logic.structure.member.Member;



import javax.persistence.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    List<Member> members;

    @ManyToMany
    List<Category> categories;


    public Crew() {}


}
