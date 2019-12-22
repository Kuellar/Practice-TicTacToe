import java.io.IOException;

/**
 * This class represents the GameDriver.
 * <p>
 *     The GameDriver manege turns. Here you start the game.
 *
 * @author Ignacio Cuellar Tapia
 * @since 1.0
 */
public class GameDriver {

    public static void main(String args[]) throws IOException {
        Player X = new Player('X');
        Player O = new Player('O');
        TicTacToe game = new TicTacToe(X, O);
        playGame(game);
    }

    /**
     * Start the game.
     *
     * @param game
     *      The game to be played.
     */
    public static void playGame(TicTacToe game) throws IOException {
        while(game.notOver()){
            System.out.print(game.toString());

            System.out.print("Turno de "+game.getTurnOwner()+ " = ");
            game.update();

        }
        System.out.print(game.toString());
        if(game.getWinner().isNobody())
            System.out.println("Empate");
        else
            System.out.println("El ganador es " + game.getWinner().getName());

    }


}
