package com.example.demo.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SampleNameTypeDto {

  String name;
  LocalDateTime time;

}
