package com.example.demo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Policy {

  String policyNo;
  String applicant;
  String mainInsure;
  String mainCoverage;
  LocalDate acceptDate;
  LocalDate effDate;

}
