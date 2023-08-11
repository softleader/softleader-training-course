package tw.com.softleader.training.ooppractice;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public enum PolicyColumn {

  EFF_DATE(Policy::getEffDate),
  PREMIUM(Policy::getPremium),
  ;

  final Function<Policy, Object> getter;

  public <T> Optional<T> get(Policy policy, Class<T> tClass) {
    return Optional.ofNullable(this.getter.apply(policy)).map(tClass::cast);
  }

}
