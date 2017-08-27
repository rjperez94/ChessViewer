package assignment3.chessview.pieces;

import assignment3.chessview.*;

public class Rook extends PieceImpl implements Piece {
	public boolean hasMoved = false;

	public Rook(boolean isWhite) {
		super(isWhite);
	}
	
	public boolean isValidMove(Position oldPosition, Position newPosition,
			Piece isTaken, Board board) {	
		if (isTaken == null) {
			//System.out.println("TAKEN NULL");
			return checkMove (oldPosition, newPosition,	isTaken, board);
		} else {
			//System.out.println("TAKEN  not NULL");
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
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);

		int oldRow = oldPosition.row();
		int oldCol = oldPosition.column();
		int newRow = newPosition.row();
		int newCol = newPosition.column();

		 /*
	     * Fulfill one of:
	     * 1. Move in same row
	     * 2. Move in same column
	     * 
	     * Provided that the way is clear and newPos is empty
	     */
		if (oldRow == newRow) {
			if (oldRow == newRow || oldCol == newCol && board.clearRowExcept(oldPosition, newPosition, p,t)) {
				return this.equals(p) && t == null	;
			}
		} else if (oldCol == newCol) {
			if (oldRow == newRow || oldCol == newCol && board.clearColumnExcept(oldPosition, newPosition, p,t)) {
				return this.equals(p) && t == null	;
			}
		}
		return false;

		/*return this.equals(p)
						&& (t == isTaken || (isTaken != null && isTaken.equals(t)))
						&& (board.clearColumnExcept(oldPosition, newPosition, p, t));*/
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
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);

		int oldRow = oldPosition.row();
		int oldCol = oldPosition.column();
		int newRow = newPosition.row();
		int newCol = newPosition.column();

		/*
	     * Fulfill one of:
	     * 1. Move in same row
	     * 2. Move in same column
	     * 
	     * Provided that the way is clear and newPos is equal to the piece intended to be captured
	     * and captured piece is opposite colour
	     */
		if (oldRow == newRow) {
			if (this.equals(p)	&& oldRow == newRow || oldCol == newCol && board.clearRowExcept(oldPosition, newPosition, p,t) && t != null) {
				if (t.equals(isTaken))
					return (!t.isWhite() && p.isWhite() || t.isWhite() && !p.isWhite());
			}
		} else if (oldCol == newCol) {
			if (this.equals(p)	&& oldRow == newRow || oldCol == newCol && board.clearColumnExcept(oldPosition, newPosition, p,t) && t != null) {
				if (t.equals(isTaken))
					return (!t.isWhite() && p.isWhite() || t.isWhite() && !p.isWhite());
			}
		}
		return false;
	}
	
	public String toString() {
		if(isWhite) {
			return "R";
		} else {
			return "r";
		}
	}
}
