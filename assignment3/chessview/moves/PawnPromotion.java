package assignment3.chessview.moves;

import assignment3.chessview.*;
import assignment3.chessview.pieces.*;

/**
 * This represents a pawn promotion.
 *
 */
public class PawnPromotion implements MultiPieceMove {
	private Piece promotion;
	private Piece piece;
	private Position oldPosition;
	private Position newPosition;
	private Move move;
	
	public PawnPromotion(SinglePieceMove move, Piece promotion) {
		this.move = move;
		this.promotion = promotion;
		this.piece = move.piece;
		this.oldPosition = move.oldPosition;
		this.newPosition = move.newPosition;
	}
	
	public boolean isWhite() {
		return piece.isWhite();
	}
	
	public boolean isValid(Board board) {
		if (piece instanceof Pawn) {		//if piece is pawn
			//apply this move with board copy
			Board nextboard = new Board(board);
			this.apply(nextboard);
			
			//look for checks in board copy
			nextboard.WInCheck = nextboard.isInCheck(true);
			nextboard.BInCheck = nextboard.isInCheck(false);
			//look for checks in this board
			board.WInCheck = board.isInCheck(true);
			board.BInCheck = board.isInCheck(false);
			
			if (move.isValid(board)) {	//move should be valid
				if (piece.isWhite() && newPosition.row() == 8) {		//is white pawn at its last row
					if (!board.WInCheck && (!nextboard.WInCheck && !nextboard.BInCheck)) {		
						//no side should be in check as check move class should have handled this move if it were
						return true;
					}
				} else if (!piece.isWhite() && newPosition.row() == 1) {	//is black pawn at its last row
					//no side should be in check as check move class should have handled this move if it were
					if (!board.BInCheck && (!nextboard.BInCheck && !nextboard.WInCheck)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void apply(Board board) {
		board.move(oldPosition, newPosition);		//move piece and set piece at newPos to promoted piece
		board.setPieceAt(newPosition, promotion);
	}
	
	public String toString() {
		return super.toString() + "=" + SinglePieceMove.pieceChar(promotion);
	}
}
