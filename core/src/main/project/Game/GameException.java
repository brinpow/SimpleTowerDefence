package project.Game;

public class GameException extends RuntimeException{
    /* Exception indicating illegal state of game or one of its components
     * (possible bug or implementation mistake)
     */
    public GameException(String message) {
        super(message);
    }
}