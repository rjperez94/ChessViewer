package assignment3.chessview.moves;

import assignment3.chessview.*;
import assignment3.chessview.pieces.*;


public class SinglePieceMove implements MultiPieceMove {
	protected Piece piece;
	protected Position oldPosition;
	protected Position newPosition;
	
	public SinglePieceMove(Piece piece, Position oldPosition, Position newPosition) {
		this.piece = piece;
		this.oldPosition = oldPosition;
		this.newPosition = newPosition;
	}
	
	public Piece piece() {
		return piece;
	}
	
	public boolean isWhite() {
		return piece.isWhite();
	}
	
	public Position oldPosition() {
		return oldPosition;
	}
	
	public Position newPosition() {
		return newPosition;
	}
	
	public boolean isValid(Board board) {
		//look for checks
		board.WInCheck = board.isInCheck(true);
		board.BInCheck = board.isInCheck(false);
		
		if (board.WInCheck || board.BInCheck) {
			//look for checks in next move
			Board nextBoard = new Board(board);
			nextBoard.move(oldPosition,newPosition);
			nextBoard.WInCheck = nextBoard.isInCheck(true);
			nextBoard.BInCheck = nextBoard.isInCheck(false);
			
			//I put myself in check
			if (this.isWhite() && nextBoard.WInCheck|| !this.isWhite() && nextBoard.BInCheck) {
				return false;
			}
		}

		return piece.isValidMove(oldPosition, newPosition, null, board);
	}
	
	public void apply(Board b) {
		if (b.pieceAt(oldPosition) instanceof King) {
			//update king has moved for castling
			King k = (King)b.pieceAt(oldPosition);
			k.hasMoved = true;
		} else if (b.pieceAt(oldPosition) instanceof Rook) {
			//update rook has moved for castling
			Rook r = (Rook)b.pieceAt(oldPosition);
			r.hasMoved = true;
		} else if (b.pieceAt(oldPosition) instanceof Pawn) {
			//update pawn prev row for validating enpassant
			Pawn p = (Pawn)b.pieceAt(oldPosition);
			p.prevPos = oldPosition.row();
		}
		//move piece
		b.move(oldPosition,newPosition);
	}
	
	public String toString() {
		return pieceChar(piece) + oldPosition + "-" + newPosition; 
	}
	
	protected static String pieceChar(Piece p) {
		if(p instanceof Pawn) {
			return "";
		} else if(p instanceof Knight) {
			return "N";
		} else if(p instanceof Bishop) {
			return "B";
		} else if(p instanceof Rook) {
			return "R";
		} else if(p instanceof Queen) {
			return "Q";
		} else {
			return "K";
		}
	}
}
