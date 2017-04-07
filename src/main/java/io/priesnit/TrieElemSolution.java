package io.priesnit;

import challenge.Runner;
import challenge.Solution;

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

        StringBuilder sb = new StringBuilder().append(c.getMin()).append(" ");
        for (int i = k; i < integers.length; i++) {
            c.put(integers[i]);
            c.remove(integers[i - k]);
            sb.append(c.getMin()).append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Runner.runHuge(new TrieElemSolution());
    }
}
