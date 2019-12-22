import java.io.*;

/**
 * This class represents the player.
 * <p>
 *     The have a name and mark.
 *
 * @author Ignacio Cuellar Tapia
 * @since 1.0
 */
public class Player {
    private final char mark;
    private final String name;
    private final BufferedReader in;

    /**
     * Creates a new Player.
     * Calls another constructor
     *
     * @param mark
     *     the mark (normally X or O)
     */
    public Player(char mark) {
        this(mark, new BufferedReader(
                new InputStreamReader(System.in)
        ));
    }

    /**
     * Creates a new Player.
     * This constructor is not intended to be called directly.
     *
     * @param initMark
     *     The mark
     * @param initIn
     *     The inputs
     */
    protected Player(char initMark, BufferedReader initIn) {
        mark = initMark;
        in = initIn;
        name = mark+"";
    }

    /**
     * Creates a new Test Player.
     * This constructor is intended to be used only for test.
     *
     * @param mark
     *     The mark
     * @param moves
     *     The inputs
     */
    public Player(char mark, String moves) {
        this(mark, new BufferedReader(
                new StringReader(moves)
        ));
    }

    /**
     * Creates a new empty Player.
     */
    public Player() {
        this(' ');
    }

    /**
     * Check if the player is nobody.
     *
     * @return true if the player is nobody
     */
    public boolean isNobody() {
        return this.mark==' ';
    }

    /**
     * Gets the mark of the player.
     *
     * @return the mark.
     */
    public char getMark(){
        return this.mark;
    }

    /**
     * Gets the name of the player.
     *
     * @return the name.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Put new mark in the game. (input)
     *
     * @param ticTacToe
     *     the game.
     */
    public void move(TicTacToe ticTacToe) throws IOException {
        String phrase = in.readLine();
        char[] phraseChar = phrase.toCharArray();
        char col = phraseChar[0];
        char row = phraseChar[1];

        if(ticTacToe.isValid(col,row))
            ticTacToe.move(col,row,getMark());
        else{
            System.out.println("Posicion invalida");
            this.move(ticTacToe);
        }

    }
}
