package assignment3.chessview.pieces;

import assignment3.chessview.*;

public class King extends PieceImpl implements Piece {
	public boolean hasMoved = false;

	public King(boolean isWhite) {
		super(isWhite);
	}	
	
	public boolean isValidMove(Position oldPosition, Position newPosition,
			Piece isTaken, Board board) {
		int diffCol = Math.max(oldPosition.column(), newPosition.column())
				- Math.min(oldPosition.column(), newPosition.column());
		int diffRow = Math.max(oldPosition.row(), newPosition.row())
				- Math.min(oldPosition.row(), newPosition.row());
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		
		return this.equals(p) //this is the piece to move
				//if newPos is empty OR if not empty, newPos should be equal to isTaken object and taken object should be opposite colour
				&& (t == isTaken || (isTaken != null && isTaken.equals(t) && (!t.isWhite() && p.isWhite() || t.isWhite() && !p.isWhite())))
				// can move one square vertically, horizontally or diagonally, 
				&& (diffCol == 1 || diffRow == 1) && diffCol <= 1 && diffRow <= 1;
	}
	
	public String toString() {
		if(isWhite) {
			return "K";
		} else {
			return "k";
		}
	}
}
