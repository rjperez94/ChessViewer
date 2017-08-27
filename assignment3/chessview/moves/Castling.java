package assignment3.chessview.moves;

import assignment3.chessview.*;
import assignment3.chessview.pieces.*;

public class Castling implements MultiPieceMove {	
	private boolean kingSide;
	private boolean isWhite;
	
	public Castling(boolean isWhite, boolean kingSide) {		
		this.kingSide = kingSide;
		this.isWhite = isWhite;
	}
	
	public boolean isWhite() {
		return isWhite;
	}
	
	public void apply(Board board) {	
		Position blackLeftRook = new Position(8, 1); //a8
		Position blackRightRook = new Position(8, 8); //h8
		Position blackKing = new Position(8, 5); //e8
		
		Position whiteLeftRook = new Position(1, 1); //a1
		Position whiteRightRook = new Position(1, 8); //h1
		Position whiteKing = new Position(1, 5); //e1
		
		if (isWhite()) {		//if white
			if (kingSide) {		//if kingside 
				King k = (King) board.pieceAt(whiteKing);
				Rook r = (Rook) board.pieceAt(whiteRightRook);
				//set moved to true
				r.hasMoved = true;
				k.hasMoved = true;
				//move pieces
				board.move(whiteKing, new Position (1,7));
				board.move(whiteRightRook, new Position (1,6));
			} else {		//if not kingside 
				King k = (King) board.pieceAt(whiteKing);
				Rook r = (Rook) board.pieceAt(whiteLeftRook);
				//set moved to true
				r.hasMoved = true;
				k.hasMoved = true;
				//move pieces
				board.move(whiteKing, new Position (1,3));
				board.move(whiteLeftRook, new Position (1,4));
			}
		} else {		//if black
			if (kingSide) {		//if kingside
				King k = (King) board.pieceAt(blackKing);
				Rook r = (Rook) board.pieceAt(blackRightRook);
				//set moved to true
				r.hasMoved = true;
				k.hasMoved = true;
				//move pieces
				board.move(blackKing, new Position (8,7));
				board.move(blackRightRook, new Position (8,6));
				
			} else {	//if not kingside
				King k = (King) board.pieceAt(blackKing);
				Rook r = (Rook) board.pieceAt(blackLeftRook);
				//set moved to true
				r.hasMoved = true;
				k.hasMoved = true;
				//move pieces
				board.move(blackKing, new Position (8,3));
				board.move(blackLeftRook, new Position (8,4));
			}
		}
	}
	
	public boolean isValid(Board board) {
		Position blackLeftRook = new Position(8, 1); //a8
		Position blackRightRook = new Position(8, 8); //h8
		Position blackKing = new Position(8, 5); //e8
		
		Position whiteLeftRook = new Position(1, 1); //a1
		Position whiteRightRook = new Position(1, 8); //h1
		Position whiteKing = new Position(1, 5); //e1
		
		/* Inspect if:
		 * 1. White or Black
		 *  	2. Kingside or not
		 *  		3. Is rook and king where it is supposed to be?
		 *  			4. Check if it has moved before
		 *  				5. Check if way is clear
		 * */
		if (isWhite()) {
			if (kingSide) {
				if (board.pieceAt(whiteKing) != null && board.pieceAt(whiteRightRook) != null) {
					if (board.pieceAt(whiteKing) instanceof King && board.pieceAt(whiteRightRook) instanceof Rook) {
						King k = (King) board.pieceAt(whiteKing);
						Rook r = (Rook) board.pieceAt(whiteRightRook);
						
						if (!k.hasMoved && !r.hasMoved) {
							if (board.clearRowExcept(whiteKing, whiteRightRook, k,r)) {
								return true;
							}
						}
					}
				}
			} else {
				if (board.pieceAt(whiteKing) != null && board.pieceAt(whiteLeftRook) != null) {
					if (board.pieceAt(whiteKing) instanceof King && board.pieceAt(whiteLeftRook) instanceof Rook) {
						King k = (King) board.pieceAt(whiteKing);
						Rook r = (Rook) board.pieceAt(whiteLeftRook);
						
						if (!k.hasMoved && !r.hasMoved) {
							if (board.clearRowExcept(whiteKing, whiteLeftRook, k,r)) {
								return true;
							}
						}
					}
				}
			}
		} else {
			if (kingSide) {
				if (board.pieceAt(blackKing) != null && board.pieceAt(blackRightRook) != null) {
					if (board.pieceAt(blackKing) instanceof King && board.pieceAt(blackRightRook) instanceof Rook) {
						King k = (King) board.pieceAt(blackKing);
						Rook r = (Rook) board.pieceAt(blackRightRook);
						
						if (!k.hasMoved && !r.hasMoved) {
							if (board.clearRowExcept(blackKing, blackRightRook, k,r)) {
								return true;
							}
						}
					}
				}
			} else {
				if (board.pieceAt(blackKing) != null && board.pieceAt(blackLeftRook) != null) {
					if (board.pieceAt(blackKing) instanceof King && board.pieceAt(blackLeftRook) instanceof Rook) {
						King k = (King) board.pieceAt(blackKing);
						Rook r = (Rook) board.pieceAt(blackLeftRook);
						
						if (!k.hasMoved && !r.hasMoved) {
							if (board.clearRowExcept(blackKing, blackLeftRook, k,r)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;		
	}		
	
	public String toString() {
		if(kingSide) {
			return "O-O";
		} else {
			return "O-O-O";
		}
	}
}
