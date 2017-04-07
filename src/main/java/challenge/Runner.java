package challenge;


import java.util.Objects;
import java.util.Random;

public class Runner {

    public void run(Solution solution) {
        final int[] input = {1, 4, 6, 2, 4, 9, 5, 7, 2, 8, 5, 3, 123, 21, 54, 12};
        final int windowSize = 4;

        String solve = solution.solve(windowSize, input);
        System.out.println("Result is " + solve);
    }

    public static void runHuge(Solution solution) {
        int n = 100000;
        final int windowSize = 1000;

        Random r = new Random(234572356545753454L);
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Math.abs(r.nextInt());
        }
        long start = System.currentTimeMillis();
        String solve = solution.solve(windowSize, input);
        long end = System.currentTimeMillis();

        System.out.println("Took " + (end - start ) + " ms");
        System.out.println("Result is " + solve);
    }

    public static void compare(Solution solution1, Solution solution2) {
        int n = 100000;
        final int windowSize = 1000;


        Random r = new Random(234572356545753454L);
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Math.abs(r.nextInt());
        }

        long start1 = System.currentTimeMillis();
        String result1 = solution1.solve(windowSize, input);
        System.out.println("Solution1 took " + (System.currentTimeMillis() - start1 ) + " ms");

        long start2 = System.currentTimeMillis();
        String result2 = solution2.solve(windowSize, input);
        System.out.println("Solution2 took " + (System.currentTimeMillis() - start2 ) + " ms");

        if (!Objects.equals(result1, result2)) {
            System.out.println("Results are different: "
                    + "\n " + result1
                    + "\n " + result2);
        }
    }


}
