package patrick;

import challenge.Solution;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by pgunia on 15.04.17.
 */
public class LessNaiveSolution implements Solution {

    private int curMin = Integer.MAX_VALUE;

    private int curMinOffset = -1;

    @Override public String solve(final int k, final int[] integers) {
        final List<Integer> result = IntStream.rangeClosed(0, integers.length - k).map(startIndex -> getMin(startIndex, k, integers)).boxed().collect(Collectors.toList());
        return result.stream().map(min -> String.valueOf(min)).collect(Collectors.joining(DELIMITER));
    }

    private int getMin(final int curStart, final int windowSize, final int[] input) {

        final int windowEndIndex = curStart + windowSize;
        final int nextIndex = windowEndIndex + 1;

        // special handling for first element
        // iterate over all elements, that are put into the window
        if (this.curMin < 0) {
            return this.curMin;
        } else {
            final int prevIndex = curStart - 1;
            final int prevElement = input[prevIndex];
            final int nextElement = input[nextIndex];

            // Case 1: Min element stays in window
            if (this.curMinOffset >= curStart) {
                // Case 1a: Min element is < new element: curElement is still minElement
                if (this.curMin < nextElement) {
                    return this.curMin;
                } else if (this.curMin == nextElement) {
                    // Case 1b: Min element is == nextElement => nextElement is smaller than all others
                    // make nextElement -> minElement
                    this.curMinOffset = nextIndex;
                    return this.curMin;
                } else {
                    // Case 1c: nextElement is > minElement, curMin stays min Element
                    return this.curMin;
                }
            } else {
                // Case 2: Min element goes out of window
                // Case 2a: Next element is <= curMin -> next element is new min element
                if (nextElement <= this.curMin) {
                    this.curMin = nextElement;
                    this.curMinOffset = nextIndex;
                } else {
                    // Case 2b: Next element is > curMin
                    // find the next min element by iterating the window -> this needs to be faster
                    // set min back to MAX_INT
                    this.curMin = Integer.MAX_VALUE;
                    return findMinInWindow(curStart, windowEndIndex, input);
                }
            }
        }
        return -1;
    }

    private int findMinInWindow(final int windowStart, final int windowEnd, final int[] input) {
        IntStream.range(windowStart, windowEnd).forEach(curIndex -> {
            final int curElement = input[curIndex];
            if (this.curMin < input[curIndex]) {
                this.curMin = curElement;
                this.curMinOffset = curIndex;
            }
        });
        return this.curMin;
    }

    @Override public String getName() {
        return "Less Naive Solution";
    }
}
