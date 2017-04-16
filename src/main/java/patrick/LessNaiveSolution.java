package patrick;

import challenge.Solution;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by pgunia on 15.04.17.
 */
public class LessNaiveSolution implements Solution {

    private int curMinElement = Integer.MAX_VALUE;

    private int curMinOffset = -1;

    @Override public String solve(final int k, final int[] integers) {
        final List<Integer> result = IntStream.rangeClosed(0, integers.length - k).map(startIndex -> getMin(startIndex, k, integers)).boxed().collect(Collectors.toList());
        return result.stream().map(min -> String.valueOf(min)).collect(Collectors.joining(DELIMITER));
    }

    private int getMin(final int windowStartOffset, final int windowSize, final int[] input) {

        final int windowEndOffset = windowStartOffset + windowSize - 1;
        final int element = input[windowStartOffset];
        // special handling for first element
        // iterate over all elements, that are put into the window
        if (this.curMinOffset < 0) {
            return findMinInWindow(windowStartOffset, windowEndOffset, input);
        } else {
            final int windowEndElement = input[windowEndOffset];

            // Case 1: Min element stays in window
            if (this.curMinOffset > windowStartOffset) {
                // Case 1a: Min element is < new element: curElement is still minElement
                if (this.curMinElement < windowEndElement) {
                    return this.curMinElement;
                } else if (this.curMinElement == windowEndElement) {
                    // Case 1b: Min element is == windowEndElement => windowEndElement is smaller than all others
                    // make windowEndElement -> minElement
                    this.curMinOffset = windowEndOffset;
                    return this.curMinElement;
                } else if (this.curMinElement > windowEndElement) {
                    this.curMinElement = windowEndElement;
                    this.curMinOffset = windowEndOffset;

                    // Case 1c: windowEndElement is > minElement, curMinElement stays min Element
                    return this.curMinElement;
                }
            } else {
                // Case 2: Min element goes out of window
                // Case 2a: Next element is <= curMinElement -> next element is new min element
                if (windowEndElement <= this.curMinElement) {
                    this.curMinElement = windowEndElement;
                    this.curMinOffset = windowEndOffset;
                    return this.curMinElement;
                } else {
                    // Case 2b: Next element is > curMinElement
                    // find the next min element by iterating the window -> this needs to be faster
                    // set min back to MAX_INT
                    this.curMinElement = Integer.MAX_VALUE;
                    return findMinInWindow(windowStartOffset, windowEndOffset, input);
                }
            }
            throw new RuntimeException("This should never happen!");
        }
    }

    private int findMinInWindow(final int windowStart, final int windowEnd, final int[] input) {
        IntStream.rangeClosed(windowStart, windowEnd).forEach(curIndex -> {
            final int curElement = input[curIndex];
            if (this.curMinElement >= curElement) {
                this.curMinElement = curElement;
                this.curMinOffset = curIndex;
            }
        });
        return this.curMinElement;
    }

    @Override public String getName() {
        return "Less Naive Solution";
    }
}
