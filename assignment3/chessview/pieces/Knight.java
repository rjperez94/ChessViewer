package assignment3.chessview.pieces;

import assignment3.chessview.*;

public class Knight extends PieceImpl implements Piece {
	public Knight(boolean isWhite) {
		super(isWhite);
	}
		
	public boolean isValidMove(Position oldPosition, Position newPosition,
			Piece isTaken, Board board) {
		if (isTaken == null) {
			//System.out.println("TAKEN NULL");  non-capture
			return checkMove (oldPosition, newPosition,	isTaken, board);
		} else {
			//System.out.println("TAKEN  not NULL");  capture
			return checkTake (oldPosition, newPosition,	isTaken, board);
		}
	}
	
	/**
	 * @param oldPosition - position of this piece before move
	 * @param newPosition - position of this piece after move
	 * @param isTaken - reference to piece being CAPTURED === null
	 * @param board - reference to this board
	 * @return true if this NON capture move is valid
	 * 
	 */
	private boolean checkMove (Position oldPosition, Position newPosition, Piece isTaken, Board board) {
		int diffCol = Math.max(oldPosition.column(), newPosition.column())
				- Math.min(oldPosition.column(), newPosition.column());
		int diffRow = Math.max(oldPosition.row(), newPosition.row())
				- Math.min(oldPosition.row(), newPosition.row());
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		
		//if this piece equals object a oldPos and newPos is empty
		/*Piece can move:
		 * 1. One square horiz and two vert
		 * 2. Two squares horiz and one vert
		 * 
		 * Piece can leap over other pieces so no need for clearWays
		 * */
		return this.equals(p) && ((diffCol == 2 && diffRow == 1) || (diffCol == 1 && diffRow == 2)) && t == null;
	} 
	
	/**
	 * @param oldPosition - position of this piece before move
	 * @param newPosition - position of this piece after move
	 * @param isTaken - reference to piece being CAPTURED
	 * @param board - reference to this board
	 * @return true if this capture move is valid
	 * 
	 */
	private boolean checkTake (Position oldPosition, Position newPosition, Piece isTaken, Board board) {
		int diffCol = Math.max(oldPosition.column(), newPosition.column())
				- Math.min(oldPosition.column(), newPosition.column());
		int diffRow = Math.max(oldPosition.row(), newPosition.row())
				- Math.min(oldPosition.row(), newPosition.row());
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		
		//if this piece equals object a oldPos and newPos is equal to the piece intended to be captured
	    // and captured piece is opposite colour
		
		/*Piece can move:
		 * 1. One square horiz and two vert
		 * 2. Two squares horiz and one vert
		 * 
		 * Piece can leap over other pieces so no need for clearWays
		 * */
		if (this.equals(p)	&& ((diffCol == 2 && diffRow == 1) || (diffCol == 1 && diffRow == 2)) && t != null) {
			if (t.equals(isTaken))
				return (!t.isWhite() && p.isWhite() || t.isWhite() && !p.isWhite());
		}
		return false;
	}
	
	public String toString() {
		if(isWhite) {
			return "N";
		} else {
			return "n";
		}
	}
}
