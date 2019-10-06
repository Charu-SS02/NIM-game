/** Simple variant of game Nim
*
* @author Charu
*
*/

import java.util.*;

class NimPlayer {
	//Declaration of variables for storing user details
	String username;
	String given_name;
	String family_name;
	int games_played;
	int games_won;
	boolean ai;	//Check if player is AI or human ("true" for AI and "false" for human)

	//Constructor to set the input given by the user
	public NimPlayer(String u, String g, String f, int gp, int gw, boolean ai) {
		this.username = u;
		this.given_name = g;
		this.family_name = f;
		this.games_played = gp;
		this.games_won = gw;
		this.ai = ai;
	}

	/*Method to return the number of stones each player wants to remove in his turn,
	also passes the scanner object of Nimsys class as an argument*/
	public String removeStone(Scanner sc) {
		String numofstones_remove = sc.nextLine();

		return numofstones_remove;
	}

	//Printing the number of stones left and asterisks accordingly
	public void printStone(int numofstones) {
		System.out.print("\n" + numofstones + " stones left:");

		for (int i = 0; i < numofstones; i++) {
			System.out.print(" *");
		}
	}
}
