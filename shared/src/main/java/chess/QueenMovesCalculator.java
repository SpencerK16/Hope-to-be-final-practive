package chess;

import java.util.Collection;
import java.util.HashSet;

public class QueenMovesCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new HashSet<>();

        addVerticalAndHorizontalMoves(board, position, moves);
        addDiagonalMoves(board, position, moves);

        return moves;
    }

    private void addVerticalAndHorizontalMoves(ChessBoard board, ChessPosition position, Collection<ChessMove> moves) {
        int[] directions = {-1, 1};

        //Add Vertical Moves
        for (int direction : directions) {
            int row = position.getRow();
            while (true) {
                row += direction;
                if (row < 1 || row > 8) break;
                ChessPosition newPosition = new ChessPosition(row, position.getColumn());
                if (!addMovesIfValid(board, position, newPosition, moves)) break;
            }
        }
        //Add Horizontal Moves
        for (int direction : directions) {
            int col = position.getColumn();
            while (true) {
                col += direction;
                if (col < 1 || col > 8) break;
                ChessPosition newPosition = new ChessPosition(position.getRow(), col);
                if (!addMovesIfValid(board, position, newPosition, moves)) break;
            }
        }
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
            if (pieceAtEnd.getTeamColor() != board.getPiece(start).getTeamColor()){
                moves.add(new ChessMove(start, end, null));
            }
            return false;
        }
        moves.add(new ChessMove(start, end, null));
        return true;
    }
}
