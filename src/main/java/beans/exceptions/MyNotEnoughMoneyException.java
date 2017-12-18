package beans.exceptions;

public class MyNotEnoughMoneyException extends RuntimeException {
    public MyNotEnoughMoneyException(String cause){
        super(cause);
    }
}
