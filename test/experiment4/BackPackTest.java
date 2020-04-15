package experiment4;

import static experiment4.BackPack.BackPack_Solution;
import static experiment4.BubbleSort.BubbleSort;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class BackPackTest {
    @Test(expected=NegativeArraySizeException.class)
    public void testArraySizeDimension1() {
        BackPack_Solution(Integer.MAX_VALUE, 1, new int[] {1}, new int[] {1});
    }
    @Test(expected=NegativeArraySizeException.class)
    public void testArraySizeDimension2() {
        BackPack_Solution(1, Integer.MAX_VALUE, new int[] {1}, new int[] {1});
    }
    @Test(expected=NullPointerException.class)
    public void testNullPointerWeight() {
        BackPack_Solution(1, 1, null, new int[] {1});
    }
    @Test(expected=NullPointerException.class)
    public void testNullPointerPrice() {
        BackPack_Solution(1, 1, new int[] {1}, null);
    }
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testArrayBoundWeight() {
        BackPack_Solution(1, 1, new int[] {}, new int[] {1});
    }
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testArrayBoundPrice() {
        BackPack_Solution(1, 1, new int[] {1}, new int[] {});
    }
    private void testConcise(int mass, int num, int[] weight, int[] price) {
        int[][] test = BackPack_Solution(mass, num, Arrays.copyOf(weight, num), Arrays.copyOf(price, num));
        assertNotNull(test);
        assertEquals(num + 1, test.length);
        for (int i = 0; i < num + 1; i++) {
            assertNotNull(test[i]);
            assertEquals(mass + 1, test[i].length);
            assertEquals(0, test[i][0]);
        }
        for (int i = 1; i < mass + 1; i++) {
            assertEquals(0, test[0][i]);
        }
        for (int i = 1; i < num + 1; i++) {
            for (int j = 1; j < mass + 1; j++) {
                int ans = test[i- 1][j];
                if (j >= weight[i - 1]) {
                    ans = Math.max(ans, test[i - 1][j - weight[i - 1]] + price[i - 1]);
                }
                assertEquals(ans, test[i][j]);
            }
        }
    }
    @Test
    public void testSpecialValues() {
        testConcise(0, 0, new int[] {}, new int[] {});
        testConcise(1, 1, new int[] {1}, new int[] {1});
        testConcise(10, 3, new int[] {3, 4, 5}, new int[] {4, 5, 6});
        testConcise(10, 3, new int[] {3, 4, 5, 3}, new int[] {4, 5, 6, 6}); // would you use data beyond num?
    }
    @Test
    public void testRandomValues() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int num = random.nextInt(100);
            int[] weight = new int[num];
            int[] price = new int[num];
            for (int j = 0; j < num; j++) {
                weight[j] = random.nextInt(100);
                price[j] = random.nextInt(100);
            }
            testConcise(random.nextInt((num == 0 ? 1 : num) * 100), num, weight, price);
        }
    }
}
