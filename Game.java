import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        int turn = 0;
        Board myBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);
        while(!myBoard.isGameOver()) {
            System.out.println(myBoard.toString());
            String s;
            if(turn == 0) {
                System.out.println("It is currently white's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col]");
                Scanner scanner = new Scanner(System.in);
                s = scanner.nextLine();
                turn++;
            }
            else {
                System.out.println("It is currently black's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col]");
                Scanner scanner = new Scanner(System.in);
                s = scanner.nextLine();
                turn--;
            }
            String[] Input = new String[4];
            Input = s.split(" ");
            myBoard.movePiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1]), Integer.parseInt(Input[2]), Integer.parseInt(Input[3]));
        }
        if(turn == 1) { // Game ended after white's turn
            System.out.println("White had won the game!");
        }
        else { // Game ended after black's turn
            System.out.println("Black has won the game");
        }
    }
}
