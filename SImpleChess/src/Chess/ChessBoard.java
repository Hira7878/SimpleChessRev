package Chess;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        board = new Piece[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize rooks
        board[0][0] = new Rook(true);
        board[0][7] = new Rook(true);
        board[7][0] = new Rook(false);
        board[7][7] = new Rook(false);
        
        // Initialize knights
        board[0][1] = new Knight(true);
        board[0][6] = new Knight(true);
        board[7][1] = new Knight(false);
        board[7][6] = new Knight(false);
        
        // Initialize bishops
        board[0][2] = new Bishop(true);
        board[0][5] = new Bishop(true);
        board[7][2] = new Bishop(false);
        board[7][5] = new Bishop(false);
        
        // Initialize queens
        board[0][3] = new Queen(true);
        board[7][3] = new Queen(false);
        
        // Initialize kings
        board[0][4] = new King(true);
        board[7][4] = new King(false);
        
        // Initialize pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(true);
            board[6][i] = new Pawn(false);
        }
    }    
    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        board[x][y] = piece;
    }

    public void movePiece(int startX, int startY, int endX, int endY) {
        Piece piece = getPiece(startX, startY);
        if (piece != null && piece.isValidMove(startX, startY, endX, endY, this)) {
            setPiece(endX, endY, piece);
            setPiece(startX, startY, null);
        } else {
            System.out.println("Invalid move");
        }
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null) {
                    System.out.print(" - ");
                } else {
                    System.out.print(piece.getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean isInCheck(boolean isWhite) {
        // Find the king's position
        int kingX = -1, kingY = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece instanceof King && piece.isWhite == isWhite) {
                    kingX = i;
                    kingY = j;
                    break;
                }
            }
        }
    
        // Check if any opposing piece can attack the king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.isWhite != isWhite) {
                    if (piece.isValidMove(i, j, kingX, kingY, this)) {
                        return true; // King is in check
                    }
                }
            }
        }
        return false; // King is safe
    }

    public boolean isCheckmate(boolean isWhite) {
        // If the player is in check, check for valid moves
        if (isInCheck(isWhite)) {
            // Check all pieces for possible moves
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Piece piece = board[i][j];
                    if (piece != null && piece.isWhite == isWhite) {
                        for (int x = 0; x < 8; x++) {
                            for (int y = 0; y < 8; y++) {
                                if (piece.isValidMove(i, j, x, y, this)) {
                                    // Temporarily move the piece
                                    Piece temp = board[x][y];
                                    board[x][y] = piece;
                                    board[i][j] = null;
    
                                    // Check if the king is still in check
                                    if (!isInCheck(isWhite)) {
                                        // Undo the move
                                        board[i][j] = piece;
                                        board[x][y] = temp;
                                        return false; // Valid move found, not checkmate
                                    }
    
                                    // Undo the move
                                    board[i][j] = piece;
                                    board[x][y] = temp;
                                }
                            }
                        }
                    }
                }
            }
            return true; // No valid moves found, it's checkmate
        }
        return false; // Not in check, so not checkmate
    }

    public boolean isStalemate(boolean isWhite) {
    // If the player is in check, it cannot be stalemate
    if (isInCheck(isWhite)) {
        return false;
    }

    // Check all pieces for possible moves
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            Piece piece = board[i][j];
            if (piece != null && piece.isWhite == isWhite) {
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        if (piece.isValidMove(i, j, x, y, this)) {
                            return false; // Valid move found, not stalemate
                        }
                    }
                }
            }
        }
    }
    return true; // No valid moves found and not in check, it's stalemate
}
}
