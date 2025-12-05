package com.japps.adventofcode.util;

import static org.apache.commons.collections4.CollectionUtils.*;

import java.util.*;

import org.apache.commons.lang3.*;

public final class RangeUtil {

	private RangeUtil() {}

	public static long distinctElementsStreamed(List<Range<Long>> ranges) {
		if (isEmpty(ranges)) {
			return 0;
		}
		List<long[]> mergedSortedRangesMinMax = new ArrayList<>();
		minSort(ranges).forEach(range -> mergeSortedRanges(range, mergedSortedRangesMinMax));
		return mergedSortedRangesMinMax.stream().mapToLong(rangeMinMax -> rangeMinMax[1] - rangeMinMax[0] + 1).sum();
	}

	private static void mergeSortedRanges(Range<Long> range, List<long[]> mergedSortedRangesMinMax) {
		long[] currentRangeMinMax = { range.getMinimum(), range.getMaximum() };
		if (isEmpty(mergedSortedRangesMinMax)) {
			mergedSortedRangesMinMax.add(currentRangeMinMax);
		} else {
			long[] lastRangeMinMax = mergedSortedRangesMinMax.getLast();
			if (currentRangeMinMax[0] <= lastRangeMinMax[1] + 1) {
				lastRangeMinMax[1] = Math.max(lastRangeMinMax[1], currentRangeMinMax[1]);
			} else {
				mergedSortedRangesMinMax.add(currentRangeMinMax);
			}
		}
	}

	public static long distinctElements(List<Range<Long>> ranges) {
		long distinctElements = 0;
		long currentRangeEnd = -1;
		for (Range<Long> range : minSort(ranges)) {
			long rangeStart = range.getMinimum();
			long rangeEnd = range.getMaximum();
			if (currentRangeEnd >= rangeStart) {
				rangeStart = currentRangeEnd + 1;
			}
			if (rangeStart <= rangeEnd) {
				distinctElements += (rangeEnd - rangeStart + 1);
			}
			currentRangeEnd = Math.max(currentRangeEnd, rangeEnd);
		}
		return distinctElements;
	}

	/**
	 * Returns a new sorted range based on the comparison by minimum or range start.
	 * The original range list remains unmodified.
	 *
	 * @param ranges The range list.
	 * @return A new range list that is sorted by the min or range start.
	 */
	public static List<Range<Long>> minSort(List<Range<Long>> ranges) {
		return ranges.stream().sorted(Comparator.comparing(Range::getMinimum)).toList();
	}

	/**
	 * Returns a new sorted range based on the comparison by maximum or range start.
	 * The original range list remains unmodified.
	 *
	 * @param ranges The range list.
	 * @return A new range list that is sorted by the max or range start.
	 */
	public static List<Range<Long>> maxSort(List<Range<Long>> ranges) {
		return ranges.stream().sorted(Comparator.comparing(Range::getMaximum)).toList();
	}
}
