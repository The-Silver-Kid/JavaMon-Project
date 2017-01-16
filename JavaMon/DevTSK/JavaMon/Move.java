package DevTSK.JavaMon;

public abstract class Move {

	//TODO : Priority ordering...

	protected Boolean neverMiss, OHKO;

	protected Type type = new Type("Normal");

	protected double baseDamage = 0, baseAccuracy = 0, priority = 1;

	protected int category = 0, PP = 0;

	protected String name = "Null";

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public double[] getBase() {
		return new double[] { baseDamage, baseAccuracy, priority };
	}

	public abstract void getEffect(Monster atk, Monster def);

	public int getPhysical() {
		return category;
	}

	public int getCategory() {
		return category;
	}

	public Boolean neverMisses() {
		return neverMiss;
	}

	public Boolean isOHKO() {
		return OHKO;
	}
}
