package edu.hw10.Task1;

import edu.hw10.Task1.Annotation.Max;
import edu.hw10.Task1.Annotation.Min;
import edu.hw10.Task1.Annotation.NotNull;

public record SimplePersonRecord(@NotNull String name, @NotNull @Min(10) @Max(30) Integer age) {
}
