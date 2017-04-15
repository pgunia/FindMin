package patrick;

import challenge.Runner;
import challenge.Solution;
import io.priesnit.TrieElemSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pgunia on 15.04.17.
 */
public class NaiveSolution implements Solution {

    /**
     * Basic, naive solution => way more expensive than O(n)
     * This can be done way better
     *
     * @param window Array of Integer for which the min value should be found
     * @return Min Integer inside the window
     */
    private static int getMin(final int[] window) {

        // let's say we only have positive integers
        int curMin = Integer.MAX_VALUE;
        for (Integer cur : window) {
            if (cur < curMin) {
                curMin = cur;
            }
        }
        return curMin;
    }

    @Override public String solve(int k, int[] integers) {

        final int arrayLength = integers.length;
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= (arrayLength - k); i++) {
            result.add(getMin(Arrays.copyOfRange(integers, i, i + k)));
        }
        return result.stream().map(min -> String.valueOf(min)).collect(Collectors.joining(DELIMITER));
    }

    @Override public String getName() {
        return "Naive Solution";
    }

    public static void main(String[] args) {
        final Runner runner = new Runner();
        runner.runHuge(new TrieElemSolution());
    }
}
