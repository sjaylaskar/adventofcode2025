package com.japps.adventofcode.util;

import java.util.*;

import org.apache.commons.collections4.*;

public final class ListUtil {

	private ListUtil() {}

	public static Long sum(List<Long> list) {
		return list.stream().mapToLong(Long::valueOf).sum();
	}

	public static <T> List<T> union(List<T> list1, List<T> list2) {
		return ListUtils.union(list1, list2);
	}
}
