package assignment3.chessview.pieces;

import assignment3.chessview.*;

public class Pawn extends PieceImpl implements Piece {
	public boolean initialPos = false;
	public int prevPos;
	public Pawn(boolean isWhite) {
		super(isWhite);
		if (isWhite) {		//remember starting row positions 
			prevPos = 2;
		} else {
			prevPos = 7;
		}
	}
	
	public boolean isValidMove(Position oldPosition, Position newPosition, Piece isTaken, Board board) {
		if (isTaken == null) {
			//System.out.println("TAKEN NULL");  non-capture
			return checkMove (oldPosition, newPosition,	isTaken, board);
		} else {
			//System.out.println("TAKEN  not NULL"); capture
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
		prevPos = oldPosition.row(); //remember row where pawn came from -- used for en passant
		
		int dir = isWhite ? 1 : -1;		//determine direction of pawn moves using colour
		
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		
		//if a pawn hasn't moved from starting position,  set flag to true
		if (oldPosition.row() == 2 && isWhite || oldPosition.row() == 7 && !isWhite) {
			initialPos = true;
		}
		/*Piece moves:
		 * 1. In SAME column
		 * 2. Can move one or two squares forward if at initialPos
		 * 3. Can only move one square forward in any other case, can't move back
		 * 
		 * Provided that the way is clear and newPos is empty
		 */
		if (oldPosition.column() == newPosition.column()) {
			if (initialPos) {
				//for white
				if (oldPosition.row() == 2 && (newPosition.row() == 3 || newPosition.row() == 4) && board.pieceAt(new Position(3, newPosition.column())) == null) {
					return (this.equals(p) && t == null);
				//for black	
				} else if (oldPosition.row() == 7 && (newPosition.row() == 6 || newPosition.row() == 5) && board.pieceAt(new Position(6, newPosition.column())) == null) {
					return (this.equals(p) && t == null);
				} 
			} else {
				return ((oldPosition.row()+dir == newPosition.row()) && this.equals(p) && t == null);
			}
		}
		return false;
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
		int dir = isWhite ? 1 : -1;
		Piece t = board.pieceAt(newPosition);
		
		/*Piece can only capture one square diagonally and it can't move back
		 * ***See en passant move for special conditions
		 * 
		 * Provided that the way is clear and newPos is equal to the piece intended to be captured
	     * and captured piece is opposite colour
		 */
		if (newPosition.row() == oldPosition.row()+dir && t != null) {
			if (newPosition.column() == oldPosition.column() - 1 || newPosition.column() == oldPosition.column() + 1) {
				if (t.equals(isTaken))
					return !isTaken.isWhite() && this.isWhite || isTaken.isWhite() && !this.isWhite;
			}
		}
		return false;
	}
	
	public String toString() {
		if(isWhite) {
			return "P";
		} else {
			return "p";
		}
	}
}
