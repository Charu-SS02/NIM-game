/** Simple variant of game Nim
*
* @author Charu
*
*/

import java.lang.*;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String cmd) {
        super("\'" + cmd + "\' is not a valid command.");
    }
}
