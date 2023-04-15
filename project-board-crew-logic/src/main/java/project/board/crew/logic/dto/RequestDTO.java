package project.board.crew.logic.dto;

public class RequestDTO {
    private UserDTO userDTO;
    private CrewDTO crewDTO;
    private NotificationDTO notificationDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public CrewDTO getCrewDTO() {
        return crewDTO;
    }

    public void setCrewDTO(CrewDTO crewDTO) {
        this.crewDTO = crewDTO;
    }

    public NotificationDTO getNotificationDTO() {
        return notificationDTO;
    }

    public void setNotificationDTO(NotificationDTO notificationDTO) {
        this.notificationDTO = notificationDTO;
    }
}
