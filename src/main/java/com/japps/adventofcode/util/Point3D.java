/*
* Id: Point.java 09-Dec-2021 2:09:27 pm SubhajoyLaskar
* Copyright (©) 2021 Subhajoy Laskar
* https://www.linkedin.com/in/subhajoylaskar
*/
package com.japps.adventofcode.util;

import java.util.*;

/**
 * The point 3D.
 *
 * @author Subhajoy Laskar
 * @version 1.0
 */
public class Point3D extends Point {

    private long z;

    public Point3D(final long x, final long y, final long z) {
        super(x, y);
        this.z = z;
    }

    public static Point3D of(final long x, final long y, final long z) {
    	return new Point3D(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Point3D point3D = (Point3D) o;
        return x() == point3D.x() && y() == point3D.y() && z == point3D.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x(), y(), z);
    }

    public long z() {
        return z;
    }

    public Point3D z(long z) {
        this.z = z;
        return this;
    }

    public long squaredDistance(Point3D otherPoint) {
        long distZ = z() - otherPoint.z();
        return super.squaredDistance(otherPoint) + distZ * distZ;
    }
}
