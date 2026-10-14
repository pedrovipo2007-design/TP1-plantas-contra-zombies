package utils;

import pvz.logic.Game;

public class Position {

	private int row;
	private int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Position(int hashcode) {
		int col = hashcode % (Game.NUM_COLS);
		this.col = col;
		this.row = (hashcode - col) / (Game.NUM_COLS);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int hashcode() {
		return row * Game.NUM_COLS + col; // hashcode es row*Game.NUM_COLS+col
	}

	public int Zhashcode() {
		if (this.col < Game.NUM_COLS)
			return this.hashcode();
		else
			return (Game.NUM_COLS * Game.NUM_ROWS + this.row);
	}

	public Position left() {
		Position position;
		if (this.col < 8) {
			position = new Position(this.getRow(), this.getCol() - 1);
		} else {
			position = new Position(this.getRow(), Game.NUM_COLS - 1);
		}

		return position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Position))
			return false;
		Position other = (Position) o;
		return row == other.row && col == other.col;
	}
}
