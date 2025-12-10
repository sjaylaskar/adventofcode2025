/*
* Id: Point.java 09-Dec-2021 2:09:27 pm SubhajoyLaskar
* Copyright (©) 2021 Subhajoy Laskar
* https://www.linkedin.com/in/subhajoylaskar
*/
package com.japps.adventofcode.util;

import java.util.List;

/**
 * The point.
 *
 * @author Subhajoy Laskar
 * @version 1.0
 */
public class Point extends LongPair {

    /**
     * Instantiates a new point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(final long x, final long y) {

        super(x, y);
    }

    public static Point of(final long x, final long y) {
    	return new Point(x, y);
    }

    public long shortestPath(final Point other) {
    	return shortestPath(this, other);
    }

    /**
     * Shortest path.
     *
     * @param point1 the point 1
     * @param point2 the point 2
     * @return the long
     */
    public static long shortestPath(final Point point1, final Point point2) {
        return Math.abs(point1.x() - point2.x()) + Math.abs(point1.y() - point2.y());
    }

    /**
     * Shortest path sum.
     *
     * @param points the points
     * @return the long
     */
    public static long shortestPathSum(final List<Point> points) {
    	long sum = 0;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                sum += shortestPath(points.get(i), points.get(j));
            }
        }
        return sum;
    }

    public long squaredDistance(Point otherPoint) {
        long distX = x() - otherPoint.x();
        long distY = y() - otherPoint.y();
        return distX * distX + distY * distY;
    }

    public double distance(Point otherPoint) {
        return Math.sqrt(squaredDistance(otherPoint));
    }
}
