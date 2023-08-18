package com.example.demo.jpa;

import com.example.demo.SampleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;

@Entity
@Getter
@Setter
public class SampleEntity {

  @Id
  Long id;

  @Column(length = 20)
  String name;

  @Column(name = "amt", precision = 16, scale = 6)
  BigDecimal amount;

  @Column(length = 5)
  @Enumerated(EnumType.STRING)
  SampleType type;

  LocalDateTime time;

  LocalDate date;

  YearMonth yearMonth;

  Year year;

}
