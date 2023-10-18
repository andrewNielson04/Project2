// Written by Andrew Nielson, niels880 and Will Borgerding, borge369
public class Knight {
    private int row, col;
    private boolean isBlack;
    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if(board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            int horizontalDistance = Math.abs(endCol - this.col);
            int verticalDistance = Math.abs(endRow - this.row);
            if (horizontalDistance + verticalDistance == 3 && horizontalDistance != 0 && verticalDistance != 0) {
                return true;
            }
        }
        return false;
    }
}
