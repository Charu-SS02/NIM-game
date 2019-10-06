/** Simple variant of game Nim
*
* @author Charu
*
*/

import java.util.*;
import java.lang.*;
import java.io.*;

public class NimGame {
    NimPlayer[] stats;      //Creation of NimPlayer array of objects
    int len;                //Variable to store number of players currently stored in the NimPlayer array

    public NimGame() throws IOException {
        this.stats = new NimPlayer[100];	//NimGame constructor to initialise the NimPlayer array with a maximum of 100 players
        this.len = 0;

        //Load existing user data from file (if exists)
        File fp = new File("players.dat");

        if (fp.exists()) {
            FileInputStream fis = new FileInputStream(fp);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String filestr;
            while ((filestr = br.readLine()) != null) {
                String[] fc = filestr.split(",");

                if (fc[5].equals("true")) {     //Player is AI
                    this.stats[this.len] = new NimAIPlayer(fc[0], fc[1], fc[2], Integer.parseInt(fc[3]), Integer.parseInt(fc[4]));
                } else {                        //PLayer is human
                    this.stats[this.len] = new NimHumanPlayer(fc[0], fc[1], fc[2], Integer.parseInt(fc[3]), Integer.parseInt(fc[4]));
                }

                this.len++;
            }
        }
    }

    //Method to add an AI player to the array of players
    public void addaiplayer(String u, String g, String f) {
        for (int i = 0; i < this.len; i++) {
            if (this.stats[i].username.equals(u)) {
                // do nothing if user already exists
                System.out.println("The player already exists.");

                return;
            }
        }

        //add new user to Nimplayer array and increment length by 1
        this.stats[this.len] = new NimAIPlayer(u, g, f, 0, 0);
        this.len++;
    }

    //Method to add a human player to the array of players
    public void addplayer(String u, String g, String f) {
        for (int i = 0; i < this.len; i++) {
            if (this.stats[i].username.equals(u)) {
                // do nothing if user already exists
                System.out.println("The player already exists.");

                return;
            }
        }

        //add new user to Nimplayer array and increment length by 1
        this.stats[this.len] = new NimHumanPlayer(u, g, f, 0, 0);
        this.len++;
    }

    //Method to remove players from the array
    public void removeplayer(String u, boolean all) {
        if (all) {
            //set length to 0 if all players need to be removed
            this.len = 0;
        } else {
            boolean flag = false;

            for (int i = 0; i < this.len; i++) {
                //shift all succeeding Nimplayer objects to the left by 1 index after user is encountered in NimPlayer array
                if (this.stats[i].username.equals(u) || flag) {
                    if (!flag) {
                        flag = true;
                    }

                    this.stats[i] = this.stats[i + 1];
                }
            }

            if (flag) {
                //user removed from Nimplayer array
                this.len--;
            } else {
                //user did not exist in NimPlayer array
                System.out.println("The player does not exist.");
            }
        }
    }

    //Method to make changes to the user details
    public void editplayer(String u, String g, String f) {
        for (int i = 0; i < this.len; i++) {
            if (this.stats[i].username.equals(u)) {
                //user found in NimPlayer array and his details are edited
                this.stats[i].given_name = g;
                this.stats[i].family_name = f;

                return;
            }
        }

        //otherwise, user does not exist
        System.out.println("The player does not exist.");
    }

    //Method to reset the player statistics
    public void resetstats(String u, boolean all) {
        if (all) {
            //reset stats for all players
            for (int i = 0; i < this.len; i++) {
                this.stats[i].games_played = 0;
                this.stats[i].games_won = 0;
            }
        } else {
            //search for specified user
            for (int i = 0; i < this.len; i++) {
                //user found in array and his stats are reset
                if (this.stats[i].username.equals(u)) {
                    this.stats[i].games_played = 0;
                    this.stats[i].games_won = 0;

                    return;
                }
            }

            //otherwise, user does not exist
            System.out.println("The player does not exist.");
        }
    }

    //Method to display the player rankings
    public void rankings(boolean desc) {
        NimPlayer[] player_rank = this.stats.clone();   //Create a copy of NimPlayer array

        //perform insertion sort on new NimPlayer array
        //primary sorting is by win ratio (ascending or descending)
        //secondary sorting (in case of tie) is by ascending order of username

        if (desc) {
            for (int i = 1; i < this.len; i++) {
                NimPlayer key = player_rank[i];
                int j = i - 1;

                while (j >= 0 && ((((double) player_rank[j].games_won / Math.max(1, player_rank[j].games_played)) > ((double) key.games_won / Math.max(1, key.games_played))) || ((((double) player_rank[j].games_won / Math.max(1, player_rank[j].games_played)) == ((double) key.games_won / Math.max(1, key.games_played))) && (player_rank[j].username.compareTo(key.username) < 0)))) {
                    player_rank[j + 1] = player_rank[j];
                    j--;
                }

                player_rank[j + 1] = key;
            }

            //display rankings in descending order (maximum 10 players)
            for (int i = Math.min(10, this.len) - 1; i >= 0; i--) {
                //calculate win ratio for each player
                long win_ratio = Math.round(100.0 * ((double) player_rank[i].games_won / Math.max(1, player_rank[i].games_played)));

                System.out.print(win_ratio);

                if (win_ratio < 10) {
                    System.out.print("%   | ");
                } else if (win_ratio < 100) {
                    System.out.print("%  | ");
                } else {
                    System.out.print("% | ");
                }

                if (player_rank[i].games_played < 10) {
                    System.out.print("0" + player_rank[i].games_played + " games | ");
                } else {
                    System.out.print(player_rank[i].games_played + " games | ");
                }

                System.out.println(player_rank[i].given_name + " " + player_rank[i].family_name);
            }
        } else {
            for (int i = 1; i < this.len; i++) {
                NimPlayer key = player_rank[i];
                int j = i - 1;

                while (j >= 0 && ((((double) player_rank[j].games_won / Math.max(1, player_rank[j].games_played)) > ((double) key.games_won / Math.max(1, key.games_played))) || ((((double) player_rank[j].games_won / Math.max(1, player_rank[j].games_played)) == ((double) key.games_won / Math.max(1, key.games_played))) && (player_rank[j].username.compareTo(key.username) > 0)))) {
                    player_rank[j + 1] = player_rank[j];
                    j--;
                }

                player_rank[j + 1] = key;
            }

            //display rankings in ascending order (maximum 10 players)
            for (int i = 0; i < Math.min(10, this.len); i++) {
                //calculate win ratio for each player
                long win_ratio = Math.round(100.0 * ((double) player_rank[i].games_won / Math.max(1, player_rank[i].games_played)));

                System.out.print(win_ratio);

                if (win_ratio < 10) {
                    System.out.print("%   | ");
                } else if (win_ratio < 100) {
                    System.out.print("%  | ");
                } else {
                    System.out.print("% | ");
                }

                if (player_rank[i].games_played < 10) {
                    System.out.print("0" + player_rank[i].games_played + " games | ");
                } else {
                    System.out.print(player_rank[i].games_played + " games | ");
                }

                System.out.println(player_rank[i].given_name + " " + player_rank[i].family_name);
            }
        }
    }

    //Method to start the game between two players
    public void startgame(Scanner sc, int initialstones, int upperbound, String u1, String u2) {
        //Creation of two instances of NimPlayer object
        NimPlayer player1 = this.stats[0];
        NimPlayer player2 = this.stats[0];
        boolean flag1 = false;
        boolean flag2 = false;

        //search for both players in NimPlayer array
        for (int i = 0; i < this.len; i++) {
            if (this.stats[i].username.equals(u1)) {
                player1 = this.stats[i];
                flag1 = true;
            } else if (this.stats[i].username.equals(u2)) {
                player2 = this.stats[i];
                flag2 = true;
            }
        }

        //do nothing if either player does not exist
        if (!flag1 || !flag2) {
            System.out.println("One of the players does not exist.");

            return;
        }

        System.out.println("\nInitial stone count: " + initialstones);
        System.out.println("Maximum stone removal: " + upperbound);
        System.out.println("Player 1: " + player1.given_name + " " + player1.family_name);
        System.out.println("Player 2: " + player2.given_name + " " + player2.family_name);

        //increment games played for both players by 1
        player1.games_played++;
        player2.games_played++;

        NimPlayer curr_player = player1;        //A new object of NimPlayer class to keep track of current player
        int stonesremoved;                      //variable to keep count of number of stones to be removed

        while (initialstones > 0) {
            try {
                curr_player.printStone(initialstones);     //Method to print number of stones

                /*Calls removestones function to get number of stones to remove for the current player,
                removes the stones and updates the Number of stones left*/
                System.out.println("\n" + curr_player.given_name + "\'s turn - remove how many?");

                if (curr_player.ai) {
                    //Player is AI
                    //curr_player will call the removestone() function of NimAIPlayer class instead of NimPlayer class due to method overloading
                    stonesremoved = ((NimAIPlayer) curr_player).removeStone(initialstones, upperbound);
                } else {
                    //Player is human
                    String sr = ((NimHumanPlayer) curr_player).removeStone(sc);
                    boolean flag3 = false;

                    for (char c : sr.toCharArray()) {
                        if (!Character.isDigit(c)) {
                            flag3 = true;
                            break;
                        }
                    }

                    //error handling for non-integer move
                    if (flag3) {
                        throw new InvalidMoveException(initialstones, upperbound);
                    }

                    stonesremoved = Integer.parseInt(sr);   //remove the stones specified

                    if (stonesremoved < 1 || stonesremoved > Math.min(initialstones, upperbound)) {
                        //ask for input again if user tries to remove less than 1 and more than specified upper bound number of stones
                        throw new InvalidMoveException(initialstones, upperbound);
                    }
                }

                //decrement stone count by number of stones removed
                initialstones -= stonesremoved;

                //reset current player to other player
                if (curr_player == player1) {
                    curr_player = player2;
                } else {
                    curr_player = player1;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        }

        System.out.println("\nGame Over");
        System.out.println(curr_player.given_name + " " + curr_player.family_name + " wins!");

        //increment games won for current player by 1
        curr_player.games_won++;
    }

    //Method to display the players details, number of games won and lost and perform error handling
    public void displayplayer(String u, boolean all) {
        if (all) {
            //display all players
            NimPlayer[] player_rank = this.stats.clone();   //create a copy of NimPlayer array

            //perform insertion sort on new NimPlayer array by ascending order of username
            for (int i = 1; i < this.len; i++) {
                NimPlayer key = player_rank[i];
                int j = i - 1;

                while (j >= 0 && (player_rank[j].username.compareTo(key.username) > 0)) {
                    player_rank[j + 1] = player_rank[j];
                    j--;
                }

                player_rank[j + 1] = key;
            }

            //print all users' details and stats
            for (int i = 0; i < this.len; i++) {
                System.out.println(player_rank[i].username + "," + player_rank[i].given_name + "," + player_rank[i].family_name + "," + player_rank[i].games_played + " games," + player_rank[i].games_won + " wins");
            }
        } else {
            //search for specified user
            for (int i = 0; i < this.len; i++) {
                //user found in NimPlayer array and his details and stats are printed
                if (this.stats[i].username.equals(u)) {
                    System.out.println(this.stats[i].username + "," + this.stats[i].given_name + "," + this.stats[i].family_name + "," + this.stats[i].games_played + " games," + this.stats[i].games_won + " wins");

                    return;
                }
            }

            //otherwise, user does not exist
            System.out.println("The player does not exist.");
        }
    }

    //Method to save current user data to file
    public void save() throws IOException {
        File fp = new File("players.dat");
        FileOutputStream fos = new FileOutputStream(fp);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < this.len; i++) {
            //write current user's data to line in file
            bw.write(this.stats[i].username + "," + this.stats[i].given_name + "," + this.stats[i].family_name + "," + this.stats[i].games_played + "," + this.stats[i].games_won + "," + this.stats[i].ai);

            if (i < this.len - 1) {
                bw.newLine();
            }
        }

        bw.close();
    }
}
