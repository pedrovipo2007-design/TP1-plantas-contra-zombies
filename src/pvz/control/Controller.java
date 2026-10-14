package pvz.control;

import pvz.logic.Game;
import pvz.view.GamePrinter;
import pvz.view.GameView;
import pvz.view.Messages;
import utils.Position;
import pvz.logic.gameobjects.Peashooter;
import pvz.logic.gameobjects.Sunflower;
/**
 * Input/output coordinator of the game (the C in MVC).
 *
 * <p>
 * Owns the game loop: reads a line from stdin, parses it into a command,
 * validates parameters (plant type, position), delegates state changes to
 * {@link Game}, and triggers a board reprint via {@link tp1.pvz.view.GameView>}
 * when the cycle advances. It holds no game state of its own; the source of
 * truth is always {@link Game}.
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
		return (p.getCol() < game.NUM_COLS && p.getRow() < game.NUM_ROWS);
		// VOLVER - NO SE USA
	}

	private boolean isObjectValid(String object) { // VOLVER - NO SE USA
		boolean valid = false;
		if (object.equals("peashooter"))
			valid = true;
		else if (object.equals("sunflower"))
			valid = true;
		else if (object.equals("s"))
			valid = true;
		else if (object.equals("p"))
			valid = true;
		return valid;
	}

	public void run() {
		view.showGame();

		String action[]; // donde se almacena el comando a ejecutar.

		while (!game.hasGameFinished()) {

			action = view.getPrompt();

//			Available commands:
//				[a]dd <plant> <col> <row>: add a plant in position (col, row) - [0,CTE)
//				[l]ist: print the list of available plants
//				[r]eset: start a new game
//				[h]elp: print this help message
//				[e]xit: terminate the program
//				[n]one | "": skips cycle
//				Command > 

			if ((action[0].equals("a") || action[0].equals("add"))
					&& (action.length < 4 || !action[2].matches("\\d+") || !action[3].matches("\\d+"))) {

				view.showError(Messages.INVALID_COMMAND);

				// comprobamos que los datos que incluye el add son correctos
				// las 2 ultimas condiciones comprueban que son numeros (lo he buscado)
				// la otra condicion comprueba que haya 4 inputs

			}
			
			else if (action[0].equals("a") || action[0].equals("add")) {//ahora se hace utilizando las constantes
				

			    Position pos = new Position(Integer.parseInt(action[3]), Integer.parseInt(action[2]));

			    if (!game.checkGameObject(action[1])) {
			        view.showError(Messages.INVALID_GAME_OBJECT);
			    } else if (!game.isInsideBoard(pos) || !game.isEmpty(pos)) {
			        view.showError(Messages.INVALID_POSITION);
			    } else {
			        boolean sunflower = action[1].equals("s") || action[1].equals("sunflower");
			        int cost = sunflower ? Sunflower.COST : Peashooter.COST;
			        if (game.getCoins() >= cost) {
			            game.addGameObject(action[1], pos);
			            game.update();
			            view.showGame();
			        } else
			            view.showError(Messages.NOT_ENOUGH_COINS);
			    }
			}
			// VOLVER
			// comparar tests
			// isPositionValid y isObjectValid no se usan

			else if (action[0].equals("l") || action[0].equals("list"))
				view.showMessage(Messages.LIST);
			else if (action[0].equals("r") || action[0].equals("reset")) {

				game.reset();
				view.showGame();
			} else if (action[0].equals("h") || action[0].equals("help"))
				view.showMessage(Messages.HELP);
			else if (action[0].equals("e") || action[0].equals("exit"))
				game.quit();
			else if (action[0].equals("n") || action[0].equals("none") || action[0].equals("")) {
				game.update();
				view.showGame();
			} else
				view.showError(Messages.INVALID_COMMAND);

			// cuidado, no funciona el ==, tiene que ser con equals, media horita tirada a
			// la basura
		}
		view.showEndMessage();
	}
}
