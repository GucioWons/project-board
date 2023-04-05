package project.board.crew.logic.structure.categories;


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
public class CrewCategories {

    @Id
    @Column(name = "id", nullable = false)
    private Long categoryId;

    private String categoryName;

    public CrewCategories(){}
}
