package experiment1;

public class Experiment1 {
	final static int denomination[] = {50, 20, 10, 5, 1};
	final static int amount[] = {1, 1, 1, 2, 3};
	public Experiment1() {
		assert(denomination.length == amount.length);
	}
	private boolean canTakeImpl(int x, int start) {
		if (x == 0) return true;
		for (int i = start; i < denomination.length; i++) {
			for (int j = Math.min(x / denomination[i], amount[i]); j >= 0; j--) {
				if (canTakeImpl(x - denomination[i] * j, i + 1)) return true;
			}
		}
		return false;
	}
	public boolean canTake(int x) {
		if (x < 0) return false;
		return canTakeImpl(x, 0);
	}
}
