// Written by Andrew Nielson, niels880 and Will Borgerding, borge369
import java.util.Scanner;

public class Game {
    public static Board myBoard;
    public static void main(String[] args) {
        int turn = 0; // starts with white
        myBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);
        while(!myBoard.isGameOver()) {
            System.out.println(myBoard.toString());
            String s;
            if(turn == 0) { // white's turn
                System.out.println("It is currently white's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");
                Scanner scanner = new Scanner(System.in); // Ask input for move
                s = scanner.nextLine();
                turn++; // set turn to 1 (black)
            }
            else { // black's turn
                System.out.println("It is currently black's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");
                Scanner scanner = new Scanner(System.in); // Ask input for move
                s = scanner.nextLine();
                turn--; // set turn to 0 (white)
            }
            String[] Input = new String[4]; // String array reserved for move input
            Input = s.split(" ");
            boolean yourPiece = true; // assume piece is your piece, checks following
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
                // enters loop if starting piece is null, if the move is not legal, or if the piece is not your piece
                System.out.println("Illegal move, try again.");
                Scanner scanner = new Scanner(System.in); // ask for input again
                s = scanner.nextLine();
                Input = new String[4];
                Input = s.split(" ");
                 // check if piece is not yours
                if(myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])) == null) { // null piece
                    yourPiece = false;
                }
                else if(myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])).getIsBlack() && turn == 1){ // white moving black's piece
                    yourPiece = false;
                }
                else if(!myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])).getIsBlack() && turn == 0){ // black moving white's piece
                    yourPiece = false;
                }
                else { // if passes check then piece is yours
                    yourPiece = true;
                }
            }
            String pieceString = myBoard.getPiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1])).toString(); // String representation of piece
            myBoard.movePiece(Integer.parseInt(Input[0]), Integer.parseInt(Input[1]), Integer.parseInt(Input[2]), Integer.parseInt(Input[3]));
            if(Integer.parseInt(Input[2]) == 0 && pieceString.equals("\u2659")) { // If the piece is on row 0 and is a white pawn
                myBoard.getPiece(Integer.parseInt(Input[2]), Integer.parseInt(Input[3])).promotePawn(Integer.parseInt(Input[2]), false); // promote pawn
            }
            else if(Integer.parseInt(Input[2]) == 7 && pieceString.equals("\u265f")) { // If the piece is on row 7 and is a black pawn
                myBoard.getPiece(Integer.parseInt(Input[2]), Integer.parseInt(Input[3])).promotePawn(Integer.parseInt(Input[2]), true); // promote pawn
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