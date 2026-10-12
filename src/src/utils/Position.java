package utils;
import pvz.logic.Game;


public class Position {

	private int row;
	private int col;
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
	public int hashcode() {
		return row*Game.NUM_COLS+col;
	}
	
	public Position left() {
		Position position=new Position(this.getRow(),this.getCol()-1);
		return position;
	}

}
