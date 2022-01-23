import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberRangeSummarizerTest {

    NumberRangeSummarizerImpl rangeSummarizer;

    @BeforeEach
    public void init() {
        rangeSummarizer = new NumberRangeSummarizerImpl();
    }

    @Test
    public void test_collect_null_or_empty() {
        // Test empty space as input
        assertEquals(Collections.EMPTY_LIST, rangeSummarizer.collect(" "));
        // Test null as input
        assertEquals(Collections.EMPTY_LIST, rangeSummarizer.collect(null));
    }

    @Test
    public void test_collect_after_cleanup() {
        // Test values with some spaces
        assertEquals(Arrays.asList(1,2,3,4,5,6,7), rangeSummarizer.collect("1, 2  ,   3,    4, 5,6,  7"));
        // Test sorting of values
        assertEquals(Arrays.asList(1,2,3,4,5), rangeSummarizer.collect("5, 2, 1, 3, 4"));
        // Test duplicate values
        assertEquals(Arrays.asList(1,2,3,4,5), rangeSummarizer.collect("1,2,2,3,3,3,4,4,4,4,5,5"));
        // Test combination of values with spaces. duplicates and ordering
        assertEquals(Arrays.asList(1,2,3,4,5), rangeSummarizer.collect("5,5,2, 3,  3,3,1,  4,4,4, 5,5"));
    }

    @Test
    public void test_summarize_null_or_empty() {
        assertEquals("", rangeSummarizer.summarizeCollection(null));
        assertEquals("", rangeSummarizer.summarizeCollection(Collections.emptyList()));
    }

    @Test
    public void test_summarize_collection_range() {
        assertEquals("1, 3, 6-8, 12-15, 21-24, 31", rangeSummarizer.summarizeCollection(Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31)));
        assertEquals("10, 15-18, 30, 32, 47-49, 71-72, 80-81, 100", rangeSummarizer.summarizeCollection(Arrays.asList(10, 15,16,17,18,30,32,47,48,49,71,72,80,81,100)));
    }
}
