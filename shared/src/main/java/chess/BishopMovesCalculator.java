package chess;

import java.util.Collection;
import java.util.HashSet;

public class BishopMovesCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new HashSet<>();

        addDiagonalMoves(board, position, moves);

        return moves;
    }

    private void addDiagonalMoves(ChessBoard board, ChessPosition position, Collection<ChessMove> moves) {
        int[] directions = {-1, 1};

        for (int rowDirections : directions){
            for (int colDirections : directions) {
                int row = position.getRow();
                int col = position.getColumn();
                while (true) {
                    row += rowDirections;
                    col += colDirections;
                    if (row < 1 || row > 8 || col < 1 || col > 8) break;
                    ChessPosition newPosition = new ChessPosition(row, col);
                    if (!addMovesIfValid(board, position, newPosition, moves)) break;

                }
            }
        }
    }

    private boolean addMovesIfValid(ChessBoard board, ChessPosition start, ChessPosition end, Collection<ChessMove> moves) {
        ChessPiece pieceAtEnd = board.getPiece(end);
        if (pieceAtEnd != null) {
            if(pieceAtEnd.getTeamColor() != board.getPiece(start).getTeamColor()) {
                moves.add(new ChessMove(start, end, null));
            }
            return false;
        }
        moves.add(new ChessMove(start, end, null));
        return true;
    }
}
