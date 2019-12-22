import java.io.IOException;

public class TicTacToe {
    private char[][] gameState;
    private Player[] player;
    private Player winner;
    private int turn;
    private int squaresLeft;

    public TicTacToe(){
        gameState = new char[3][3];
        for (char col='a'; col <='c'; col++)
            for (char row='1'; row<='3'; row++)
                this.set(col,row,' ');
        squaresLeft = 9;
        turn = 0;
        winner = new Player();
    }

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

    public char get(char x, char y) {
        return gameState[colToInt(x)][rowToInt(y)];
    }

    public void set(char x, char y, char z) {
        this.gameState[colToInt(x)][rowToInt(y)] = z;
    }

    public int colToInt(char x){
        int resp = 0;
        for (char col='a'; col <='c'; col++) {
            if(col==x)break;
            resp++;
        }
        return resp;
    }

    public int rowToInt(char y){
        int resp = 0;
        for (char row='1'; row<='3'; row++) {
            if(row==y)break;
            resp++;
        }
        return resp;
    }

    public boolean inRange(char x, char y) {
        return x <= 'c' && x >= 'a' && y <= '3' && y >= '1';
    }

    public boolean notOver() {
        return this.getWinner().isNobody()
                && this.getSquaresLeft() > 0;
    }

    public Player getWinner(){
        return this.winner;
    }

    public int getSquaresLeft(){
        return this.squaresLeft;
    }

    private void swapTurn() {
        turn = (turn == 0) ? 1 : 0;

    }

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

    private void setWinner(char player) {
        for (Player n: this.player) {
            if(n.getMark()==player)this.winner=n;
        }
    }

    public void update() throws IOException {
        player[turn].move(this);
    }

    public void move(char col, char row, char mark) {
        System.out.println(mark + " at " + col + row);
        this.set(col, row, mark);
        this.squaresLeft--;
        this.swapTurn();
        this.checkWinner();
    }

    public String getTurnOwner(){
        return this.player[turn].getName();
    }

    public boolean isValid(char col, char row) {
        return this.inRange(col,row) && this.get(col,row)==' ';
    }
}
