package project.board.crew.logic.exceptions;

public class NoCrewException extends ApiException{
    public NoCrewException(String errorMessage) {
        super(errorMessage);
    }
}
