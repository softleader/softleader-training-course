package tw.com.softleader.training.spring_web_sample.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
  String errorCode;
  String message;
  StackTraceElement[] stackTrace;
}
