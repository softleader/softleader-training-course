package tw.com.softleader.training.mvcdemo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.regex.Pattern.MULTILINE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Tag(name = "Hello API", description = "spring-web demo用")
@RestController
public class HelloWorldController {

    @Operation(description = "show html字串")
    @GetMapping
    public String hello() {
        return "<h1 style='color: red'>world</h1>";
    }

    @Operation(description = "show 物件 json")
    @GetMapping(value = "/obj", produces = "application/xml")
    public HelloObj helloObject() {
        return new HelloObj("world", 2021);
    }

    @Operation(description = "以RequestParam接收參數")
    @RequestMapping(value = "/param", method = { GET, POST })
    public HelloObj helloObject(
        @Parameter(name = "名稱", example = "rhys") @RequestParam(required = false) String name,
        @Parameter(name = "數字", example = "111") @RequestParam(required = false) Integer number) {
        return new HelloObj(name, number);
    }

    @Operation(description = "接收Form表單參數")
    @PostMapping("/form")
    public HelloObj helloObjectFromForm(HelloObj HelloObj) {
        return HelloObj;
    }

    @Operation(description = "接收Json參數")
    @PostMapping("/json")
    public HelloObj helloObjectFromJson(@RequestBody HelloObj HelloObj) {
        return HelloObj;
    }

    @Operation(description = "檔案上傳")
    @SneakyThrows
    @PostMapping("/upload")
    public HelloObj helloObjectFromUpload(@RequestParam MultipartFile helloFile) {
        byte[] bytes = helloFile.getBytes();
        String s = new String(bytes, UTF_8);
        Pattern nameRegex = Pattern.compile("name=(\\S*)", MULTILINE);
        Pattern numberRegex = Pattern.compile("number=(\\S*)", MULTILINE);

        Matcher nameMatcher = nameRegex.matcher(s);
        Matcher numberMatcher = numberRegex.matcher(s);

        String name = Optional.of(nameMatcher)
            .filter(Matcher::matches).map(m -> m.group(1)).orElse(null);
        Integer number = Optional.of(numberMatcher)
            .filter(Matcher::matches).map(m -> m.group(1)).map(Integer::parseInt).orElse(null);

        return new HelloObj(name, number);
    }

}
