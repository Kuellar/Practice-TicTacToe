import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TicTacToeTest {

    private TicTacToe game;

    @Before
    public void setUp() {
        game = new TicTacToe();
    }

    @Test
    public void testState() {
        assertTrue(game.get('a','1') == ' ');
        assertTrue(game.get('c','3') == ' ');
        game.set('c','3','X');
        assertTrue(game.get('c','3') == 'X');
        game.set('c','3',' ');
        assertTrue(game.get('c','3') == ' ');
        assertFalse(game.inRange('d','4'));
    }

    @Test public void testXWinDiagonal() throws IOException {
        checkGame("a1\nb2\nc3\n", "b1\nc1\n", "X", 4);
    }


    public void checkGame(String Xmoves, String Omoves, String winner, int squaresLeft) throws IOException {
        Player X = new Player('X', Xmoves);
        // a scripted player
        Player O = new Player('O', Omoves);
        TicTacToe game = new TicTacToe(X, O);
        GameDriver.playGame(game);
        assertTrue(game.getWinner().getName().equals(winner));
        assertTrue(game.getSquaresLeft() == squaresLeft);
    }

}
