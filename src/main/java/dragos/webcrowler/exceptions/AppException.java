package dragos.webcrowler.exceptions;

public class AppException extends RuntimeException {
    public AppException(final String exceptionMsg) {
        super(exceptionMsg);
    }
}
