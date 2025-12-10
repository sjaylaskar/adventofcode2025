/*
 * Id: Pair.java 09-Dec-2021 2:05:15 pm SubhajoyLaskar
 * Copyright (©) 2021 Subhajoy Laskar
 * https://www.linkedin.com/in/subhajoylaskar
 */
package com.japps.adventofcode.util;

import java.util.*;

/**
 * The Pair.
 *
 * @author Subhajoy Laskar
 * @version 1.0
 */
public class LongPair implements Comparable<LongPair> {

    /** The x. */
    private long x;

    /** The y. */
    private long y;

    public static LongPair of(final long x, final long y) {
    	return new LongPair(x, y);
    }

    public static LongPair of(final LongPair longPair) {
        return new LongPair(longPair.x(), longPair.y());
    }

    /**
     * Instantiates a new pair.
     *
     * @param x the x
     * @param y the y
     */
    public LongPair(final long x, final long y) {
        this.x = x;
        this.y = y;
    }

    public LongPair flip() {
        return LongPair.of(this.y, this.x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
			return true;
		}
        if (obj == null) {
			return false;
		}
        if (getClass() != obj.getClass()) {
			return false;
		}
        final LongPair other = (LongPair) obj;
        return x == other.x && y == other.y;
    }

    public boolean equals(long x, long y) {
        return (this.x == x && this.y == y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " [x=" + x + ", y=" + y + "]";
    }

    /**
     * Gets the x.
     *
     * @return the x
     */
    public long x() {
        return x;
    }

    /**
     * Gets the y.
     *
     * @return the y
     */
    public long y() {
        return y;
    }

	/**
	 * @param x the x to set
	 */
	public LongPair x(final long x) {
		this.x = x;
		return this;
	}

	/**
	 * @param y the y to set
	 */
	public LongPair y(final long y) {
		this.y = y;
		return this;
	}

    @Override
    public int compareTo(LongPair other) {
        int compare = Long.compare(this.x(), other.x());
        return compare == 0 ? Long.compare(this.y(), other.y()) : compare;
    }

	public boolean lessThan(LongPair other) {
		return (x() < other.x() && y() < other.y());
	}

	public long distance() {
		return Math.abs(y() - x());
	}
}
