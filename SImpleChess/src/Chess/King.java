package Chess;

class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        // King moves one square in any direction
        return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1;
    }
    
    @Override
    public String getSymbol() {
        return isWhite ? "K" : "k";
    }
}
