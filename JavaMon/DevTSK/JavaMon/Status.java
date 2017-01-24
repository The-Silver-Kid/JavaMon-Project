package DevTSK.JavaMon;

import java.util.Random;

public class Status {

	private static final String[] names = new String[] {
			"CLEAR", "POISON", "BURN", "FROZEN", "PARALYSIS", "POISON", "SLEEP", "FADING"
	};

	public static final int CLEAR = 0, POISON = 1, BURN = 2, FROZEN = 3, PARALYSIS = 4, BPOISON = 5, SLEEP = 6, FADING = 7;

	private int state = 0, bPoison = 0;

	public Status(int i) {
		state = i;
		if (state < 0 || state > 6)
			state = 0;
	}

	public void changeStatus(int status, Boolean usedRest) {
		if (!usedRest)
			bPoison = 0;
		state = status;
	}

	public int effect(int totalHP) {
		switch (state) {
		case 0:
			break;
		case 1:
			return DamageCalculation.statusCalc(1, 0, totalHP);
		case 2:
			return DamageCalculation.statusCalc(2, 0, totalHP);
		case 3:
			Random r = new Random();
			if (r.nextInt(100) > 20)
				changeStatus(0, false);
			break;
		case 4:
			//TODO : pear
			break;
		case 5:
			return DamageCalculation.statusCalc(1, bPoison, totalHP);
		case 6:
			//TODO : Sleep
			break;
		case 7:
			return DamageCalculation.statusCalc(7, 0, totalHP);
		default:
			System.out.println("oops.\nSomehow an illegal status was applied to a pokemon...\nClearing that up...");
			this.state = 0;
		}
		return 0;
	}

	public String toString() {
		return names[state];
	}
}
