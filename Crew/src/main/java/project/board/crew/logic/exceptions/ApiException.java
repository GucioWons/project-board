package project.board.crew.logic.exceptions;

public class ApiException extends RuntimeException{
    public ApiException(String errorMessage){
        super(errorMessage);
    }
}
