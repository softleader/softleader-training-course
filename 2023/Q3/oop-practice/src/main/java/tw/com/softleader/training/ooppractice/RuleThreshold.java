package tw.com.softleader.training.ooppractice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RuleThreshold extends Rule {

  // ---累計金額達門檻---
  BigDecimal threshold; // 門檻
  @Builder.Default
  boolean thresholdInclude = true; // 包含

  @Override boolean test(RuleFactor factor) {
    var policies = factor.getPolicies();
    var rule = this;
    var sum = policies.stream()
        .map(p -> rule.getColumn().get(p, BigDecimal.class).orElse(BigDecimal.ZERO))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    if (rule.thresholdInclude) {
      return sum.compareTo(rule.getThreshold()) >= 0;
    } else {
      return sum.compareTo(rule.getThreshold()) > 0;
    }
  }
}
