package com.example.demo.http;

import com.example.demo.Sample;
import com.example.demo.SampleCriteria;
import com.example.demo.SampleService;
import com.example.demo.jpa.SampleEntity;
import com.example.demo.jpa.SampleNameTypeDto;
import com.example.demo.jpa.SampleNameTypeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
  public Page<Sample> query(
      @RequestParam(required = false) Long id,
      @RequestParam(required = false) String name,
      Pageable pageable) {

    log.info("id:{}, name:{}", id, name);
    var spec = Specification.<SampleEntity>where(null);
    if (id != null) {
      spec = spec.and(Specification.where((entity, q, cb) -> cb.equal(entity.get("id"), id)));
    }
    if (name != null && !name.isBlank()) {
      spec = spec.and(Specification.where((entity, q, cb) -> cb.like(entity.get("name"), "%" + name + "%")));
    }

    return sampleService.query(spec, pageable);
  }

  @GetMapping("/spec")
  public Page<Sample> queryBySpec(SampleCriteria criteria, Pageable pageable) {
    return sampleService.queryBySpec(criteria, pageable);
  }

  @GetMapping("/projection/1")
  public List<SampleNameTypeVo> queryProjection1() {
    return sampleService.queryProjection1();
  }

  @GetMapping("/projection/2")
  public List<SampleNameTypeDto> queryProjection2() {
    return sampleService.queryProjection2();
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
