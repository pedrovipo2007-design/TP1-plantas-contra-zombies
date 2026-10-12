package utils;
import pvz.logic.Game;


public class Position {

	private int row;
	private int col;
	
	public Position(int row, int col) { // ron cola (para recordar el orden)
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
		return row*Game.NUM_COLS+col; // hashcode es row*Game.NUM_COLS+col
	}
	
	public Position left() {
		Position position=new Position(this.getRow(),this.getCol()-1);
		return position;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Position)) return false;
	    Position other = (Position) o;
	    return row == other.row && col == other.col;
	}

	@Override
	public int hashCode() {
	    return hashcode();
	}
}
