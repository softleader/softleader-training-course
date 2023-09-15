package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import tw.com.softleader.data.jpa.spec.repository.QueryBySpecExecutor;

import java.util.List;

public interface SampleDao extends
    JpaRepository<SampleEntity, Long>, JpaSpecificationExecutor<SampleEntity>, QueryBySpecExecutor<SampleEntity> {

  List<SampleEntity> findByName(String name);

  @Query("from SampleEntity where name = ?1") // JPQL ~= HQL (少了 UNION)
  List<SampleEntity> findByName2(String name);

  @Query(value = "select * from SAMPLE_ENTITY where NAME = ?1", nativeQuery = true) // SQL
  List<SampleEntity> findByName3(String name);

  @Query("select "
      + "name as name, "
      + "type as type "
      + "from SampleEntity")
  List<SampleNameTypeVo> findProjection1();

  @Query("select new com.example.demo.jpa.SampleNameTypeDto( "
      + "name, "
      + "time "
      + ") from SampleEntity")
  List<SampleNameTypeDto> findProjection2();



}
