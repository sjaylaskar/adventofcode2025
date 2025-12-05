package com.japps.adventofcode.util;

import static org.apache.commons.collections4.CollectionUtils.*;

import java.util.*;

import org.apache.commons.lang3.*;

public final class RangeUtil {

	private RangeUtil() {}

	public static long distinctElementsInRanges(List<Range<Long>> ranges) {
		if (isEmpty(ranges)) {
			return 0;
		}
		List<long[]> mergedSortedRangesMinMax = new ArrayList<>();
		ranges.stream().sorted(Comparator.comparing(Range::getMinimum)).forEach(range -> {
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
		});
		return mergedSortedRangesMinMax.stream().mapToLong(rangeMinMax -> rangeMinMax[1] - rangeMinMax[0] + 1).sum();
	}
}
