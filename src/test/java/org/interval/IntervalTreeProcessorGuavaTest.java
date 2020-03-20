package org.interval;

import com.google.common.collect.Range;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class IntervalTreeProcessorGuavaTest {

    IntervalTreeProcessorGuava intervalTreeProcessorGuava;
    IntervalTreeProcessorGuava emptyIntervalTreeProcessorGuava;

    @Before
    public void setUp() throws Exception {
        emptyIntervalTreeProcessorGuava = new IntervalTreeProcessorGuava();
        intervalTreeProcessorGuava = new IntervalTreeProcessorGuava();
        intervalTreeProcessorGuava.add(Range.closedOpen(0, 10));
        intervalTreeProcessorGuava.add(Range.closedOpen(10, 20));
        intervalTreeProcessorGuava.add(Range.closedOpen(25, 26));
        intervalTreeProcessorGuava.add(Range.closedOpen(30, 40));
        intervalTreeProcessorGuava.add(Range.closedOpen(40, 50));
    }

    @Test
    public void shouldFillRanges() {
        assertEquals(3, intervalTreeProcessorGuava.getRanges().asRanges().size());
    }

    @Test
    public void shouldCalculateNearestLeftIntervalDistance() {
        Range<Integer> range = Range.closedOpen(25, 26);
        Range<Integer> hugeRange = Range.closedOpen(Integer.MIN_VALUE, Integer.MAX_VALUE);

        assertEquals(Optional.of(5), intervalTreeProcessorGuava.distanceNearestLeft(range));
        assertEquals(Optional.empty(), intervalTreeProcessorGuava.distanceNearestLeft(hugeRange));

        assertEquals(Optional.empty(), emptyIntervalTreeProcessorGuava.distanceNearestLeft(range));
    }

    @Test
    public void shouldCalculateNearestRightIntervalDistance() {
        Range<Integer> range = Range.closedOpen(25, 26);
        Range<Integer> hugeRange = Range.closedOpen(Integer.MIN_VALUE, Integer.MAX_VALUE);

        assertEquals(Optional.of(4), intervalTreeProcessorGuava.distanceNearestRight(range));
        assertEquals(Optional.empty(), intervalTreeProcessorGuava.distanceNearestRight(hugeRange));

        assertEquals(Optional.empty(), emptyIntervalTreeProcessorGuava.distanceNearestRight(range));
    }
}