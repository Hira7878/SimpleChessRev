package Chess;

class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        // Bishop moves diagonally
        return Math.abs(startX - endX) == Math.abs(startY - endY);
    }

    @Override
    public String getSymbol() {
        return isWhite ? "B" : "b";
    }
}
