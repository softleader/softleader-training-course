package com.example.demo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;

@Data
public class Sample {

  Long id;
  String name;
  BigDecimal amount;
  SampleType type;
  LocalDateTime time;
  LocalDate date;
  YearMonth yearMonth;
  Year year;

}
