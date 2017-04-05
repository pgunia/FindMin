import java.util.Arrays;

public class FindMinMain {

    public static void main(String...args) {

        final Integer[] input = {1,4,6,2,4,9,5,7,2,8,5,3,123,21,54,12};
        final int windowSize = 4;

        final int arrayLength = input.length;
        for(int i = 0; i <= (arrayLength - windowSize); i++) {
            System.out.println("Cur Min: " + getMin(Arrays.copyOfRange(input, i, i + windowSize)));
        }
    }

    private static int getMin(final Integer[] window) {

        // let's say we only have positive integers
        int curMin = Integer.MAX_VALUE;
        for(Integer cur : window) {
            if(cur < curMin) {
                curMin = cur;
            }
        }
        return curMin;
    }
}
