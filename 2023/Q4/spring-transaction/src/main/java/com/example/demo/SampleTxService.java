package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class SampleTxService {

  final SampleDao sampleDao;

  public void saveOne() {
    sampleDao.save(SampleEntity.builder().name("Rhys").build());
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void saveOneRequiresNew() {
    sampleDao.save(SampleEntity.builder().name("Rhys").build());
  }

  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void saveOneNotSupported() {
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
