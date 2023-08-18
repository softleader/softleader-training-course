package com.example.demo;

import com.example.demo.jpa.SampleDao;
import com.example.demo.jpa.SampleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SampleService {

  final SampleDao sampleDao;

  public List<SampleEntity> query(String name) {
    return sampleDao.findByName(name);
  }

}
