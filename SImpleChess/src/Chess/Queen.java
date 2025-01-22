package Chess;

class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
    // Queen moves like both a rook and a bishop
    return startX == endX || startY == endY || Math.abs(startX - endX) == Math.abs(startY - endY);
    }
    
    @Override
    public String getSymbol() {
        return isWhite ? "Q" : "q";
    }
}
    