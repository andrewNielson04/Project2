// Written by Andrew Nielson, niels880 and Will Borgerding, borge369
public class Board {

    // Instance variables
    private Piece[][] board;
    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;  // set piece in board array
        piece.setPosition(row, col); // change piece object's row and column
    }

    // Game functionality methods
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if(this.getPiece(startRow, startCol) != null) { // must be piece in source
            if (board[startRow][startCol].isMoveLegal(this, endRow, endCol)) { // move must pass respective piece's isMoveLegal method
                this.setPiece(endRow, endCol, board[startRow][startCol]); // insert source piece into destination
                board[startRow][startCol] = null; // set source location to null
                return true;
            }
        }
        return false;
    }

    public boolean isGameOver() {
        int kingCount = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(this.getPiece(i, j) != null) { // if there is a piece in the spot
                    String pieceString = this.getPiece(i, j).toString(); // String version of piece
                    if(pieceString.equals("\u265a") || pieceString.equals("\u2654")) { // if piece is a king, regardless of color
                        kingCount++;
                    }
                    if(kingCount == 2) { // If there are two kings in the game then the game is ongoing
                        return false;
                    }
                }
            }
        }
        return true; // game is complete if there are <2 kings
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    public void clear() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                board[i][j] = null; // sets all values to null
            }
        }
    }

    // Movement helper functions
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        // piece in bounds of board
        if(startRow <= 7 && startRow >= 0 && endRow <= 7 && endRow >= 0 && endCol <= 7 && endCol >= 0 && startCol <= 7 && startCol >= 0) {
            if(this.getPiece(startRow, startCol) != null) { // there is a piece in source
                if(this.getPiece(startRow, startCol).getIsBlack() == isBlack) { // the source color is the color that is moving
                    // destination is null (empty) or piece of opposite color
                    if(this.getPiece(endRow, endCol) == null || this.getPiece(endRow, endCol).getIsBlack() != isBlack) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int horizontalChange = Math.abs(startCol - endCol);
        int verticalChange = Math.abs(startRow - endRow);
        if ((horizontalChange <= 1 && verticalChange <= 1)) { // Allows for horizontal, vertical, and diagonal moves of 1 distance
            return true;
        }
        return false;
    }

    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow) { // same row
            if (endCol - startCol <= -1) { // left
                for (int i = startCol - 1; i > endCol; i--) {
                    if (this.getPiece(startRow, i) != null) { // if there's a piece in the way
                        return false;
                    }
                }
            } else if (endCol - startCol >= 1) { // right
                for (int i = startCol + 1; i < endCol; i++) {
                    if (this.getPiece(startRow, i) != null) { // if there's a piece in the way
                        return false;
                    }
                }
            }
            return true; // if it's not moving at all
        }
        return false;
    }

    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol == endCol) { // same column
            if (endRow - startRow <= -1) { // up
                for (int i = startRow - 1; i > endRow; i--) {
                    if (this.getPiece(i, startCol) != null) { // if there's a piece in the way
                        return false;
                    }
                }
            }
            else if (endRow - startRow >= 1) { // down
                for (int i = startRow + 1; i < endRow; i++) {
                    if (this.getPiece(i, startCol) != null) { // if there's a piece in the way
                        return false;
                    }
                }
            }
            return true; // if it's not moving at all
        }
        return false;
    }

    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol)) { // vertical and horizontal distance are equal
            if (endCol - startCol <= -1) { // Left
                if (endRow - startRow <= -1) { // Up
                    for (int i = startCol - 1; i > endCol; i--) {
                        if (this.getPiece(startRow - startCol + i, i) != null) { // piece in way
                            return false;
                        }
                    }
                } else { // Down
                    for (int i = startCol - 1; i > endCol; i--) {
                        if (this.getPiece(startRow + startCol - i, i) != null) { // piece in way
                            return false;
                        }
                    }
                }
            } else if (endCol - startCol >= 1) { // Right
                if (endRow - startRow <= -1) { // Up
                    for (int i = startCol + 1; i < endCol; i++) {
                        if (this.getPiece(startRow + startCol - i, i) != null) { // piece in way
                            return false;
                        }
                    }
                } else { // Down
                    for (int i = startCol + 1; i < endCol; i++) {
                        if (this.getPiece(i + startRow - startCol, i) != null) { // piece in way
                            return false;
                        }
                    }
                }
            }
            return true; // no movement
        }
        return false;
    }
}