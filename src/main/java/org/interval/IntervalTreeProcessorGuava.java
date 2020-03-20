package org.interval;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

import java.util.Optional;

/**
 * https://stackoverflow.com/questions/17466218/what-are-the-differences-between-segment-trees-interval-trees-binary-indexed-t
 * https://stackoverflow.com/questions/15149857/intervaltree-in-guava
 */
public class IntervalTreeProcessorGuava {

    private RangeSet<Integer> ranges = TreeRangeSet.create();

    public RangeSet<Integer> getRanges() {
        return ranges;
    }

    /**
     * Add range to ranges
     * @param range
     */
    public void add(Range<Integer> range) {
        ranges.add(range);
    }

    /**
     * Calculate distance to the nearest left interval
     * @param range
     * @return
     */
    public Optional<Integer> distanceNearestLeft(Range<Integer> range) {
        RangeSet<Integer> leftRangeSet = ranges.subRangeSet(Range.lessThan(range.lowerEndpoint()));
        if (leftRangeSet.isEmpty())
            return Optional.empty();
        Integer leftBound = leftRangeSet.span().upperEndpoint();
        int value = range.lowerEndpoint() - leftBound;
        return value < 0 ? Optional.empty() : Optional.of(value);
    }

    /**
     * Calculate distance to the nearest right interval
     * @param range
     * @return
     */
    public Optional<Integer> distanceNearestRight(Range<Integer> range) {
        RangeSet<Integer> rightRangeSet = ranges.subRangeSet(Range.greaterThan(range.upperEndpoint()));
        if (rightRangeSet.isEmpty())
            return Optional.empty();
        Integer upperBound = rightRangeSet.span().lowerEndpoint();
        int value = upperBound - range.upperEndpoint();
        return value < 0 ? Optional.empty() : Optional.of(value);
    }
}
