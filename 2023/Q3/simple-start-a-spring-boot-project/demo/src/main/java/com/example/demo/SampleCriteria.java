package com.example.demo;

import tw.com.softleader.data.jpa.spec.annotation.Spec;
import tw.com.softleader.data.jpa.spec.domain.Like;

public record SampleCriteria(
        @Spec
        Long id,
        @Spec(Like.class)
        String name
) {
}
