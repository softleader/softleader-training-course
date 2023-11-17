package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleNoTxService {

  final SampleDao sampleDao;

  public void saveOne() {
    sampleDao.save(SampleEntity.builder().name("Rhys").build());
  }

  public void saveTwo() {
    sampleDao.save(SampleEntity.builder().name("Rhys").build());
    sampleDao.save(SampleEntity.builder().name("Matt").build());
  }

  public void saveTwoError() {
    sampleDao.save(SampleEntity.builder().name("Rhys").build());
    if (true) {
      throw new RuntimeException();
    }
    sampleDao.save(SampleEntity.builder().name("Matt").build());
  }

}
