package edu.hw3;

import edu.hw3.Task7.TreeWithAvailableNullKey;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @Test
    void treeNullKeyTest() {

        // given
        TreeMap<String, String> treeMap = new TreeWithAvailableNullKey().getTree();

        // when
        treeMap.put(null, "test");

        // then
        assertThat(treeMap.containsKey(null))
            .isTrue();
    }
}
