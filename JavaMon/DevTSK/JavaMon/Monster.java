package DevTSK.JavaMon;

public abstract class Monster {

	//TODO : Handle move sets

	public static final int HP = 0, ATTACK = 1, DEFENSE = 2, SATTACK = 3, SDEFENSE = 4, SPEED = 5;

	protected MoveSet moveset;

	protected static final String[] statNames = new String[] { "HP", "ATTACK", "DEFENSE",
			"SPECIAL ATTACK", "SPECIAL DEFENSE", "SPEED" };

	protected int[] EV = new int[] { 0, 0, 0, 0, 0, 0 },
			EVgained = new int[] { 0, 0, 0, 0, 0, 0 },
			IV = new int[] { 0, 0, 0, 0, 0, 0 },
			baseStats = new int[] { 0, 0, 0, 0, 0, 0 },
			stats = new int[] { 0, 0, 0, 0, 0, 0 };

	protected int[] EVYield;

	protected Nature nature;

	protected Move[] attacks = new Move[4];

	protected int cHP, EXP;

	protected byte level = 1;

	protected Type type = new Type("Normal", "Flying");

	//Not wild
	public Monster(byte level, int[] EVs, int[] baseStats, int[] EVYield, int[] IVs, Nature nature, Type type) {
		this.level = level;

		this.EVYield = EVYield;
		this.IV = IVs;
		this.EV = EVs;

		this.baseStats = baseStats;

		this.nature = nature;

		calculateLevelUp(baseStats, EVs, IVs, level);

		this.type = type;
	}

	//WILD
	public Monster(byte level, int[] IVs, Nature nature, Type type) {
		this.level = level;

		this.IV = IVs;

		this.nature = nature;

		calculateLevelUp(baseStats, this.EV, IVs, level);

		this.type = type;
	}

	public int getStat(int stat) {
		if (stat < 0 || stat > 5) {
			return 0;
		}
		return stats[stat];
	}

	public byte getLevel() {
		return level;
	}

	public void getEXP(int i) {
		if (level == (byte) 100)
			return;
		EXP += i;
	}

	public void levelUp() {
		level = (byte) (level + 1);
		for (int i = 0; i < EV.length; i++)
			EV[i] = EV[i] + EVYield[i];
		calculateLevelUp(baseStats, EV, IV, (byte) 1);
	}

	protected void calculateLevelUp(int[] baseStats, int[] EVs, int[] IVs, byte level) {
		int[] s = stats;

		for (int i = 0; i < stats.length; i++) {
			if (i == 0)
				s[i] = (((2 * baseStats[i] + IVs[i] + (EVs[i] / 4)) * level) / 100) + level + 10;
			else
				s[i] = (int) (((((2 * baseStats[i] + IVs[i] + (EVs[i] / 4)) * level) / 100) + 5) * nature.getNatureValue(i));
		}

		stats = s;
	}

	public int getCurrentHP() {
		return cHP;
	}

	public void setCurrentHP(int cHP) {
		this.cHP = cHP;
	}

	public void addMoveset(MoveSet moves) {
		this.moveset = moves;
	}

	public Type getType() {
		return type;
	}
}
