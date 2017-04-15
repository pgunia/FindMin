package challenge;

import io.priesnit.TrieElemSolution;
import org.junit.Test;
import patrick.NaiveSolution;

/**
 * Created by pgunia on 15.04.17.
 */
public class TestChallengeSolution {

    @Test
    public void testCorrectnessFixedInput() {

        final Runner runner = new Runner();

        final int[] input = {1, 4, 6, 2, 4, 9, 5, 7, 2, 8, 5, 3, 123, 21, 54, 12};
        final int windowSize = 4;

        runner.compare(new NaiveSolution(), new TrieElemSolution(), input, windowSize);
    }
}
