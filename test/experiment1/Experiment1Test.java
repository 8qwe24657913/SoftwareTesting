package experiment1;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Experiment1Test {
	Experiment1 obj = null;
	static final int denomination[] = {50, 20, 10, 5, 1};
	static final int amount[] = {1, 1, 1, 2, 3};
	@Before
	public void init() {
		obj = new Experiment1();
	}
	
	@Test
	public void testSuspiciousValues() {
		// zero
		assertEquals(true, obj.canTake(0));
		
		// minus value(s)
		assertEquals(false, obj.canTake(-1));
		
		// possibly overflow
		assertEquals(false, obj.canTake(Integer.MAX_VALUE));
		assertEquals(false, obj.canTake(Integer.MIN_VALUE));
		
		// regular amount
		assertEquals(true, obj.canTake(1));
		assertEquals(true, obj.canTake(2));
		assertEquals(true, obj.canTake(5));
		assertEquals(true, obj.canTake(8));
		assertEquals(false, obj.canTake(14));
		assertEquals(true, obj.canTake(93));
		assertEquals(false, obj.canTake(94));
		assertEquals(false, obj.canTake(100));
	}

	@Test
	public void testAllPossibleValues() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(0);
		for (int i = 0; i < denomination.length; i++) {
			Set<Integer> newSet = new HashSet<Integer>();
			for (int j = 0; j < amount[i]; j++) {
				int value = denomination[i] * j;
				for (int num : set) {
					newSet.add(num + value);
				}
			}
			set = newSet;
		}
		for (int testCase : set) {
			assertEquals(true, obj.canTake(testCase));
		}
		// random tests
		Random random = new Random();
		for (int i = 0; i < 100; i++) { // 100 cases
			int testCase = random.nextInt();
			assertEquals(set.contains(testCase), obj.canTake(testCase));
		}
	}
}
