package data.Exception;

public class BadDeleteRequstException extends Exception{
    /**
     * Constructs a BadDeleteRequestException with the given error message.
     *
     * @param message the mistake message
     */
    public BadDeleteRequstException(String message){
        super(message);
    }
}
