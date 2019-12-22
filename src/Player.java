import java.io.*;

public class Player {
    private final char mark;
    private final String name;
    private final BufferedReader in;

    //calls another constructor
    public Player(char mark) {
        this(mark, new BufferedReader(
                new InputStreamReader(System.in)
        ));
    }

    //This constructor is not intended to be called directly
    protected Player(char initMark, BufferedReader initIn) {
        mark = initMark;
        in = initIn;
        name = mark+"";
    }

    //Test Player
    public Player(char mark, String moves) {
        this(mark, new BufferedReader(
                new StringReader(moves)
        ));
    }

    //nobody
    public Player() {
        this(' ');
    }


    public boolean isNobody() {
        return this.mark==' ';
    }

    public char getMark(){
        return this.mark;
    }

    public String getName(){
        return this.name;
    }

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
