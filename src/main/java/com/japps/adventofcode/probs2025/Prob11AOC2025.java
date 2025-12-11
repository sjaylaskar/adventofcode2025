/*
 * Copyright (©) 2024 Subhajoy Laskar
 * https://www.linkedin.com/in/subhajoylaskar
 */
package com.japps.adventofcode.probs2025;

import static com.japps.adventofcode.util.ProblemSolverUtil.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import com.japps.adventofcode.util.*;

public final class Prob11AOC2025 extends AbstractSolvable implements Loggable {

    private static final Prob11AOC2025 INSTANCE = instance();

    private Prob11AOC2025() {

    }

    private static Prob11AOC2025 instance() {

        return new Prob11AOC2025();
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
        Map<String, List<String>> graph = new HashMap<>();
        for (String line : lines) {
            String[] path = line.split(":");
            graph.putIfAbsent(path[0], new ArrayList<>());
            graph.get(path[0]).addAll(Arrays.stream(path[1].trim().split(SPACE)).map(String::trim).collect(Collectors.toCollection(ArrayList::new)));
        }
        println(countPathsToOut("you", graph));
        println(countPathsToOut("svr", graph, false, false));
    }

    private final static Map<String, Long> YOU_TO_OUT_COUNT_MAP = new HashMap<>();
    private static long countPathsToOut(String startNode, Map<String, List<String>> graph) {
        if (YOU_TO_OUT_COUNT_MAP.containsKey(startNode)) {
            return YOU_TO_OUT_COUNT_MAP.get(startNode);
        }
        long count;
        if (startNode.equals("out")) {
            count = 1;
        } else {
            count = 0;
            for (String node : graph.getOrDefault(startNode, List.of())) {
                count += countPathsToOut(node, graph);
            }
        }
        YOU_TO_OUT_COUNT_MAP.put(startNode, count);
        return count;
    }

    private final static Map<String, Map<String, Long>> SVR_TO_OUT_PATH_COUNT_MAP = new HashMap<>();
    private static long countPathsToOut(String startNode, Map<String, List<String>> graph, boolean isVisitedDac, boolean isVisitedFft) {
        String key = (isVisitedDac ? "1" : "0") + (isVisitedFft ? "1" : "0");
        SVR_TO_OUT_PATH_COUNT_MAP.putIfAbsent(startNode, new HashMap<>());
        Map<String, Long> countMap = SVR_TO_OUT_PATH_COUNT_MAP.get(startNode);
        if (countMap.containsKey(key)) {
            return countMap.get(key);
        }
        long count;
        if (startNode.equals("out")) {
            count = (isVisitedDac && isVisitedFft) ? 1 : 0;
        } else {
            count = 0;
            for (String node : graph.getOrDefault(startNode, List.of())) {
                boolean isVisitedDacNode = isVisitedDac || node.equals("dac");
                boolean isVisitedFftNode = isVisitedFft || node.equals("fft");
                count += countPathsToOut(node, graph, isVisitedDacNode, isVisitedFftNode);
            }
        }
        countMap.put(key, count);
        return count;
    }
}
