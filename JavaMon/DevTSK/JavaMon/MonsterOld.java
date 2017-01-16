package DevTSK.JavaMon;

public class MonsterOld {

	public static final int HP = 0, ATTACK = 1, DEFENSE = 2, SATTACK = 3, SDEFENSE = 4, SPEED = 5;

	//TODO : Create Learning of moves...
	@SuppressWarnings("unused")
	private MoveSet moveset;

	private static final String[] statNames = new String[] { "HP", "ATTACK", "DEFENSE",
			"SPECIAL ATTACK", "SPECIAL DEFENSE", "SPEED" };

	private int[] EV = new int[] { 0, 0, 0, 0, 0, 0 },
			/*EVgained = new int[] { 0, 0, 0, 0, 0, 0 },*/
			IV = new int[] { 0, 0, 0, 0, 0, 0 },
			baseStats = new int[] { 0, 0, 0, 0, 0, 0 },
			stats = new int[] { 0, 0, 0, 0, 0, 0 };

	private final int[] EVYield;

	private Nature nature;

	@SuppressWarnings("unused")
	private Move[] attacks = new Move[4];

	private int cHP;

	private byte level = 1;

	private Type type = new Type("Normal", "Flying");

	public int getStat(int stat) {
		if (stat < 0 || stat > 5) {
			return 0;
		}
		return stats[stat];
	}

	public MonsterOld(byte level, int[] EVs, int[] baseStats, int[] EVYield, int[] IVs, Nature nature, Type type) {
		this.level = level;

		this.EVYield = EVYield;
		this.IV = IVs;
		this.EV = EVs;

		this.baseStats = baseStats;

		this.nature = nature;

		this.stats = calculateLevelUp(new int[] { 0, 0, 0, 0, 0, 0 }, baseStats, EVs, IVs, level);

		this.type = type;
	}

	public MonsterOld(byte level, int[] baseStats, int[] EVYield, int[] IVs, Nature nature, Type type) {
		this.level = level;

		this.EVYield = EVYield;
		this.IV = IVs;

		this.baseStats = baseStats;

		this.nature = nature;

		this.stats = calculateLevelUp(new int[] { 0, 0, 0, 0, 0, 0 }, baseStats, this.EV, IVs, level);

		this.type = type;
	}

	public byte getLevel() {
		return level;
	}

	public void levelUp() {
		level = (byte) (level + 1);
		for (int i = 0; i < EV.length; i++)
			EV[i] = EV[i] + EVYield[i];
		stats = calculateLevelUp(stats, baseStats, EV, IV, (byte) 1);
	}

	private int[] calculateLevelUp(int[] stats, int[] baseStats, int[] EVs, int[] IVs, byte level) {
		int[] s = stats;

		for (int i = 0; i < stats.length; i++) {
			if (i == 0)
				s[i] = (((2 * baseStats[i] + IVs[i] + (EVs[i] / 4)) * level) / 100) + level + 10;
			else
				s[i] = (int) (((((2 * baseStats[i] + IVs[i] + (EVs[i] / 4)) * level) / 100) + 5) * nature.getNatureValue(i));
		}

		return s;
	}

	public int getCurrentHP() {
		return cHP;
	}

	public void setCurrentHP(int cHP) {
		this.cHP = cHP;
	}

	public void debug() {
		for (int i = 0; i < EV.length; i++)
			System.out.println(statNames[i] + " : " + stats[i] + " " + EV[i] + " " + IV[i]);
	}

	public void addMoveset(MoveSet moves) {
		this.moveset = moves;
	}

	public Type getType() {
		return type;
	}
}
