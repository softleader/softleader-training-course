package tw.com.softleader.training.spring_web_sample;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tw.com.softleader.training.spring_web_sample.exceptionhandler.CustomExceptionHandler;
import tw.com.softleader.training.spring_web_sample.exceptionhandler.ErrorResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class SampleControllerAdvice {

  final List<CustomExceptionHandler> exceptionHandlers;

  Map<? extends Class<? extends Exception>, CustomExceptionHandler> mapper;

  @PostConstruct
  public void init() {
    mapper = exceptionHandlers.stream()
        .collect(Collectors.toMap(CustomExceptionHandler::support, Function.identity()));
  }

  @ExceptionHandler
  public ResponseEntity<?> handleException(Exception e) {
    var error = Optional.ofNullable(mapper.get(e.getClass()))
        .map(handler -> handler.handle(e))
        .orElseGet(() -> ErrorResponse.builder()
            .errorCode("E999")
            .message(e.getMessage())
            .stackTrace(e.getStackTrace())
            .build());

    return ResponseEntity.status(400).body(error);
  }

}
