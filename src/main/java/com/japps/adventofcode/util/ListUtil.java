package com.japps.adventofcode.util;

import java.util.*;
import java.util.stream.*;

import org.apache.commons.collections4.*;

public final class ListUtil {

	private ListUtil() {}

	public static Long sum(List<Long> list) {
		return list.stream().mapToLong(Long::valueOf).sum();
	}

	public static <T> List<T> union(List<T> list1, List<T> list2) {
		return ListUtils.union(list1, list2);
	}

	public static int findMaxNumIndex(List<Integer> list, int endIndex) {
		return IntStream.range(0, endIndex).reduce((i, j) -> list.get(i) >= list.get(j) ? i : j).orElseThrow(() -> new IllegalStateException("Could not find the index for maximum number"));
	}

	public static long maxNumOfGivenLengthInSequence(List<Integer> numbers, int lengthOfMaxNum) {
		int sizeOfList = numbers.size();
		if (sizeOfList < lengthOfMaxNum) {
			throw new IllegalArgumentException("List must contain at least: " + lengthOfMaxNum + " digits.");
		}
		Deque<Integer> numStack = new ArrayDeque<>();
		int numOfDigitsToExclude = sizeOfList - lengthOfMaxNum;
		for (int num : numbers) {
			while (!numStack.isEmpty() && numOfDigitsToExclude > 0 && numStack.peekLast() < num) {
				numStack.pollLast();
				numOfDigitsToExclude--;
			}
			numStack.addLast(num);
		}
		while (numStack.size() > lengthOfMaxNum) {
			numStack.pollLast();
		}
		StringBuilder numberBuilder = new StringBuilder();
		for (int digit : numStack) {
			numberBuilder.append(digit);
		}
		return Long.parseLong(numberBuilder.toString());
	}
}
