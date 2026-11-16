package pvz.logic;

import pvz.logic.gameobjects.Plant;
import pvz.logic.gameobjects.Zombies;
import utils.Position;

public class GameObjectContainer {
	
	private GameObject[][] board;
	
	
	
	
	
	GameObjectContainer(){
		board = new GameObject[Game.NUM_ROWS][Game.NUM_COLS+2];
	}
	public boolean isEmpty(Position pos) {
		return board[pos.getRow()][pos.getCol()]==null;
	}
	public void add(GameObject obj) {
		int row= obj.getPosition().getRow();
		int col= obj.getPosition().getCol();
		board[row][col]=obj;
		board[row][col].onEnter();
	}
	public boolean plantAtackReceived(Position atackPos, int damage) {
		int row=atackPos.getRow();
		int col=atackPos.getCol();
		if (!isInsideContainer(row, col)) return false;
		if(board[row][col]!=null) {
			return board[row][col].plantAtackReceived(damage);
		}
		else return false;
		
		
	}
	public void update() {
		for(int i=0;i<Game.NUM_ROWS;i++) {
			for(int j=0;j<=Game.NUM_COLS;j++) {
				if(board[i][j]!=null) {
					if(board[i][j].update()) {
						Position pos=new Position(i,j);
						board[pos.left().getRow()][pos.left().getCol()]=board[i][j];
						board[i][j]=null;
					}
				}
			}
		}
		removeDeadObjects();
		
	}
	private void removeDeadObjects() {
		boolean removed;
		do {
			removed = false;
			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < board[row].length; col++) {
					GameObject obj = board[row][col];
					if (obj != null && !obj.isAlive()) {
						board[row][col] = null;
						obj.onExit();
						removed = true;
					}
				}
			}
		} while (removed && hasDeadObjects());
	}
	private boolean hasDeadObjects() {
		for (GameObject[] row : board) {
			for (GameObject obj : row) {
				if (obj != null && !obj.isAlive()) return true;
			}
		}
		return false;
	}
	public String iconInPosition(Position pos) {
		return board[pos.getRow()][pos.getCol()].getIcon();
	}
	public void reset() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j]=null;
					
				}

			}
		}
	public boolean doZombiesReachedTheHouse() {
		for(int row=0;row<Game.NUM_ROWS;row++) {
			GameObject obj = board[row][Game.NUM_COLS+1];
			if(obj instanceof Zombies && obj.isAlive()) return true;
		}
		return false;
	}
	public boolean zombieAtackReceived(Position atackPos, int damage) {
		int row=atackPos.getRow();
		int col=atackPos.getCol();
		if (!isInsideContainer(row, col)) return false;
		if(board[row][col]!=null) {
			return board[row][col].zombieAtackReceived(damage);
		}
		else return false;
		
		
	}
	private boolean isInsideContainer(int row, int col) {
		return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
	}
		
	}
	
	
	

