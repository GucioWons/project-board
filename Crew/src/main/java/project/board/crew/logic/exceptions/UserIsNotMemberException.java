package project.board.crew.logic.exceptions;

public class UserIsNotMemberException extends ApiException{
    public UserIsNotMemberException(String errorMessage) {
        super(errorMessage);
    }
}
