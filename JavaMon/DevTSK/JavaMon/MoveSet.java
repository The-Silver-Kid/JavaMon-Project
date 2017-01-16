package DevTSK.JavaMon;

public class MoveSet {

	private byte[] level;
	private Move[] Move;

	public MoveSet(byte[] level, Move[] Move) {
		this.level = level;
		this.Move = Move;
	}

	public Move getMove(byte level) {
		Move ret = null;
		for (int i = 0; i < Move.length; i++)
			if (this.level[i] == level)
				ret = Move[i];

		return ret;
	}
}
