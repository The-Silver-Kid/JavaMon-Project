package DevTSK.JavaMon;

import java.util.Random;

public abstract class Monster {

	//TODO : Handle move sets

	public static final int HP = 0, ATTACK = 1, DEFENSE = 2, SATTACK = 3, SDEFENSE = 4, SPEED = 5, ACCURACY = 6, EVASIVENESS = 7;

	protected MoveSet moveset;

	protected static final String[] statNames = new String[] { "HP", "ATTACK", "DEFENSE",
			"SPECIAL ATTACK", "SPECIAL DEFENSE", "SPEED" };

	protected int[] EV = new int[] { 0, 0, 0, 0, 0, 0 },
			EVgained = new int[] { 0, 0, 0, 0, 0, 0 },
			IV = new int[] { 0, 0, 0, 0, 0, 0 },
			baseStats = new int[] { 0, 0, 0, 0, 0, 0 },
			stats = new int[] { 0, 0, 0, 0, 0, 0 };

	protected EggGroup eg = new EggGroup("Undiscovered");

	// Same as attacks followed by accuracy and evasiveness
	public int[] inBattleStats = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
	public Boolean flinched = false;
	public Boolean canAttack = true;
	public Status status = new Status(Status.CLEAR);

	protected int[] EVYield;

	protected Nature nature;

	protected Move[] attacks = new Move[4];

	protected int cHP, EXP;

	protected byte level = 1;

	protected Type type = new Type("Normal", "Flying");

	//TODO : Rewrite constructors.
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

	public int setBattleStats(int stat, int levels) {
		int ret;
		if (this.inBattleStats[stat] == 6 || this.inBattleStats[stat] == -6)
			ret = 0;
		else if (this.inBattleStats[stat] + levels > 6) {
			this.inBattleStats[stat] = 6;
			ret = 1;
		} else if (this.inBattleStats[stat] + levels < -6) {
			this.inBattleStats[stat] = -6;
			ret = 1;
		} else {
			this.inBattleStats[stat] += levels;
			ret = 1;
		}
		return ret;
	}

	public int causeFlinch() {
		this.flinched = true;
		return 1;
	}

	public int applyStatus(int status, Boolean usedRest) {
		if (usedRest == false)
			if (this.status.toString().equals("CLEAR")) {
				this.status.changeStatus(status, usedRest);
				if (status == Status.BURN)
					this.inBattleStats[Monster.ATTACK] = (int) (this.inBattleStats[Monster.ATTACK] / 2);
				if (status == Status.FROZEN)
					this.canAttack = false;
				return 1;
			} else {
				return 0;
			}
		else {
			this.status.changeStatus(status, usedRest);
			return 1;
		}
	}

	public void update() {
		Random r = new Random();
		if (!this.status.toString().equals("FROZEN") && !this.status.toString().equals("SLEEP"))
			this.canAttack = true;
		if (this.status.toString().equals("PARALYZED"))
			if (r.nextInt(100) < 25)
				this.canAttack = false;
	}
}
