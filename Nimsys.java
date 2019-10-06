/** Simple variant of game Nim
*
* @author Charu
*
*/

import java.util.*;
import java.io.*;

public class Nimsys {
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to Nim");

		NimGame game = new NimGame(); //Creation of NimGame object
		Scanner sc = new Scanner(System.in); //Creation of Scanner object to manage input from user

		//Loop to analyse the input given by the user and manage the calls to functions in NimGame class
		while (true) {
			try {
				System.out.print("\n$");

				String input = sc.nextLine();

				//Splitting the user input at whitespaces
				//Command name stored at index 0
				//Command parameters stored at succeeding indices
				String[] inputstr = input.trim().split("\\s+");

				//Error handling for incorrect number of command arguments
				if (inputstr.length > 2) {
					throw new IncorrectArgumentsException();
				}

				if (inputstr[0].equals("addaiplayer")) {				//Analysing addaiplayer input from the user
					String[] details = inputstr[1].split(",");			//Splitting the user input at commas

					if (details.length != 3) {
						throw new IncorrectArgumentsException();
					}

					game.addaiplayer(details[0], details[2], details[1]);
				} else if (inputstr[0].equals("addplayer")) {			//Analysing addplayer input from the user
					String[] details = inputstr[1].split(",");			//Splitting the user input at commas

					if (details.length != 3) {
						throw new IncorrectArgumentsException();
					}

					game.addplayer(details[0], details[2], details[1]);
				} else if (inputstr[0].equals("removeplayer")) {		//Analysing removeplayer input
					if (inputstr.length > 1) {
						String[] details = inputstr[1].split(",");

						if (details.length != 1) {
							throw new IncorrectArgumentsException();
						}

						game.removeplayer(details[0], false);
					} else {
						System.out.println("Are you sure you want to remove all players? (y/n)");
						String res = sc.nextLine();

						if (res.charAt(0) == 'y') {
							game.removeplayer("", true);
						}
					}
				} else if (inputstr[0].equals("editplayer")) {			//Analysing editplayer input from the user
					String[] details = inputstr[1].split(",");

					if (details.length != 3) {
						throw new IncorrectArgumentsException();
					}

					game.editplayer(details[0], details[2], details[1]);
				} else if (inputstr[0].equals("resetstats")) {			//Analysing resetstats input from the user
					if (inputstr.length > 1) {
						String[] details = inputstr[1].split(",");

						if (details.length != 1) {
							throw new IncorrectArgumentsException();
						}

						game.resetstats(details[0], false);
					} else {
						System.out.println("Are you sure you want to reset all player statistics? (y/n)");
						String res = sc.nextLine();

						if (res.charAt(0) == 'y') {
							game.resetstats("", true);
						}
					}
				} else if (inputstr[0].equals("displayplayer")) {		//Analysing displayplayer input from the user
					if (inputstr.length > 1) {
						String[] details = inputstr[1].split(",");

						if (details.length != 1) {
							throw new IncorrectArgumentsException();
						}

						game.displayplayer(details[0], false);
					} else {
						game.displayplayer("", true);
					}
				} else if (inputstr[0].equals("rankings")) {			//Analysing rankings input from the user
					if (inputstr.length > 1) {
						String[] details = inputstr[1].split(",");

						if (details.length != 1) {
							throw new IncorrectArgumentsException();
						}

						if (details[0].equals("desc")) {
							game.rankings(true);
						} else if (details[0].equals("asc")) {
							game.rankings(false);
						}
					} else {
						game.rankings(true);
					}
				} else if (inputstr[0].equals("startgame")) {			//Analysing startgame input from the user
					String[] details = inputstr[1].split(",");

					if (details.length != 4) {
						throw new IncorrectArgumentsException();
					}

					game.startgame(sc, Integer.parseInt(details[0]), Integer.parseInt(details[1]), details[2], details[3]);
				} else if (inputstr[0].equals("exit")) {				//Analysing exit input from the user
					if (inputstr.length > 1) {
						throw new IncorrectArgumentsException();
					}

					game.save();	//Save current user statistics

					System.out.println();
					System.exit(0);						//Exiting game
				} else {
					//Error handling for invalid command
					throw new InvalidCommandException(inputstr[0]);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
}
