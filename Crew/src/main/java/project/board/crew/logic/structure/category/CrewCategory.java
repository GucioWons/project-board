package project.board.crew.logic.structure.category;


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
public class CrewCategory {

     @Id
    private Long categoryId;

    private String categoryName;

    public CrewCategory(){}
}
