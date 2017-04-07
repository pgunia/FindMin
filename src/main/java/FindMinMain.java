
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMinMain {

    public static void main(String... args) {

        final Integer[] input = {1, 4, 6, 2, 4, 9, 5, 7, 2, 8, 5, 3, 123, 21, 54, 12};
        final int windowSize = 4;

        final int arrayLength = input.length;
        final List<Integer> resultPatrick = new ArrayList<>();
        for (int i = 0; i <= (arrayLength - windowSize); i++) {
            resultPatrick.add(getMin(Arrays.copyOfRange(input, i, i + windowSize)));
        }

        System.out.println("---------------------------------------------------------------------------");

        final List<Integer> resultFlo = getMinFlo(input, windowSize);
        System.out.println(Arrays.equals(resultFlo.toArray(), resultPatrick.toArray()));

    }

    /**
     * Basic, naive solution => way more expensive than O(n)
     * This can be done way better
     *
     * @param window Array of Integer for which the min value should be found
     * @return Min Integer inside the window
     */
    private static int getMin(final Integer[] window) {

        // let's say we only have positive integers
        int curMin = Integer.MAX_VALUE;
        for (Integer cur : window) {
            if (cur < curMin) {
                curMin = cur;
            }
        }
        System.out.println("Cur Min: " + curMin);
        return curMin;
    }

    public static List<Integer> getMinFlo(final Integer[] input, int windowSize) {
        int currentMin = 0;
        boolean currentMinFound = false;
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            if (i % windowSize == 0) {
                result.add(currentMin);
                System.out.println("Cur Min: " + currentMin);
                currentMinFound = false;
            }
            if (!currentMinFound) {
                currentMin = input[i];
                currentMinFound = true;
                continue;
            }
            if (input[i] < currentMin) {
                currentMin = input[i];
            }
        }
        return result;
    }
}

