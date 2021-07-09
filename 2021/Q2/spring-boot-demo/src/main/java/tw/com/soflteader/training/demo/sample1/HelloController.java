package tw.com.soflteader.training.demo.sample1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public String hello() {
        return "hello";
    }

    @GetMapping("/ex")
    public String throwEx() {
        throw new RuntimeException("===throwEx===");
    }


}
