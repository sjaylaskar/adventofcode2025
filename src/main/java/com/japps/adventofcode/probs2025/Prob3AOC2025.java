/*
 * Copyright (©) 2024 Subhajoy Laskar
 * https://www.linkedin.com/in/subhajoylaskar
 */
package com.japps.adventofcode.probs2025;

import static com.japps.adventofcode.util.ListUtil.*;

import java.io.*;
import java.util.*;

import com.japps.adventofcode.util.*;

public final class Prob3AOC2025 extends AbstractSolvable implements Loggable {

    private static final Prob3AOC2025 INSTANCE = instance();

    private Prob3AOC2025() {

    }

    private static Prob3AOC2025 instance() {

        return new Prob3AOC2025();
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
		long joltageMax = 0;
		long joltage12BatteryMax = 0;
		for (String line : lines) {
			List<Integer> batteries = Arrays.stream(line.split("")).mapToInt(Integer::valueOf).boxed().toList();
			int maxIndex = findMaxNumIndex(batteries, batteries.size() - 1);
			List<Integer> secondMaxSubList = batteries.subList(maxIndex + 1, batteries.size());
			int secondMaxIndex = findMaxNumIndex(secondMaxSubList, secondMaxSubList.size());
			joltageMax += batteries.get(maxIndex) * 10 + secondMaxSubList.get(secondMaxIndex);
			joltage12BatteryMax += maxNumOfGivenLengthInSequence(batteries, 12);
		}
		println(joltageMax);
		println(joltage12BatteryMax);
	}
}
