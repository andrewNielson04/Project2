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
        piece.setPosition(row, col);
    }

    // Game functionality methods
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if(board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
            this.setPiece(endRow, endCol, board[startRow][startCol]);
            return true;
        }
        return false;
    }

    public boolean isGameOver() {
        int kingCount = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(this.getPiece(i, j) != null) {
                    String pieceString = this.getPiece(i, j).toString();
                    if(pieceString.equals("\u265a") || pieceString.equals("\u2654")) {
                        kingCount++;
                    }
                    if(kingCount == 2) {
                        return false;
                    }
                }
            }
        }
        return true;
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
                board[i][j] = null;
            }
        }
    }

    // Movement helper functions
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if(startRow <= 7 && startRow >= 0 && endRow <= 7 && endRow >= 0 && endCol <= 7 && endCol >= 0 && startCol <= 7 && startCol >= 0) {
            if(startRow - endRow == 0 && startCol - endCol == 0) { // Doesn't move
                return true;
            }
            if(this.getPiece(startRow, startCol) != null) { // redundant??
                if(this.getPiece(startRow, startCol).getIsBlack() == isBlack) {
                    if(this.getPiece(endRow, endCol) == null || this.getPiece(endRow, endCol).getIsBlack() != isBlack) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if(this.getPiece(startRow, startCol) != null) {
            if (verifySourceAndDestination(startRow, startCol, endRow, endCol, this.getPiece(startRow, startCol).getIsBlack())) {
                int horizontalChange = Math.abs(startCol - endCol);
                int verticalChange = Math.abs(startRow - endRow);
                if ((horizontalChange <= 1 && verticalChange <= 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if(this.getPiece(startRow, startCol) != null) {
            if (verifySourceAndDestination(startRow, startCol, endRow, endCol, this.getPiece(startRow, startCol).getIsBlack())) {
                if (startRow == endRow) {
                    if (endCol - startCol <= -1) {
                        for (int i = startCol - 1; i > endCol; i--) {
                            if (this.getPiece(startRow, i) != null) {
                                return false;
                            }
                        }
                    }
                    else if (endCol - startCol >= 1) {
                        for (int i = startCol + 1; i < endCol; i++) {
                            if (this.getPiece(startRow, i) != null) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if(this.getPiece(startRow, startCol) != null) {
            if (verifySourceAndDestination(startRow, startCol, endRow, endCol, this.getPiece(startRow, startCol).getIsBlack())) {
                if (startCol == endCol) {
                    if (endRow - startRow <= -1) {
                        for (int i = startRow - 1; i > endRow; i--) {
                            if (this.getPiece(i, startCol) != null) {
                                return false;
                            }
                        }
                    }
                    else if (endRow - startRow >= 1) {
                        for (int i = startRow + 1; i < endRow; i++) {
                            if (this.getPiece(i, startCol) != null) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if(this.getPiece(startRow, startCol) != null) {
            if (verifySourceAndDestination(startRow, startCol, endRow, endCol, this.getPiece(startRow, startCol).getIsBlack())) {
                if (startRow - endRow == startCol - endCol) {
                    if (endCol - startCol <= -1) { // Up
                        if (endRow - startRow <= -1) { // Left
                            for (int i = startCol - 1; i > startCol; i--) {
                                if (this.getPiece(i + startRow - startCol, i) != null) {
                                    return false;
                                }
                            }
                        } else { // Right
                            for (int i = startCol + 1; i < endCol; i++) {
                                if (this.getPiece(startRow + startCol - i, i) != null) {
                                    return false;
                                }
                            }
                        }
                    } else if (endCol - startCol >= 1) { // Down
                        if (endRow - startRow <= -1) { // Left
                            for (int i = startCol + 1; i < endCol; i++) {
                                if (this.getPiece(i + startRow - startCol, i) != null) {
                                    return false;
                                }
                            }
                        } else { // Right
                            for (int i = startCol - 1; i > endCol; i--) {
                                if (this.getPiece(startRow + startCol - i, i) != null) {
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                }
            }
        }
         return false;
    }
}