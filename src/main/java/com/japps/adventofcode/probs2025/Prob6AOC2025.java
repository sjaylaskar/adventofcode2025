/*
 * Copyright (©) 2024 Subhajoy Laskar
 * https://www.linkedin.com/in/subhajoylaskar
 */
package com.japps.adventofcode.probs2025;

import static org.apache.commons.lang3.math.NumberUtils.*;
import static com.japps.adventofcode.util.MathUtil.*;
import static com.japps.adventofcode.util.ProblemSolverUtil.*;

import java.io.*;
import java.util.*;
import java.util.function.*;

import org.apache.commons.lang3.*;

import com.japps.adventofcode.util.*;

public final class Prob6AOC2025 extends AbstractSolvable implements Loggable {

	private static final Prob6AOC2025 INSTANCE = instance();

	private Prob6AOC2025() {

	}

	private static Prob6AOC2025 instance() {

		return new Prob6AOC2025();
	}

	public static void main(final String[] args) {

		try {
			INSTANCE.compute();
		} catch (final IOException exception) {
			INSTANCE.error(exception.getLocalizedMessage());
		}
	}

	private enum Op {
		ADD("+", 0L), MULTIPLY("*", 1L);
		private final String symbol;
		private final long identity;

		Op(String symbol, long identity) {
			this.symbol = symbol;
			this.identity = identity;
		}

		String symbol() {
			return symbol;
		}

		long identity() {
			return identity;
		}

		static Op op(char symbol) {
			return Arrays.stream(Op.values()).filter(op -> StringUtils.equals(op.symbol(), BLANK + symbol)).findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid operation symbol: " + symbol));
		}

		static boolean isOp(char symbol) {
			return Arrays.stream(Op.values()).anyMatch(op -> StringUtils.equals(op.symbol(), BLANK + symbol));
		}
	}

	private static final Map<String, BiFunction<Long, Long, Long>> OPFUNC = Map.of("+", Long::sum, "*", multiply());

	private void compute() throws IOException {
		numRowWiseColumnOperation();
		numColumnWiseColumnOperation();
	}

	private void numRowWiseColumnOperation() throws IOException {
		List<String> lines = lines();
		String[][] items = lines.stream().map(line -> line.split(MULTISPACE_REGEX)).toArray(String[][]::new);
		long totalResult = 0;
		for (int col = 0; col < items[0].length; col++) {
			long columnResult = 0;
			BiFunction<Long, Long, Long> columnFn = OPFUNC.get(Op.ADD.symbol());
			for (int row = items.length - 1; row >= 0; row--) {
				String item = items[row][col];
				if (StringUtils.equals(Op.MULTIPLY.symbol(), item)) {
					columnResult = 1;
					columnFn = OPFUNC.get(Op.MULTIPLY.symbol());
				} else if (StringUtils.equals(Op.ADD.symbol(), item)) {
					columnResult = 0;
					columnFn = OPFUNC.get(Op.ADD.symbol());
				} else {
					columnResult = columnFn.apply(columnResult, Long.parseLong(item));
				}
			}
			totalResult += columnResult;
		}
		println(totalResult);
	}

	private void numColumnWiseColumnOperation() throws IOException {
		char[][] items = linesAsCharArray();
		long totalResult = 0;
		List<Long> nums = new ArrayList<>();
		for (int col = items[0].length - 1; col >= 0; col--) {
			StringBuilder numStr = new StringBuilder(BLANK);
			for (char[] chars : items) {
				if (col > chars.length - 1) {
					break;
				}
				char item = chars[col];
				if (item == SPACE_CHAR) {
					continue;
				} else if (Op.isOp(item)) {
					nums.add(Long.parseLong(numStr.toString()));
					Op op = Op.op(item);
					totalResult += nums.stream().reduce(op.identity(), (x, y) -> OPFUNC.get(op.symbol()).apply(x, y));
					nums = new ArrayList<>();
					numStr = new StringBuilder(BLANK);
				} else {
					numStr.append(item);
				}
			}
			if (isCreatable(numStr.toString())) {
				nums.add(Long.parseLong(numStr.toString()));
			}
		}
		println(totalResult);
	}
}
