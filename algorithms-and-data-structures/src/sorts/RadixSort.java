package sorts;

import java.util.Arrays;


/**
 * Radix sort is a non-comparative integer sorting algorithm that sorts data with integer keys by grouping keys 
 * by the individual digits which share the same significant position and value. A positional notation is required, 
 * but because integers can represent strings of characters (e.g., names or dates) and specially formatted floating 
 * point numbers, radix sort is not limited to integers.
 * Family: Bucket.
 * Space: 10 Buckets with at most n integers per bucket.
 * Stable: True.
 * 
 * Average case = O(n*k)
 * Worst case = O(n*k)
 * Best case = O(n*k)
 * NOTE: n is the number of digits and k is the average bucket size
 * 
 * http://en.wikipedia.org/wiki/Radix_sort
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class RadixSort {

    private static final int NUMBER_OF_BUCKETS = 10; // 10 for base 10 numbers


    private RadixSort() { }

    public static Integer[] sort(Integer[] unsorted) {
        int[][] buckets = new int[NUMBER_OF_BUCKETS][10];
        for (int i=0; i<NUMBER_OF_BUCKETS; i++) buckets[i][0] = 1; //Size is one since the size is stored in first element
        int numberOfDigits = getMaxNumberOfDigits(unsorted); //Max number of digits
        int divisor = 1;
        for (int n=0; n<numberOfDigits; n++) {
            int digit = 0;
            for (int d : unsorted) {
                digit = getDigit(d,divisor);
                buckets[digit] = add(d,buckets[digit]);
            }
            int index = 0;
            for (int i=0; i<NUMBER_OF_BUCKETS; i++) {
                int[] bucket = buckets[i];
                int size = bucket[0];
                for (int j=1; j<size; j++) {
                    unsorted[index++] = bucket[j];
                }
                buckets[i][0] = 1; //reset the size
            }
            divisor *= 10;
        }
        return unsorted;
    }

    private static int getMaxNumberOfDigits(Integer[] unsorted) {
        int max = Integer.MIN_VALUE;
        int temp = 0;
        for (int i : unsorted) {
            temp = (int)Math.log10(i)+1;
            if (temp>max) max=temp;
        }
        return max;
    }

    private static int getDigit(int integer, int divisor) {
        return (integer / divisor) % 10;
    }

    private static int[] add(int integer, int[] bucket) {
        int size = bucket[0]; //size is stored in first element
        int length = bucket.length;
        if (size>=length) {
            bucket = Arrays.copyOf(bucket, ((length*3)/2)+1);
        }
        bucket[size] = integer;
        bucket[0] = ++size;
        return bucket;
    }
}
