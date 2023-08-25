package com.example.demo.http;

import com.example.demo.Sample;
import com.example.demo.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

  final SampleService sampleService;

  @GetMapping
  public List<Sample> query(
      @RequestParam(required = false) Long id,
      @RequestParam(required = false) String name) {

    log.info("id:{}, name:{}", id, name);

    return sampleService.query();
  }

  @GetMapping("/{id}")
  public Sample queryOne(@PathVariable Long id) {
    return sampleService.query(id)
        .orElseThrow(() -> new IllegalArgumentException("此ID不存在:" + id));
  }

  @PostMapping
  public Sample save(@RequestBody Sample dto) {
    return sampleService.insert(dto);
  }

}
