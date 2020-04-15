package experiment4;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;

import static experiment4.BubbleSort.BubbleSort;
import org.junit.Test;

public class BubbleSortTest {
    @Test(expected=NullPointerException.class) 
    public void testNullPointer() {
        BubbleSort(null);
    }
    @Test
    public void testSpecialValues() {
        assertArrayEquals(new int[]{}, BubbleSort(new int[]{}));
        assertArrayEquals(new int[]{0}, BubbleSort(new int[]{0}));
        assertArrayEquals(new int[]{0, 1}, BubbleSort(new int[]{0, 1}));
        assertArrayEquals(new int[]{0, 1}, BubbleSort(new int[]{1, 0}));
        assertArrayEquals(new int[]{-1, 0}, BubbleSort(new int[]{0, -1}));
        assertArrayEquals(new int[]{-1, 0}, BubbleSort(new int[]{-1, 0}));
        assertArrayEquals(new int[]{Integer.MIN_VALUE, 0}, BubbleSort(new int[]{0, Integer.MIN_VALUE}));
        assertArrayEquals(new int[]{Integer.MIN_VALUE, 0}, BubbleSort(new int[]{Integer.MIN_VALUE, 0}));
        assertArrayEquals(new int[]{0, Integer.MAX_VALUE}, BubbleSort(new int[]{Integer.MAX_VALUE, 0}));
        assertArrayEquals(new int[]{0, Integer.MAX_VALUE}, BubbleSort(new int[]{0, Integer.MAX_VALUE}));
        assertArrayEquals(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, BubbleSort(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}));
        assertArrayEquals(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, BubbleSort(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}));
        assertArrayEquals(new int[]{Integer.MIN_VALUE, 0, 1, Integer.MAX_VALUE}, BubbleSort(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 0}));
        assertArrayEquals(new int[]{Integer.MIN_VALUE, 0, 1, Integer.MAX_VALUE}, BubbleSort(new int[]{1, Integer.MIN_VALUE, 0, Integer.MAX_VALUE}));
        assertArrayEquals(new int[]{1,2,2,5,6}, BubbleSort(new int[]{1,6,2,2,5}));
    }
    @Test
    public void testRandomValues() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arr = new int[random.nextInt(100)];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = random.nextInt();
            }
            int[] test = BubbleSort(Arrays.copyOf(arr, arr.length));
            Arrays.sort(arr);
            assertArrayEquals(arr, test);
        }
    }
}
