/** Simple variant of game Nim
*
* @author Charu
*
*/
import java.lang.*;

public class InvalidMoveException extends Exception {
    public InvalidMoveException(int i, int u) {
        super("\nInvalid move. You must remove between 1 and " + Math.min(i, u) + " stones.");
    }
}
