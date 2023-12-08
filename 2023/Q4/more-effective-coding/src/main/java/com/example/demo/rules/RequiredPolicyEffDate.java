package com.example.demo.rules;

import com.example.demo.Policy;
import com.example.demo.UwMsg;

import java.util.List;

public class RequiredPolicyEffDate implements UwRule {

  private final Policy policy;

  public RequiredPolicyEffDate(Policy policy) {
    this.policy = policy;
  }

  @Override
  public List<UwMsg> check() {
    if (policy.getEffDate() != null) {
      return List.of(
          UwMsg.builder()
              .msgCode(UwMsg.INPUT_VALIDATE_FAILED)
              .msgDesc("保單生效日為空")
              .policy(policy)
              .build());
    } else {
      return List.of();
    }
  }
}
