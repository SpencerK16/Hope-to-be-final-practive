package chess;

import java.util.Collection;
import java.util.HashSet;

public class KnightMovesCalculator implements PieceMovesCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new HashSet<>();

        addKnightMoves(board, position, moves);

        return moves;
    }

    private void addKnightMoves(ChessBoard board, ChessPosition position, Collection<ChessMove> moves) {
        int[][] directions = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };

        for (int[] direction : directions) {
            int newRow = position.getRow() + direction[0];
            int newCol = position.getColumn() + direction[1];
            if (newRow >= 1 && newRow <= 8 && newCol >= 1 && newCol <= 8) {
                ChessPosition newPosition = new ChessPosition(newRow, newCol);
                addMovesIfValid(board, position, newPosition, moves);
            }

        }
    }

    private void addMovesIfValid(ChessBoard board, ChessPosition start, ChessPosition end, Collection<ChessMove> moves) {
        ChessPiece pieceAtEnd = board.getPiece(end);
        if (pieceAtEnd != null) {
            if (pieceAtEnd.getTeamColor() != board.getPiece(start).getTeamColor()){
                moves.add(new ChessMove(start, end, null));
            }
            return;
        }
        moves.add(new ChessMove(start, end, null));
    }
}
