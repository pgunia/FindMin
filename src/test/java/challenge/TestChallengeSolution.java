package challenge;

import io.priesnit.TrieElemSolution;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import patrick.LessNaiveSolution;
import patrick.NaiveSolution;

import java.util.Random;

/**
 * Created by pgunia on 15.04.17.
 */
public class TestChallengeSolution {

    private static final int[] INPUT = { 1, 4, 7, 13, 1, 15, 54, 23, 6, 2, 4, 9, 5, 7, 2, 8, 5, 3, 123, 21, 54, 12 };

    private static final int WINDOW_SIZE = 4;

    private static final int HUGE_WINDOW_SIZE = 1500;

    private static final int BIG_INPUT_LENGTH = 1000000;

    private static int[] HUGE_INPUT;

    private final Runner runner = new Runner();

    @BeforeClass
    public static void setup() {
        final Random r = new Random(234572356545753454L);
        HUGE_INPUT = new int[BIG_INPUT_LENGTH];
        for (int i = 0; i < BIG_INPUT_LENGTH; i++) {
            HUGE_INPUT[i] = Math.abs(r.nextInt());
        }
    }

    @Test
    public void testCorrectnessFixedInputNaiveTrieElement() {
        System.out.println("Naive solution vs. Trie element solution");
        Assert.assertTrue(this.runner.compare(new NaiveSolution(), new TrieElemSolution(), INPUT, WINDOW_SIZE));
    }

    @Test
    public void testCorrectnessFixedInputNaiveLessNaive() {
        System.out.println("Naive solution vs. Less naive solution");
        Assert.assertTrue(this.runner.compare(new NaiveSolution(), new LessNaiveSolution(), INPUT, WINDOW_SIZE));
    }

    @Test
    public void testCorrectnessFixedInputNaiveTrieElementBigInput() {
        System.out.println("Naive solution vs. Trie element solution - BIG INPUT");
        Assert.assertTrue(this.runner.compare(new NaiveSolution(), new TrieElemSolution(), this.HUGE_INPUT, HUGE_WINDOW_SIZE));
    }

    @Test
    public void testCorrectnessFixedInputNaiveLessNaiveBigInput() {
        System.out.println("Naive solution vs. Less naive solution - BIG INPUT");
        Assert.assertTrue(this.runner.compare(new NaiveSolution(), new LessNaiveSolution(), this.HUGE_INPUT, HUGE_WINDOW_SIZE));
    }

    @Test
    public void testCorrectnessFixedInputTrieElementLessNaiveBigInput() {
        System.out.println("Trie elem solution vs. Less naive solution - BIG INPUT");
        Assert.assertTrue(this.runner.compare(new TrieElemSolution(), new LessNaiveSolution(), this.HUGE_INPUT, HUGE_WINDOW_SIZE));
    }
}
