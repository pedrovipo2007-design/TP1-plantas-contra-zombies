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
		for(int i=0;i<Game.NUM_ROWS;i++) {
			for(int j=0;j<=Game.NUM_COLS;j++) {
				if(board[i][j]!=null) {
					if(!board[i][j].isAlive()) {
						board[i][j].onExit();
						board[i][j]=null;
					}
				}
			}
		}//ES OPTIMIZABLE PERO POR SEGURIDAD QUEDA ASI
		
	}
	public String iconInPosition(Position pos) {
		return board[pos.getRow()][pos.getCol()].getIcon();
	}
	public void reset() {
		for(int i=0;i<Game.NUM_ROWS;i++) {
			for(int j=0;j<=Game.NUM_COLS;j++) {
				board[i][j]=null;
					
				}

			}
		}
	public boolean doZombiesReachedTheHouse() {
		for(int j=0;j<Game.NUM_ROWS;j++) {
			if(board[j][Game.NUM_COLS+1]!=null) {
				if(board[j][Game.NUM_COLS+1].isAlive()) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean zombieAtackReceived(Position atackPos, int damage) {
		int row=atackPos.getRow();
		int col=atackPos.getCol();
		if(board[row][col]!=null) {
			return board[row][col].zombieAtackReceived(damage);
		}
		else return false;
		
		
	}
		
	}
	
	
	

