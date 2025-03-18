package tw.com.softleader.training.spring_web_sample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sample")
public class SampleController {

  @GetMapping
  public SampleCaseDto query(SampleCondition condition) {
    return SampleCaseDto.builder()
        .build();
  }

  @GetMapping("/{id}")
  public SampleCaseDto queryById(@PathVariable Long id) {
    return SampleCaseDto.builder()
        .id(id)
        .build();
  }

  @GetMapping("/{id}/subs")
  public List<SampleMemberDto> query(@PathVariable Long id) {
    return List.of();
  }

  @PostMapping
  public void create(@RequestBody SampleCaseDto dto) {
  }

  @PutMapping
  public void update(@RequestBody SampleCaseDto dto) {
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
  }

  @Data
  public class SampleCondition {
    String nameLike;
    LocalDate birthDateFrom;
    LocalDate birthDateTo;
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SampleCaseDto {
    Long id;
    String caseNo;
    List<SampleMemberDto> members;
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SampleMemberDto {
    String name;
    LocalDate birthDate;
  }
}
