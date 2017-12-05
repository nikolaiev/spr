package beans.exceptions;



public class MyObjectNotFoundException extends RuntimeException {
    public MyObjectNotFoundException(String cause){
        super(cause);
    }
}
