package pvz.control.hijas;

import pvz.control.Command;
import pvz.logic.GameModel;
import pvz.view.GameView;
import pvz.view.Messages;

public class ExitCommand extends Command {

	public ExitCommand() {
		name = Messages.COMMAND_EXIT_NAME;
		shortcut = Messages.COMMAND_EXIT_SHORTCUT;
		details = Messages.COMMAND_EXIT_DETAILS;
		help = Messages.COMMAND_EXIT_HELP;
	}

	@Override
	public Command create(String[] commandWords) {
		if (commandWords.length == 1) {
			return new ExitCommand();
		}
		return null;
	}

	@Override
	public void execute(GameModel model, GameView view) {
		// TODO: request the model to end the game.
	}
}
