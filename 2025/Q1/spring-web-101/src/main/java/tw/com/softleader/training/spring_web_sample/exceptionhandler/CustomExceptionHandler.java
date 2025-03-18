package tw.com.softleader.training.spring_web_sample.exceptionhandler;

public interface CustomExceptionHandler {

  Class<? extends Exception> support();

  ErrorResponse handle(Exception e);

}
