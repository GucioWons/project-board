package project.board.crew.logic.structure.crew;



import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

import project.board.crew.logic.structure.category.CrewCategory;
import project.board.crew.logic.structure.user.Users;


import javax.persistence.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class Crew {

    @Id
    private Long id;

    private String name;

    @ManyToMany
    List<Users> users;

    @ManyToMany
    List<CrewCategory> crewCategories;


    public Crew() {}


}
