package tw.com.softleader.training._2.web;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SampleController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello World";
  }

  @SneakyThrows
  @GetMapping("/error")
  public String error() {
//    if (name.equals("Rhys")) {
//      throw new IllegalArgumentException("Rhys不可用這支method");
//    }
//    throw new IllegalStateException();
    Thread.sleep(2000);
    throw new UnsupportedOperationException();
  }

  @GetMapping("/member/{eid}/hello")
  public String helloWithName(@PathVariable int eid, @RequestParam String lang) {
    switch (eid) {
      case 132: return lang.equalsIgnoreCase("ZH") ? "Hello 張鼎鑫" : "Hello Rhys";
      default: throw new UnsupportedOperationException("不支援跟這個人say hello " + eid);
    }
  }

  @GetMapping("/member")
  public String queryMember(
    @RequestParam(required = false) Integer eid,
    @RequestParam(required = false) String name
  ) {
    if (eid != null && eid.equals(132)) {
      return "(132)rhys";
    }
    if (name != null && name.equals("Rhys")) {
      return "(132)rhys";
    }
    return "";
  }

}
