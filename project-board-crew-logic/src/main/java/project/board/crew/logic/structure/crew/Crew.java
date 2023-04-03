package project.board.crew.logic.structure.crew;



import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
@Getter
@Setter
@AllArgsConstructor
public class Crew {

    @Id
    private Long id;

    private String name;


    public Crew() {}


}
