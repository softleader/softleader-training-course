package tw.com.softleader.training.mapstruct_training;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sample-pack")
@RestController
@RequiredArgsConstructor
public class SamplePackController {

  final SamplePackService samplePackService;

  @PostMapping
  public SamplePackEntity save(@RequestBody SamplePackUpdateCmd cmd) {
    return samplePackService.save(cmd);
  }
}
