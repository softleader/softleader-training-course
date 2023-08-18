package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SampleDao extends
    JpaRepository<SampleEntity, Long>, JpaSpecificationExecutor<SampleEntity> {

  List<SampleEntity> findByName(String name);

}
