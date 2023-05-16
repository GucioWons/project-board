package project.board.crew.logic.exceptions;

public class SameLeaderException extends ApiException{
    public SameLeaderException(String errorMessage) {
        super(errorMessage);
    }
}
