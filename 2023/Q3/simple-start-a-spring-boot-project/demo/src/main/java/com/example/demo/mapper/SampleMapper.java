package com.example.demo.mapper;

import com.example.demo.Sample;
import com.example.demo.jpa.SampleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SampleMapper {

  Sample fromJpa(SampleEntity entity);

}
