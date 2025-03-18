package tw.com.softleader.training.java_exp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(exception = MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {
    var errorMsgs = e.getFieldErrors().stream()
        .map(fe -> "%s: %s".formatted(fe.getField(), fe.getDefaultMessage()))
        .toList();
    return ResponseEntity.status(500).body(Map.of(
        "msg", "欄位檢核失敗",
        "errors", errorMsgs));
  }

}
