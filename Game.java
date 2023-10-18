import java.util.Scanner;

public class Game {
    public static Board myBoard;
    public static void main(String[] args) {
        int turn = 0;
        myBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);
        while(!myBoard.isGameOver()) {
            System.out.println(myBoard.toString());
            String s;
            if(turn == 0) {
                System.out.println("It is currently white's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");
                Scanner scanner = new Scanner(System.in);
                s = scanner.nextLine();
                turn++;
            }
            else {
                System.out.println("It is currently black's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");
                Scanner scanner = new Scanner(System.in);
                s = scanner.nextLine();
                turn--;
            }
            String[] Input = new String[4];
            Input = s.split(" ");
            boolean yourPiece = true;
            if(myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])) == null) { // null piece
                yourPiece = false;
            }
            else if(myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])).getIsBlack() && turn == 1){ // white moving black's piece
                yourPiece = false;
            }
            else if(!myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])).getIsBlack() && turn == 0){ // black moving white's piece
                yourPiece = false;
            }
            while(myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])) == null || !myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])).isMoveLegal(myBoard, Integer.parseInt(Input[2]), Integer.parseInt(Input[3])) || !yourPiece) {
                System.out.println("Illegal move, try again.");
                Scanner scanner = new Scanner(System.in);
                s = scanner.nextLine();
                Input = new String[4];
                Input = s.split(" ");
                if(myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])) == null) { // null piece
                    yourPiece = false;
                }
                else if(myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])).getIsBlack() && turn == 1){ // white moving black's piece
                    yourPiece = false;
                }
                else if(!myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])).getIsBlack() && turn == 0){ // black moving white's piece
                    yourPiece = false;
                }
                else {
                    yourPiece = true;
                }
            }
            String pieceString = myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])).toString(); // String representation of piece
            myBoard.movePiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1]), Integer.parseInt(Input[2]), Integer.parseInt(Input[3]));
            if(Integer.parseInt(Input[2]) == 0 && pieceString.equals("\u2659")) { // White pawn
                myBoard.getPiece(Integer.parseInt(Input[2]), Integer.parseInt(Input[3])).promotePawn(Integer.parseInt(Input[2]), false);
            }
            else if(Integer.parseInt(Input[2]) == 7 && pieceString.equals("\u265f")) { // Black pawn
                myBoard.getPiece(Integer.parseInt(Input[2]), Integer.parseInt(Input[3])).promotePawn(Integer.parseInt(Input[2]), true);
            }
        }
        if(turn == 1) { // Game ended after white's turn
            System.out.println("White has won the game!");
        }
        else { // Game ended after black's turn
            System.out.println("Black has won the game!");
        }
    }
}