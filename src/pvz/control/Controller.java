package pvz.control;

import utils.Position;

import pvz.logic.Game;
import pvz.view.GamePrinter;
import pvz.view.GameView;
import pvz.view.Messages;

/**
 * Input/output coordinator of the game (the C in MVC).
 *
 * <p>Owns the game loop: reads a line from stdin, parses it into a
 * command, validates parameters (plant type, position), delegates
 * state changes to {@link Game}, and triggers a board reprint via
 * {@link tp1.pvz.view.GameView>} when the cycle advances. It holds no game
 * state of its own; the source of truth is always {@link Game}.
 */
public class Controller {

	private final Game game;
	private final GameView view;

	public Controller(Game game) {
		this.game = game;
		this.view = new GamePrinter(game);
	}

	/**
	 * Runs the game logic.
	 */
	private boolean isPositionValid(Position p) {
		return (p.getCol()<game.NUM_COLS && p.getRow()<game.NUM_ROWS);
		// VOLVER supongo que en un futuro deberemos checkear que no haya plantas en esa casilla
	}
	
	private boolean isObjectValid(String object) {
		boolean valid=false;
		if(object=="peashoter") valid=true;
		else if(object=="sunflower") valid=true;
		else if(object=="s") valid=true;
		else if(object=="p") valid=true;
		return valid;
	}
	
	
	public void run() {
		
		String action[];//donde se almacena el comando a ejecutar.
		
		while(!game.hasGameFinished()) {
			view.showGame();
			for (String a : Messages.HELP_LINES) {
			    System.out.println(a);
			}
			action= view.getPrompt();
			
			
			if (action[0].equals("l") || action[0].equals("lista")) System.out.println(Messages.LIST);
			else if (action[0].equals("r") || action[0].equals("reset")) game.reset();
			else if (action[0].equals("h") || action[0].equals("help")) System.out.println(Messages.HELP);
			else if (action[0].equals("e") || action[0].equals("exit")) game.quit();
			else if (action[0].equals("n") || action[0].equals("none")) ;//VOLVER
			else System.out.println("no se ha ejecutado ningun comando");
			//no entiendo porque no coge nada de letras ni nada
			//cuidado, no funciona el ==, tiene que ser con equals, media horita tirada a la basura
		}
		System.out.println("el juego ha terminado");
		// TODO fill your code
	}
	
	
	/*
view.showGame();
while (!game.hasGameFinished()) {
// TODO fill your code
// Prompt the user for a line (getPrompt)
// Execute the user's command (method)
}
view.showEndMessage();
	 */

}
