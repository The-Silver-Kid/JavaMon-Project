package DevTSK.JavaMon;

import java.util.Random;

public class DamageCalculation {

	public static final double[] STATSTAGES = new double[] {
			2 / 8,
			2 / 7,
			2 / 6,
			2 / 5,
			2 / 4,
			2 / 3,
			2 / 2,
			3 / 2,
			4 / 2,
			5 / 2,
			6 / 2,
			7 / 2,
			8 / 2 };

	public static int calc(Monster atk, Monster def, Move m) {
		double damage = 0;
		Random r = new Random();
		double rand = r.nextDouble();
		do {
			rand = r.nextDouble();
		} while (rand > 1 || rand < .85);

		double stab = 1, crit = 1, oth = 1;

		//Check for Same type
		Type[] moveType = m.getType().split();
		Type[] defType = atk.getType().split();
		for (int i = 0; i < moveType.length; i++)
			for (int o = 0; o < defType.length; o++)
				if (moveType[i].toString().equals(defType[o].toString()))
					stab = 1.5;

		//Critical hit check
		double getCrit = atk.getStat(Monster.SPEED) / 2;
		if (getCrit > r.nextInt(256))
			crit = 1.5;

		//announce victory
		if (crit == 1.5)
			System.out.println("Critical Hit!");
		if (stab == 1.5)
			System.out.println("STAB");

		double mod = stab * Type.getTypeEffectiveness(m.getType(), def.getType()) * crit * oth * rand;

		if (m.getCategory() == 0)
			damage = ((((double) 2 * atk.getLevel() + 10) / (250)) * (((double) atk.getStat(Monster.ATTACK)) / (def.getStat(Monster.DEFENSE))) * m.getBase()[0] + 2) * mod;
		else if (m.getCategory() == 1)
			damage = (((2 * atk.getLevel() + 10) / (250)) * ((atk.getStat(Monster.SATTACK)) / (def.getStat(Monster.SDEFENSE))) * m.getBase()[0] + 2) * mod;
		else
			System.exit(0);

		System.out.println((2 * atk.getLevel() + 10) + " " + atk.getStat(Monster.ATTACK) + " " + (m.getBase()[0] + 2));
		System.out.println(250 + " " + def.getStat(Monster.DEFENSE));
		System.out.println(mod);
		System.out.println(damage);

		return (int) damage;
	}

	public static int statusCalc(int i, int bPoison, int totalHP) {
		switch (i) {
		case Status.POISON:
			return (int) ((double) totalHP * (1 / 8));
		case Status.BPOISON:
			return (int) ((double) totalHP * ((double) bPoison / 16));
		case Status.BURN:
			return (int) ((double) totalHP * (1 / 16));
		}
		return 0;
	}
}
