package DevTSK.JavaMon.Defaults;

import DevTSK.JavaMon.Monster;
import DevTSK.JavaMon.Move;
import DevTSK.JavaMon.Type;

public class Pound extends Move {

	/* Category
	 * 0 = Physical
	 * 1 = Special
	 * 2 = Status
	 */

	public Pound() {
		neverMiss = false;
		OHKO = false;

		type = new Type("Normal");

		baseDamage = 40;
		baseAccuracy = 1;
		PP = 35;

		priority = 1;

		category = 0;

		name = "Pound";
	}

	@Override
	public void getEffect(Monster atk, Monster def) {
	}

}
