package tw.com.softleader.training.ooppractice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class Rule {

  public static final int TYPE_THRESHOLD = 1; // 篩選類型
  public static final int TYPE_DATE_RANGE = 2; // 依日期起迄

  int type; // 篩選類型
  PolicyColumn column; // 欄位

  // ---獎勵內容---
  String reward;

  boolean test(RuleFactor factor) {
    throw new UnsupportedOperationException("不支援運算的Rule類型:" + this.getClass().getSimpleName());
  }

}
