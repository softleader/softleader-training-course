package tw.com.softleader.training.ooppractice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RuleDateRange extends Rule {

  // ---依日期起迄---
  LocalDate startDate;
  @Builder.Default
  boolean startDateInclude = true; // 包含
  LocalDate endDate;
  @Builder.Default
  boolean endDateInclude = true; // 包含

  @Override boolean test(RuleFactor factor) {
    var policies = factor.getPolicies();
    var rule = this;
    var newPolicies = policies.stream().filter(p -> {
      var targetOpt = rule.getColumn().get(p, LocalDate.class);
      if (targetOpt.isEmpty()) {
        return false;
      }
      var target = targetOpt.get();
      var pass = true;
      if (rule.getStartDate() != null) {
        if (rule.startDateInclude) {
          pass &= rule.getStartDate().isBefore(target) || rule.getStartDate().isEqual(target);
        } else {
          pass &= rule.getStartDate().isBefore(target);
        }
      }
      if (rule.getEndDate() != null) {
        if (rule.endDateInclude) {
          pass &= rule.getEndDate().isAfter(target) || rule.getEndDate().isEqual(target);
        } else {
          pass &= rule.getEndDate().isAfter(target);
        }
      }

      return pass;
    }).collect(Collectors.toList());

    factor.setPolicies(newPolicies);

    return !newPolicies.isEmpty();
  }
}
