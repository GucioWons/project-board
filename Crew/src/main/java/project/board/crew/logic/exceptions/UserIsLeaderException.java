package project.board.crew.logic.exceptions;

public class UserIsLeaderException extends ApiException{
    public UserIsLeaderException(String errorMessage) {
        super(errorMessage);
    }
}
