package com.example.demosub1;

import com.example.core.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Demo1Controller {

  final Environment env;

  @GetMapping("/profile")
  public String getProfile() {
    return String.join(",", env.getActiveProfiles());
  }

  @GetMapping
  public String trim(@RequestParam String text) {
    return StringUtils.trimToNull(text);
  }

}
