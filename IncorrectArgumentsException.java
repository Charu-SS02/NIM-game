/** Simple variant of game Nim
*
* @author Charu
*
*/

import java.lang.*;

public class IncorrectArgumentsException extends Exception {
    public IncorrectArgumentsException() {
        super("Incorrect number of arguments supplied to command.");
    }
}
