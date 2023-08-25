package com.example.demo;

import com.example.demo.jpa.SampleDao;
import com.example.demo.jpa.SampleEntity;
import com.example.demo.mapper.SampleMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SampleService {

  final SampleDao sampleDao;
  final SampleMapper sampleMapper;

  @PostConstruct
  public void init() {
    sampleDao.saveAll(List.of(
       SampleEntity.builder()
           .name("name-1")
           .amount(BigDecimal.valueOf(1))
           .type(SampleType.A)
           .time(LocalDateTime.now())
           .date(LocalDate.now())
           .yearMonth(YearMonth.now())
           .year(Year.now())
           .build(),
        SampleEntity.builder()
            .name("name-2")
            .amount(BigDecimal.valueOf(2))
            .type(SampleType.B)
            .time(LocalDateTime.now())
            .date(LocalDate.now())
            .yearMonth(YearMonth.now())
            .year(Year.now())
            .build(),
        SampleEntity.builder()
            .name("name-3")
            .amount(BigDecimal.valueOf(3))
            .type(SampleType.C)
            .time(LocalDateTime.now())
            .date(LocalDate.now())
            .yearMonth(YearMonth.now())
            .year(Year.now())
            .build()
    ));
  }

  public List<Sample> query() {
    List<SampleEntity> entities = sampleDao.findAll();

    List<Sample> result = new ArrayList<>();
    for (SampleEntity entity : entities) {
      var dto = sampleMapper.fromJpa(entity);;
      result.add(dto);
    }

    return result;
  }

  public Sample insert(Sample dto) {
    var entity = sampleMapper.toJpa(dto);
    var saved = sampleDao.save(entity);
    return sampleMapper.fromJpa(saved);
  }

  public Optional<Sample> query(Long id) {
    return sampleDao.findById(id).map(sampleMapper::fromJpa);
  }
}
