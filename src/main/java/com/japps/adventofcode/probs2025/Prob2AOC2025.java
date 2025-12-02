/*
 * Copyright (©) 2024 Subhajoy Laskar
 * https://www.linkedin.com/in/subhajoylaskar
 */
package com.japps.adventofcode.probs2025;

import static com.japps.adventofcode.util.ListUtil.*;
import static com.japps.adventofcode.util.ProblemSolverUtil.*;

import java.io.*;
import java.util.*;

import org.apache.commons.collections4.*;
import org.apache.commons.lang3.*;

import com.japps.adventofcode.util.*;

public final class Prob2AOC2025 extends AbstractSolvable implements Loggable {

    private static final Prob2AOC2025 INSTANCE = instance();

    private Prob2AOC2025() {

    }

    private static Prob2AOC2025 instance() {

        return new Prob2AOC2025();
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
		String[] ranges = lines.getFirst().split(COMMA);
		List<Long> invalidIds = new ArrayList<>();
		List<Long> repetitiveInvalidIds = new ArrayList<>();
		for (String range : ranges) {
			String[] rangeStartEnd = range.split(HYPHEN);
			long rangeStart = Long.parseLong(rangeStartEnd[0]);
			long rangeEnd = Long.parseLong(rangeStartEnd[1]);
			for (long r = rangeStart; r <= rangeEnd; r++) {
				String rStr = String.valueOf(r);
				int midIndex = rStr.length() / 2;
				if (StringUtils.equals(rStr.substring(0, midIndex), rStr.substring(midIndex))) {
					invalidIds.add(r);
				} else {
					findRepetitiveInvalidIds(rStr, repetitiveInvalidIds, r);
				}
			}
		}
		println(invalidIds);
		println(repetitiveInvalidIds);
		println(sum(invalidIds));
		println(sum(union(invalidIds, repetitiveInvalidIds)));
	}

	private static void findRepetitiveInvalidIds(String rStr, List<Long> repetitiveInvalidIds, long r) {
		int seqLen = 1;
		String seq = rStr.substring(0, seqLen);
		boolean isSeqFound = false;
		int nextSeqPos = seqLen;
		while (seqLen <= rStr.length()) {
			nextSeqPos = isSeqFound ? nextSeqPos + seqLen : seqLen;
			int nextSeqEndPos = nextSeqPos + seqLen;
			if (nextSeqPos >= rStr.length() || nextSeqEndPos > rStr.length()) {
				isSeqFound = false;
				break;
			}
			String nextSeq = rStr.substring(nextSeqPos, nextSeqEndPos);
			if (!StringUtils.equals(seq, nextSeq)) {
				seq = rStr.substring(0, ++seqLen);
				isSeqFound = false;
			} else {
				isSeqFound = true;
				if (nextSeqEndPos == rStr.length()) {
					break;
				}
			}
		}
		if (isSeqFound) {
			repetitiveInvalidIds.add(r);
		}
	}
}
