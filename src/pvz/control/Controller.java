package pvz.control;

import pvz.logic.GameModel;
import pvz.control.*;
import pvz.logic.gameobjects.Peashooter;
import pvz.logic.gameobjects.Sunflower;
import pvz.view.GamePrinter;
import pvz.view.GameView;
import pvz.view.Messages;
import utils.Position;

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

	private final GameModel game;
	private final GameView view;

	public Controller(GameModel game) {
		this.game = game;
		this.view = new GamePrinter(game);
	}

	/**
	 * Runs the game logic.
	 */

	public void run() {
		view.showGame();

		String action[]; // donde se almacena el comando a ejecutar.

		while (!game.hasGameFinished()) {
			 String[] words = view.getPrompt();
			 Command command = Command.parse(words);
			 if (command != null) {
			command.execute(game, view);
			 } else {
			view.showError(Messages.UNKNOWN_COMMAND);
			 }
			}
		view.showEndMessage();
	}
}
