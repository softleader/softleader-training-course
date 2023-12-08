package com.example.demo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UwMsg {

  public static final String INPUT_VALIDATE_FAILED = "V001";

  String msgCode;
  String msgDesc;
  Policy policy;
  Coverage coverage;
  Insurance insurance;

}
