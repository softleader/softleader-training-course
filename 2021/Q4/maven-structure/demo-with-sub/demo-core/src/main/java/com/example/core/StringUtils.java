package com.example.core;

import java.util.Optional;

public class StringUtils {

  public static boolean notEmpty(String src) {
    return src != null && !src.isEmpty();
  }

  public static String trimToNull(String src) {
    return Optional.ofNullable(src)
        .map(String::trim)
        .filter(StringUtils::notEmpty)
        .orElse(null);
  }

}
