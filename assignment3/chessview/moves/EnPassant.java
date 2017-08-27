package assignment3.chessview.moves;

import assignment3.chessview.*;
import assignment3.chessview.pieces.*;

/**
 * This represents an "en passant move" --- http://en.wikipedia.org/wiki/En_passant.
 * 
 * 
 */
public class EnPassant implements MultiPieceMove {
	private Piece piece;
	private Position oldPosition;
	private Position newPosition;

	public EnPassant(SinglePieceMove move) {
		this.piece = move.piece;
		this.oldPosition = move.oldPosition;
		this.newPosition = move.newPosition;
	}

	public boolean isWhite() {
		return piece.isWhite();
	}

	public boolean isValid(Board board) {
		Piece takenPiece = board.pieceAt(new Position (oldPosition.row(),newPosition.column()));
		int dir = isWhite() ? 1 : -1;		//determine if white or black

		if (piece instanceof Pawn && takenPiece instanceof Pawn) {		//both this piece and taken piece should be pawns
			Pawn isTaken = (Pawn) takenPiece;
			Piece t = board.pieceAt(newPosition);		//new position of CAPTURING pawn
			
			// if CAPTURED pawn has only moved once
			if (isTaken.prevPos == 2 && isTaken.isWhite() || isTaken.prevPos == 7 && !isTaken.isWhite()) {		
				//if newPos is empty and if CAPTURING pawn moves one square forward AND ....
				if (newPosition.row() == oldPosition.row()+dir && t == null) {
					//if moves one left or one right, Hence one square diagonal
					if (newPosition.column() == oldPosition.column() - 1 || newPosition.column() == oldPosition.column() + 1) {
						return !isTaken.isWhite() && isWhite() || isTaken.isWhite() && !isWhite();
					}
				}
			}
		}
		return false;
	}

	public void apply(Board board) {		
		board.move(oldPosition,newPosition);	//move pawn to newPos
		board.setPieceAt(new Position (oldPosition.row(),newPosition.column()), null); // set pawn at its relative back to null
	}

	public String toString() {
		return "ep";
	}
}
