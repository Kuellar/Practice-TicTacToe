import java.io.IOException;

/**
 * This class represents the game.
 * <p>
 *     This class implement all basic stuff.
 *     Manage rules.
 *
 * @author Ignacio Cuellar Tapia
 * @since 1.0
 */
public class TicTacToe {
    private char[][] gameState;
    private Player[] player;
    private Player winner;
    private int turn;
    private int squaresLeft;

    /**
     * Creates a new Game.
     */
    public TicTacToe(){
        gameState = new char[3][3];
        for (char col='a'; col <='c'; col++)
            for (char row='1'; row<='3'; row++)
                this.set(col,row,' ');
        squaresLeft = 9;
        turn = 0;
        winner = new Player();
    }

    /**
     * Creates a new Game.
     *
     * @param playerX
     *      The first player
     * @param playerO
     *      The second player
     */
    public TicTacToe(Player playerX, Player playerO) {
        player = new Player[2];
        player[0] = playerX;
        player[1] = playerO;

        gameState = new char[3][3];
        for (char col='a'; col <='c'; col++)
            for (char row='1'; row<='3'; row++)
                this.set(col,row,' ');
        squaresLeft = 9;
        turn = 0;
        winner = new Player();
    }

    /**
     * Gets the String.
     *
     * @return the representation of the game state.
     */
    @Override
    public String toString(){
        StringBuilder rep = new StringBuilder();
        for (char row='3'; row >= '1'; row--) {
            rep.append(row);
            rep.append(" ");

            for (char col='a'; col <='c'; col++) {
                rep.append(this.get(col,row));
                if(col!='c') rep.append(" | ");
            }
            rep.append("\n");

            if(row!='1')rep.append(" ---+---+---\n");

        }

        rep.append("   a   b   c\n");
        return(rep.toString());
    }

    /**
     * Gets the mark in some cell.
     *
     * @param x
     *     Column location.
     * @param y
     *     Row location
     * @return the mark.
     */
    public char get(char x, char y) {
        return gameState[colToInt(x)][rowToInt(y)];
    }

    /**
     * Puts a new mark in one cell.
     *
     * @param x
     *     Column location.
     * @param y
     *     Row location
     * @param z
     *     The new mark
     */
    public void set(char x, char y, char z) {
        this.gameState[colToInt(x)][rowToInt(y)] = z;
    }

    /**
     * Translate from coordinate (column) to int
     *
     * @param x
     *     Column location.
     * @return the translate.
     */
    public int colToInt(char x){
        int resp = 0;
        for (char col='a'; col <='c'; col++) {
            if(col==x)break;
            resp++;
        }
        return resp;
    }

    /**
     * Translate from coordinate (row) to int
     *
     * @param y
     *     Row location.
     * @return the translate.
     */
    public int rowToInt(char y){
        int resp = 0;
        for (char row='1'; row<='3'; row++) {
            if(row==y)break;
            resp++;
        }
        return resp;
    }

    /**
     * Check if the input is in range.
     * @param x
     *     Column location.
     * @param y
     *     Row location.
     * @return true if is in range.
     */
    public boolean inRange(char x, char y) {
        return x <= 'c' && x >= 'a' && y <= '3' && y >= '1';
    }

    /**
     * Check if the game is not over.
     * @return true if is not over.
     */
    public boolean notOver() {
        return this.getWinner().isNobody()
                && this.getSquaresLeft() > 0;
    }

    /**
     * Get the winner.
     * @return the winner.
     */
    public Player getWinner(){
        return this.winner;
    }

    /**
     * Get the squares left.
     * @return the number of squares left.
     */
    public int getSquaresLeft(){
        return this.squaresLeft;
    }

    /**
     * Change the turn owner.
     */
    private void swapTurn() {
        turn = (turn == 0) ? 1 : 0;

    }

    /**
     * Check if there's a winner.
     */
    private void checkWinner() {
        char player;
        for (char row='3'; row>='1'; row--) {
            player = this.get('a',row);
            if (player == this.get('b',row)
                    && player == this.get('c',row)) {
                this.setWinner(player);
                return;
            }
        }
        for (char col='a'; col <='c'; col++) {
            player = this.get(col,'1');
            if (player == this.get(col,'2')
                    && player == this.get(col,'3')) {
                this.setWinner(player);
                return;
            }
        }
        player = this.get('b','2');
        if (player == this.get('a','1')
                && player == this.get('c','3')) {
            this.setWinner(player);
            return;
        }
        if (player == this.get('a','3')
                && player == this.get('c','1')) {
            this.setWinner(player);
            return;
        }
    }

    /**
     * Set new winner.
     * @param player
     *      The player winner.
     */
    private void setWinner(char player) {
        for (Player n: this.player) {
            if(n.getMark()==player)this.winner=n;
        }
    }

    /**
     * update.
     */
    public void update() throws IOException {
        player[turn].move(this);
    }

    /**
     * puts new mark
     * @param col
     *      column position.
     * @param mark
     *      the mark.
     * @param row
     *      row position
     */
    public void move(char col, char row, char mark) {
        System.out.println(mark + " at " + col + row);
        this.set(col, row, mark);
        this.squaresLeft--;
        this.swapTurn();
        this.checkWinner();
    }

    /**
     * Get the name of the turn owner
     * @return turn owner.
     */
    public String getTurnOwner(){
        return this.player[turn].getName();
    }

    /**
     * Check if the location is valid.
     * @return true if is valid
     */
    public boolean isValid(char col, char row) {
        return this.inRange(col,row) && this.get(col,row)==' ';
    }
}
