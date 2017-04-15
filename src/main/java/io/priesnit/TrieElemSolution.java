package io.priesnit;

import challenge.Runner;
import challenge.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vince on 4/7/2017.
 */
public class TrieElemSolution implements Solution {
    @Override
    public String solve(int k, int[] integers) {
        // init
        TrieElementCounter c = new TrieElementCounter(k + 1);
        for (int i = 0; i < k; i++) {
            c.put(integers[i]);
        }

        final List<Integer> result = new ArrayList<Integer>((integers.length / k) + 1);

        result.add(c.getMin());
        for (int i = k; i < integers.length; i++) {
            c.put(integers[i]);
            c.remove(integers[i - k]);
            result.add(c.getMin());
        }

        return result.stream().map(min -> String.valueOf(min)).collect(Collectors.joining(DELIMITER));
    }

    @Override public String getName() {
        return "Trie Element Solution";
    }

    public static void main(String[] args) {
        final Runner runner = new Runner();
        runner.runHuge(new TrieElemSolution());
    }
}
