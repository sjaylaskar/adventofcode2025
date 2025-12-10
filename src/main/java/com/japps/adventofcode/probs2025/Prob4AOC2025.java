/*
 * Copyright (©) 2024 Subhajoy Laskar
 * https://www.linkedin.com/in/subhajoylaskar
 */
package com.japps.adventofcode.probs2025;

import static java.util.stream.IntStream.*;
import static com.japps.adventofcode.util.ArrayUtil.*;

import java.io.*;
import java.util.function.*;
import java.util.stream.*;

import com.japps.adventofcode.util.*;

public final class Prob4AOC2025 extends AbstractSolvable implements Loggable {

    private static final Prob4AOC2025 INSTANCE = instance();

    private Prob4AOC2025() {

    }

    private static Prob4AOC2025 instance() {

        return new Prob4AOC2025();
    }

    public static void main(final String[] args) {

        try {
            INSTANCE.compute();
        } catch (final IOException exception) {
            INSTANCE.error(exception.getLocalizedMessage());
        }
    }

    private void compute() throws IOException {
		char[][] grid = linesAsCharArray();
		println(countRemovalOnce(grid));
		println(countRemovalOnEachRun(grid));
	}

	private long countRemovalOnce(char[][] grid) {
		return IntStream.range(0, grid.length)
				.flatMap(row -> IntStream.range(0, grid[row].length)
				.filter(countRemovableRollPredicate(grid, row)))
				.count();
	}

	private IntPredicate countRemovableRollPredicate(char[][] grid, int row) {
		return col -> grid[row][col] == '@' && countAdjacentRolls(grid, row, col) < 4;
	}

	private long countRemovalOnEachRun(char[][] grid) {
		long rolls = 0;
		while (true) {
			long runRolls = IntStream.range(0, grid.length).flatMap(row -> IntStream.range(0, grid[row].length)
							.filter(col -> grid[row][col] == '@' && countAdjacentRolls(grid, row, col) < 4).peek(col -> grid[row][col] = '.')).count();

			if (runRolls == 0) {
				break;
			}
			rolls += runRolls;
		}
		return rolls;
	}

	private long countAdjacentRolls(char[][] grid, int i, int j) {
		return rangeClosed(-1, 1).boxed().flatMap(rowCoord -> rangeClosed(-1, 1).mapToObj(colCoord -> new int[]{rowCoord, colCoord}))
				.filter(rollFoundPredicate(grid, i, j))
				.count();
	}

	private static Predicate<int[]> rollFoundPredicate(char[][] grid, int i, int j) {
		return directions -> !(directions[0] == 0 && directions[1] == 0)
								  && isInRange(grid, i + directions[0], j + directions[1])
								  && grid[i + directions[0]][j + directions[1]] == '@';
	}
}
