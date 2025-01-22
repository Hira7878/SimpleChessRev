package Chess;

class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        int direction = isWhite ? 1 : -1;
        if (startY == endY) {
            // Move forward
            return endX - startX == direction;
        } else if (Math.abs(startY - endY) == 1) {
            // Capture
            return endX - startX == direction;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return isWhite ? "P" : "p";
    }
}

