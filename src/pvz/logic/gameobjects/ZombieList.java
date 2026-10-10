package pvz.logic.gameobjects;

import utils.Position;

import pvz.control.Level;
import pvz.logic.Game;
import pvz.logic.gameobjects.Zombie;

public class ZombieList {
	private Zombie[] zombies;
	private int numberOfZombies;
	
	
	public ZombieList() {
		zombies= new Zombie[Game.NUM_COLS*Game.NUM_ROWS];
		numberOfZombies=0;
	}
	
	public void reset() {
		numberOfZombies=0;
	}
	
	public void add(Position p) {//voy a optar por crear el zombie y luego insertarlo en vez de insertarlo y luego darle posicion
		//busqueda binaria para encontrar donde insertar el zombie en el arraty
		
		Zombie zombie=new Zombie(p);
		
		int low=0;
		int high=numberOfZombies;
		while(low<high) {
				int mid=(low+high)/2;
				if(zombie.equals(zombies[mid]))System.out.println("esa posicion ya esta ocupada");
				if(zombie.mayor(zombies[mid])){
					low=mid;
				}
				else {
					high=mid;
				}
		// le creamos hueco
		}
		for(int i=numberOfZombies+1; i>low+1;i--) {
			zombies[i]=zombies[i-1];
		}
		zombies[low]=zombie;
	}
}



