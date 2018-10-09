package tw.com.softleader.time;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HomeController {

  @GetMapping
  public LocalDateTime now() {
    return LocalDateTime.now();
  }
}
