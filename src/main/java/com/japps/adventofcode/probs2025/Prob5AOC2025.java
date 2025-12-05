/*
 * Copyright (©) 2024 Subhajoy Laskar
 * https://www.linkedin.com/in/subhajoylaskar
 */
package com.japps.adventofcode.probs2025;

import static com.japps.adventofcode.util.RangeUtil.*;

import java.io.*;
import java.util.*;

import org.apache.commons.lang3.*;

import com.japps.adventofcode.util.*;

public final class Prob5AOC2025 extends AbstractSolvable implements Loggable {

    private static final Prob5AOC2025 INSTANCE = instance();

    private Prob5AOC2025() {

    }

    private static Prob5AOC2025 instance() {

        return new Prob5AOC2025();
    }

    public static void main(final String[] args) {

        try {
            INSTANCE.compute();
        } catch (final IOException exception) {
            INSTANCE.error(exception.getLocalizedMessage());
        }
    }

    private void compute() throws IOException {
        List<String> lines = lines();
        List<Range<Long>> ranges = new ArrayList<>();
        for (String line : lines.subList(0, 174)) {
            String[] minMax = line.split("-");
            Range<Long> range = Range.between(Long.parseLong(minMax[0]), Long.parseLong(minMax[1]));
            ranges.add(range);
        }
        println(idsInRanges(lines, ranges));
        println(distinctElementsStreamed(ranges));
        println(distinctElements(ranges));
    }

    private static long idsInRanges(List<String> lines, List<Range<Long>> ranges) {
        return (lines.subList(175, lines.size()).stream().mapToLong(Long::parseLong)
                .filter(id -> ranges.stream().anyMatch(range -> range.contains(id)))
                .count());
    }
}
