package tw.com.softleader.training.ooppractice;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class RuleEngine {

  public List<String> test(RuleFactor factor, List<Rule> rules) {
    var passRules = new ArrayList<Rule>();

    for (Rule rule : rules) {
      var pass = rule.test(factor);
      if (!pass) {
        break;
      }
      passRules.add(rule);
    }

    return passRules.stream().map(Rule::getReward).collect(Collectors.toList());
  }

  public static void main(String[] args) {

    var ruleEngine = new RuleEngine();
    var ruleFactor = RuleFactor.builder()
        .policies(List.of(
            Policy.builder().effDate(LocalDate.of(2023, 9, 1)).premium(BigDecimal.valueOf(200_0000)).build()
            , Policy.builder().effDate(LocalDate.of(2023, 8, 1)).premium(BigDecimal.valueOf(50_0000)).build()
//            , Policy.builder().effDate(LocalDate.of(2023, 8, 2)).premium(BigDecimal.valueOf(60_0000)).build()
//            , Policy.builder().effDate(LocalDate.of(2023, 8, 3)).premium(BigDecimal.valueOf(70_0000)).build()
        ))
        .build();
    var rules = List.of(
        RuleDateRange.builder().endDate(LocalDate.of(2023, 8, 10))
            .type(Rule.TYPE_DATE_RANGE).column(PolicyColumn.EFF_DATE).build(),
        RuleThreshold.builder()
            .threshold(BigDecimal.valueOf(100_0000)).reward("一萬元獎金")
            .type(Rule.TYPE_THRESHOLD).column(PolicyColumn.PREMIUM).build()
    );

    var rewards = ruleEngine.test(ruleFactor, rules);

    log.warn("獎勵: {}", rewards.stream().filter(Objects::nonNull).collect(Collectors.joining()));
  }

}
