package com.example.demo.rules;

import com.example.demo.Policy;
import com.example.demo.UwMsg;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class RequiredPolicyNo implements UwRule {

  private final Policy policy;

  public RequiredPolicyNo(Policy policy) {
    this.policy = policy;
  }

  @Override
  public List<UwMsg> check() {
    if (StringUtils.isBlank(policy.getPolicyNo())) {
      return List.of(
          UwMsg.builder()
              .msgCode(UwMsg.INPUT_VALIDATE_FAILED)
              .msgDesc("保單號碼為空")
              .policy(policy)
              .build());
    } else {
      return List.of();
    }
  }
}
