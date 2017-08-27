package assignment3.chessview.pieces;

import assignment3.chessview.*;

public class Bishop extends PieceImpl implements Piece {
	public Bishop(boolean isWhite) {
		super(isWhite);
	}
	
	public boolean isValidMove(Position oldPosition, Position newPosition,
			Piece isTaken, Board board) {
		if (isTaken == null) {
			//System.out.println("TAKEN NULL"); non-capture
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
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		
		//idx differences - row and column
		int diffRow = Math.abs(newPosition.row() - oldPosition.row());
	    int diffCol = Math.abs(newPosition.column() - oldPosition.column());
		
	    //Move diagonally provided that the way is clear and newPos is empty
	    if(diffRow == diffCol && board.clearDiaganolExcept(oldPosition, newPosition, p)) {
			return this.equals(p) && t == null;
		}
	    
		return false;
		/*return this.equals(p)
				&& (t == isTaken || (isTaken != null && isTaken.equals(t)))
				&& (board.clearDiaganolExcept(oldPosition, newPosition, p, t)
						|| board.clearColumnExcept(oldPosition, newPosition, p,
								t) || board.clearRowExcept(oldPosition,
						newPosition, p, t));*/		
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
		
		//idx differences - row and column
		int diffRow = Math.abs(newPosition.row() - oldPosition.row());
		int diffCol = Math.abs(newPosition.column() - oldPosition.column());

		/*
		 * Move diagonally provided that the way is clear and newPos is equal to the piece intended to be captured
	     * and captured piece is opposite colour
	     */
		if(this.equals(p) && diffRow == diffCol && board.clearDiaganolExcept(oldPosition, newPosition, p) && t != null) {
			if (t.equals(isTaken))
				return (!t.isWhite() && p.isWhite() || t.isWhite() && !p.isWhite());
		}
		return false;
	}
	
	public String toString() {
		if(isWhite) {
			return "B";
		} else {
			return "b";
		}
	}
}
