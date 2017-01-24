package DevTSK.JavaMon;

public class EggGroup {

	private int[] ID = new int[] { -1, -1 };

	public static final String[] NAMES = new String[] {
			"Monster", "Human-Like", "Water1", "Water3", "Bug", "Mineral", "Flying", "Amorphous", "Field", "Water2",
			"Fairy", "Ditto", "Grass", "Dragon", "Undiscovered", "Gender-Unknown"
	};

	public EggGroup(int primary, int secondary) {
		if (primary < 1 || primary > NAMES.length) {
			System.err.println("Lol\nLook at you trying to create something with a primary egg group that doesn't exist!");
			primary = -1;
		}
		if (secondary < 1 || secondary > NAMES.length) {
			System.err.println("Lol\nLook at you trying to create something with a secondary egg group that doesn't exist!");
			secondary = -1;
		}
		this.ID = new int[] { primary, secondary };
	}

	public EggGroup(int egggroup) {
		if (egggroup < 0 || egggroup > NAMES.length) {
			System.err.println("Lol\nLook at you trying to create something with a egg group that doesn't exist!");
		}
		this.ID = new int[] { egggroup, egggroup };
	}

	public EggGroup(String primaryType, String secondaryType) {
		int primary = -1, secondary = -1;
		for (int i = 0; i < NAMES.length; i++) {
			if (primaryType.equalsIgnoreCase(NAMES[i]))
				primary = i;
			if (secondaryType.equalsIgnoreCase(NAMES[i]))
				secondary = i;
		}
		if (primary == -1) {
			System.err.println("Lol\nLook at you trying to create something with a primary egg group that doesn't exist!");
		}
		if (secondary == -1) {
			System.err.println("Lol\nLook at you trying to create something with a secondary egg group that doesn't exist!");
		}

		this.ID = new int[] { primary, secondary };
	}

	public EggGroup(String egggroup) {
		int primary = -1;
		for (int i = 0; i < NAMES.length; i++) {
			if (egggroup.equalsIgnoreCase(NAMES[i]))
				primary = i;
			//System.out.println(NAMES[i]);
		}
		if (primary == -1) {
			System.err.println("Lol\nLook at you trying to create something with a egg group that doesn't exist!");
		}

		this.ID = new int[] { primary, primary };
	}

	//TODO : Can Breed Method

	private int[] getID() {
		return ID;
	}

	public static String toString(EggGroup egggroup) {
		return NAMES[egggroup.getID()[0]] + " " + NAMES[egggroup.getID()[1]];
	}

	public String toString() {
		return NAMES[ID[0]] + " " + NAMES[ID[1]];
	}

	public EggGroup[] split() {
		return new EggGroup[] { new EggGroup(ID[0]), new EggGroup(ID[1]) };
	}
}
