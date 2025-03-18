package tw.com.softleader.training.spring_web_sample.exceptionhandler;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
public class MethodArgumentNotValidCustomExceptionHandler implements CustomExceptionHandler {
  @Override public Class<? extends Exception> support() {
    return MethodArgumentNotValidException.class;
  }

  @Override public ErrorResponse handle(Exception e) {
    return ErrorResponse.builder()
        .errorCode("V001")
        .message(e.getMessage())
        .stackTrace(e.getStackTrace())
        .build();
  }
}
