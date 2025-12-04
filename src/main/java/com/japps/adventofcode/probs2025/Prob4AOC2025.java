/*
 * Copyright (©) 2024 Subhajoy Laskar
 * https://www.linkedin.com/in/subhajoylaskar
 */
package com.japps.adventofcode.probs2025;

import java.io.*;

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
		char[][] grid = linesAsArray();
		countRemovalOnce(grid);
		countRemovalOnEachRun(grid);
	}

	private void countRemovalOnce(char[][] grid) {
		int rolls = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				char c = grid[i][j];
				if (c == '@' && countAdjacentRolls(grid, i, j) < 4) {
					++rolls;
				}
			}
		}
		println(rolls);
	}

	private void countRemovalOnEachRun(char[][] grid) {
		int rolls = 0;
		while (true) {
			int runRolls = 0;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					char c = grid[i][j];
					if (c == '@' && countAdjacentRolls(grid, i, j) < 4) {
						++runRolls;
						grid[i][j] = '.';
					}
				}
			}
			if (runRolls > 0) {
				rolls += runRolls;
			} else {
				break;
			}
		}
		println(rolls);
	}

	private long countAdjacentRolls(char[][] grid, int i, int j) {
		int  adjacentRolls = 0;
		if (isInRange(grid, i - 1, j - 1) && grid[i - 1][j - 1] == '@') {
			++adjacentRolls;
		}
		if (isInRange(grid, i - 1, j) && grid[i - 1][j] == '@') {
			++adjacentRolls;
		}
		if (isInRange(grid, i - 1, j + 1) && grid[i - 1][j + 1] == '@') {
			++adjacentRolls;
		}
		if (isInRange(grid, i, j - 1) && grid[i][j - 1] == '@') {
			++adjacentRolls;
		}
		if (isInRange(grid, i, j + 1) && grid[i][j + 1] == '@') {
			++adjacentRolls;
		}
		if (isInRange(grid, i + 1, j - 1) && grid[i + 1][j - 1] == '@') {
			++adjacentRolls;
		}
		if (isInRange(grid, i + 1, j) && grid[i + 1][j] == '@') {
			++adjacentRolls;
		}
		if (isInRange(grid, i + 1, j + 1) && grid[i + 1][j + 1] == '@') {
			++adjacentRolls;
		}
		return adjacentRolls;
	}

	private boolean isInRange(char[][] grid, int row, int col) {
		return (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length);
	}
}
