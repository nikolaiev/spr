package beans.exceptions;

public class BatchParseException extends RuntimeException {
    public BatchParseException(String cause){
        super(cause);
    }
}
