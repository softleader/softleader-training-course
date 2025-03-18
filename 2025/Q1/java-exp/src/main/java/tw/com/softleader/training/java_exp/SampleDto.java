package tw.com.softleader.training.java_exp;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SampleDto (
    @NotNull
    String name,
    List<String> subs,
    List<String> tags
) {
}