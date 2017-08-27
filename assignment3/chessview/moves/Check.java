package assignment3.chessview.moves;

import assignment3.chessview.*;

/**
 * This represents a "check move". Note that, a check move can only be made up
 * from an underlying simple move; that is, we can't check a check move.
 * 
 * 
 */
public class Check implements Move {
	private MultiPieceMove move;	
	
	public Check(MultiPieceMove move) {
		this.move = move;
	}
	
	public MultiPieceMove move() {
		return move;
	}
	
	public boolean isWhite() {
		return move.isWhite();
	}
	
	public boolean isValid(Board board) {	
		//look ahead
		Board nextBoard = new Board(board);
		move.apply(nextBoard);

		if (move.isWhite()) {	//if white to move
			if (nextBoard.isInCheck(false) && move.isValid(board)) {		//check if that move results in check for black
				//System.out.println("BLACK IN CHECK TRUE");
				board.BInCheck = true;
				return true;
			}
		} else if (!move.isWhite()) {		//if black to move
			if (nextBoard.isInCheck(true)&& move.isValid(board)) {	//check if that move results in check for white
				//System.out.println("WHITE IN CHECK TRUE");
				board.WInCheck = true;
				return true;
			}
		}
		return false;
	}
	
	public void apply(Board board) {
		move.apply(board);
	}
	
	public String toString() {
		return move.toString() + "+";
	}
}
