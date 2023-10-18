// Written by Andrew Nielson, niels880 and Will Borgerding, borge369
import java.util.Scanner;

public class Piece {
    // Create Instance Variables
    private char character;
    private int row, col;
    private boolean isBlack;
    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack() { return this.isBlack; }

    /**
     * Handle promotion of a pawn.
     * @param row Current row of the pawn
     * @param isBlack Color of the pawn
     */
    public void promotePawn(int row, boolean isBlack) {
        System.out.println("Pawn promotion! Enter Unicode character for desired piece:");
        System.out.println("    Queen: q");
        System.out.println("    Rook: r");
        System.out.println("    Bishop: b");
        System.out.println("    Knight: k");
        System.out.println("(Defaults to queen if invalid input)"); // prompt for pawn promotion
        Scanner scanner = new Scanner(System.in); // asks for input
        String s = scanner.nextLine();
        if(s.equals("r")) { // user chooses a rook
            if(isBlack) { this.character = '\u265c'; } // set to black rook if pawn is black
            else { this.character = '\u2656'; } // set to white rook if pawn is black
        }
        else if(s.equals("b")) { // user chooses a bishop
            if(isBlack) { this.character = '\u265d'; } // set to black bishop if pawn is black
            else { this.character = '\u2657'; } // set to white bishop if pawn is white
        }
        else if(s.equals("k")) { // user chooses knight
            if(isBlack) { this.character = '\u265e'; } // set to black bishop if pawn is  back
            else { this.character = '\u2658'; } // set to white bishop if pawn is white
        }
        else { // user chooses queen or anything else
            if(isBlack) { this.character = '\u265b'; } // set to black queen if pawn is black
            else{ this.character = '\u2655'; } // set to white queen if pawn is white
        }
    }


    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return "" + character;
    }


}
