package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @Test
    void hackerNewsGetTopStoriesTest() {

        long[] result = HackerNews.hackerNewsTopStories();

        assertThat(result).isNotEmpty();
    }

    @Test
    void hackerNewsGet37570037StoryTest() throws IOException, InterruptedException {

        String result = HackerNews.news(37570037);

        assertThat(result).isEqualTo("JDK 21 Release Notes");
    }
}
