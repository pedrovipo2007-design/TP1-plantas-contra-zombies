package pvz.logic;

import pvz.logic.gameobjects.Plant;
import utils.Position;

public class GameObjectContainer {
	
	private GameObject[][] board;
	
	
	
	
	
	GameObjectContainer(){
		board = new GameObject[Game.NUM_ROWS][Game.NUM_COLS];
	}
	public boolean isEmpty(Position pos) {
		// TODO Auto-generated method stub
		return board[pos.getRow()][pos.getCol()]==null;
	}
	public void add(GameObject obj) {
		int row= obj.getPosition().getRow();
		int col= obj.getPosition().getCol();
		board[row][col]=obj;
	}
	public boolean plantAtackReceived(Position atackPos, int damage) {
		int row=atackPos.getRow();
		int col=atackPos.getCol();
		return board[row][col].plantAtackReceived(damage);
		
	}
	public void update() {
		// TODO Auto-generated method stub
		for(int i=0;i<Game.NUM_ROWS;i++) {
			for(int j=0;j<Game.NUM_COLS;j++) {
				if(board[i][j]!=null) {
					board[i][j].update();
				}

			}
		}
	}
	public String iconInPosition(Position pos) {
		return board[pos.getRow()][pos.getCol()].getIcon();
	}
	
}
