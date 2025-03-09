package e2;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final int size;
	private final Knight knight;
	private final Pawn pawn;

    public LogicsImpl(int size){
    	this.size = size;
		Set<Pair<Integer,Integer>> notEmptyCells = new HashSet<>();
		this.knight = new Knight(size, notEmptyCells);
		this.pawn = new Pawn(size, notEmptyCells);
    }

	public LogicsImpl(int size, Knight knight, Pawn pawn){	//per Test
		this.size = size;
		this.knight = knight;
		this.pawn = pawn;
	}

	@Override
	public boolean hit(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException("Cannot move this piece!");
		}
		if(this.knight.checkMove(row, col)){
			this.knight.move(row, col);
			if(this.pawn.notEmptyCell(row, col)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.notEmptyCell(row, col);
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.notEmptyCell(row, col);
	}
}
