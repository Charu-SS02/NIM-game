/*
	NimAIPlayer.java

	This class is provided as a skeleton code for the tasks of
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks.
*/

/** Simple variant of game Nim
*
* @author Charu
*
*/
import java.util.*;

public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the tasks.
	public NimAIPlayer(String u, String g, String f, int gp, int gw) {
		super(u, g, f, gp, gw, true);
	}

	public int removeStone(int init_stones, int M) {
		int srem = (init_stones - 1) % (M + 1);

		if (srem != 0) {
			return srem;
		}

		return 1;

		
	}

	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";

		return move;
	}
}
