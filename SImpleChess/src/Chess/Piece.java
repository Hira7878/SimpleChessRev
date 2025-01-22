package Chess;

abstract class Piece {
    protected boolean isWhite;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board);

    public abstract String getSymbol();
}
