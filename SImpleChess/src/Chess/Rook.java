package Chess;

class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        // Rook moves in straight lines
        return startX == endX || startY == endY;
    }

    @Override
    public String getSymbol() {
        return isWhite ? "R" : "r";
    }
}
