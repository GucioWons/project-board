package project.board.crew.logic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CrewDTO {
    private String name;
    private List<UserDTO> usersDto;
    private List<NotificationDTO> notificationsDto;
}
