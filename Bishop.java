// Written by Andrew Nielson, niels880 and Will Borgerding, borge369
public class Bishop {
    // Instance variables
    private int row, col;
    private boolean isBlack;
    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if(board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
                return true;
            }
        }
        return false;
    }
}
