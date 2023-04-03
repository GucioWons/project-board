package project.board.crew.logic.structure.crew;



import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;
import project.board.crew.logic.structure.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    List<User> users;

    public Crew() {}


}
