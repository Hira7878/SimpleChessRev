package Chess;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Chess.Piece;

public class ChessGame {
    private static List<String> moveHistory = new ArrayList<>();

    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Scanner scanner = new Scanner(System.in);
        boolean isWhiteTurn = true;

        while (true) {
            board.printBoard();
            System.out.println((isWhiteTurn ? "White" : "Black") + "'s turn. Enter your move (e.g., e2 e4) or 'undo' to revert last move:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("undo")) {
                if (!moveHistory.isEmpty()) {
                    String lastMove = moveHistory.remove(moveHistory.size() - 1);
                    // Logic to revert the last move based on the recorded move
                    // This would require additional tracking of piece positions
                    System.out.println("Last move undone: " + lastMove);
                } else {
                    System.out.println("No moves to undo.");
                }
                continue;
            }

            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid input. Please enter a valid move.");
                continue;
            }

            // Convert input to board coordinates
            int startX = 8 - Character.getNumericValue(parts[0].charAt(1));
            int startY = parts[0].charAt(0) - 'a';
            int endX = 8 - Character.getNumericValue(parts[1].charAt(1));
            int endY = parts[1].charAt(0) - 'a';

            // Move the piece
            Piece piece = board.getPiece(startX, startY);
            if (piece != null && piece.isWhite == isWhiteTurn) {
                board.movePiece(startX, startY, endX, endY);
                moveHistory.add(parts[0] + " to " + parts[1]); // Record the move
                if (board.isCheckmate(!isWhiteTurn)) {
                    board.printBoard();
                    System.out.println((isWhiteTurn ? "White" : "Black") + " wins by checkmate!");
                    break;
                } else if (board.isStalemate(!isWhiteTurn)) {
                    board.printBoard();
                    System.out.println("It's a stalemate!");
                    break;
                }
                isWhiteTurn = !isWhiteTurn; // Switch turns
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();
    }
}