/*
 * Copyright (©) 2024 Subhajoy Laskar
 * https://www.linkedin.com/in/subhajoylaskar
 */
package com.japps.adventofcode.probs2025;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import com.japps.adventofcode.util.*;

public final class Prob1AOC2025 extends AbstractSolvable implements Loggable {

    private static final Prob1AOC2025 INSTANCE = instance();

    private Prob1AOC2025() {

    }

    private static Prob1AOC2025 instance() {

        return new Prob1AOC2025();
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
		int pos = 50;
		int countEndZero = 0;
		int countAnyZero = 0;
		for (String line : lines) {
			char direction = line.charAt(0);
			int rotations = Integer.parseInt(line.substring(1));
			for (int r = 0; r < rotations; r++) {
				if (direction == 'L') {
					pos = (pos + 99) % 100;
				} else {
					pos = (pos + 1) % 100;
				}
				if (pos == 0) {
					++countAnyZero;
				}
			}
			if (pos == 0) {
				++countEndZero;
			}
		}
		println(countEndZero);
		println(countAnyZero);
    }
}
