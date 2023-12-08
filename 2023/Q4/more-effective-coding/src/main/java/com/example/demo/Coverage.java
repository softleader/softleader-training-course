package com.example.demo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Coverage {

  String policyNo;
  String coverageNo;
  String productCode;
  String campaignCode;
  LocalDate effDate;

}
