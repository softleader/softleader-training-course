package tw.com.softleader.training.java_exp;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public record SampleController(
    SampleService sampleService
) {

  @PostMapping(value = "/hello")
  public Object hello(@RequestBody @Valid SampleDto dto) {
    return dto;
  }

  @PostMapping(value = "/sample/add")
  public void add(@RequestBody @Valid SampleDto dto) {
    sampleService.addSample(dto.name());
  }

  @PostMapping(value = "/sample/sub/add")
  public void addSub(@RequestBody @Valid SampleDto dto) {
    sampleService.addSubSample(dto.name(), dto.subs());
  }

  @PostMapping(value = "/sample/tag/add")
  public void addTag(@RequestBody @Valid SampleDto dto) {
    sampleService.addTag(dto.name(), dto.tags());
  }



}
