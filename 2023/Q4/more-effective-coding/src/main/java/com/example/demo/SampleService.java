package com.example.demo;

import com.example.demo.rules.RequiredPolicyEffDate;
import com.example.demo.rules.RequiredPolicyNo;
import com.example.demo.rules.UwRule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.example.demo.UwMsg.INPUT_VALIDATE_FAILED;

@Service
public class SampleService {

  private static Optional<List<UwMsg>> checkEffDate(List<Policy> policies, List<Coverage> coverages, Policy policy) {
    var msgs = new ArrayList<UwMsg>();
    var polMaxEffDateOpt = policies.stream().map(Policy::getEffDate).max(Comparator.naturalOrder());
    if (polMaxEffDateOpt.isEmpty()) {
      msgs.add(UwMsg.builder()
          .msgCode(INPUT_VALIDATE_FAILED)
          .msgDesc("保單缺少生效日")
          .policy(policy)
          .build());
    } else {
      var polMaxEffDate = polMaxEffDateOpt.get();
      var covMinEffDateOpt = coverages.stream().map(Coverage::getEffDate).min(Comparator.naturalOrder());
      if (covMinEffDateOpt.isEmpty()) {
        msgs.add(UwMsg.builder()
            .msgCode(INPUT_VALIDATE_FAILED)
            .msgDesc("附約缺少生效日")
            .policy(policy)
            .build());
      } else {
        var covMinEffDate = covMinEffDateOpt.get();
        if (polMaxEffDate.isAfter(covMinEffDate)) {
          msgs.add(UwMsg.builder()
              .msgCode(INPUT_VALIDATE_FAILED)
              .msgDesc("保單生效日晚於附約生效日")
              .policy(policy)
              .build());
        }
      }
    }
    return Optional.of(msgs);
  }

  private static Optional<UwMsg> checkApplicant(Policy policy) {
    if (StringUtils.isBlank(policy.getApplicant())) {
      return Optional.of(UwMsg.builder()
          .msgCode(INPUT_VALIDATE_FAILED)
          .msgDesc("要保人為空")
          .policy(policy)
          .build());
    }
    return Optional.empty();
  }

  private static void checkPolicyNo(Policy policy, ArrayList<UwMsg> msgs) {
    if (StringUtils.isBlank(policy.getPolicyNo())) {
      msgs.add(UwMsg.builder()
          .msgCode(INPUT_VALIDATE_FAILED)
          .msgDesc("保單號碼為空")
          .policy(policy)
          .build());
    }
  }

  public List<UwMsg> checkUw1(List<Policy> policies, List<Coverage> coverages, List<Insurance> insurances) {
    var msgs = new ArrayList<UwMsg>();
    for (Policy policy : policies) {
      // 保單基本檢查
      checkPolicyNo(policy, msgs);
      checkApplicant(policy).ifPresent(msgs::add);

      // 保單與附約檢查
      checkEffDate(policies, coverages, policy).ifPresent(msgs::addAll);
    }
    return msgs;
  }

  public List<UwMsg> checkUw2(List<Policy> policies, List<Coverage> coverages, List<Insurance> insurances) {
    var rules = new ArrayList<UwRule>();
    for (Policy policy : policies) {
      rules.add(new RequiredPolicyNo(policy));
      rules.add(new RequiredPolicyEffDate(policy));
    }

    return rules.stream()
        .map(UwRule::check)
        .flatMap(List::stream)
        .toList();
  }

}
