package pvz.logic;

import utils.Position;

public class GameObjectContainer {
	
	private GameObject[][] board;
	
	
	
	
	
	GameObjectContainer(){
		board = new GameObject[Game.NUM_ROWS][Game.NUM_COLS];
	}
	public boolean isEmpty(Position pos) {
		// TODO Auto-generated method stub
		return board[pos.getRow()][pos.getCol()].equals(null);
	}
	
}
