package com.example.demo.mapper;

import com.example.demo.Sample;
import com.example.demo.jpa.SampleEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SampleMapper {

  @Mapping(target = "t", source = "time")
  @Mapping(target = "d", source = "date")
  @Mapping(target = "ym", source = "yearMonth")
  @Mapping(target = "y", source = "year")
  @Mapping(target = "type", constant = "D")
  Sample fromJpa(SampleEntity entity);

  @InheritInverseConfiguration
  SampleEntity toJpa(Sample dto);

}
