package beans.exceptions;



public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String cause){
        super(cause);
    }
}
