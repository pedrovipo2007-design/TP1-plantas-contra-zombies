package pvz.control.hijas;

import pvz.control.Command;
import pvz.logic.GameModel;
import pvz.view.GameView;
import pvz.view.Messages;

public class ListZombieCommand extends Command {

	public ListZombieCommand() {
		name = Messages.COMMAND_LIST_ZOMBIES_NAME;
		shortcut = Messages.COMMAND_LIST_ZOMBIES_SHORTCUT;
		details = Messages.COMMAND_LIST_ZOMBIES_DETAILS;
		help = Messages.COMMAND_LIST_ZOMBIES_HELP;
	}

	@Override
	public Command create(String[] commandWords) {
		if (commandWords.length == 1) {
			return new ListZombieCommand();
		}
		return null;
	}

	@Override
	public boolean execute(GameModel model, GameView view) {
		// TODO: retrieve and display the available zombies.
		return false;
	}
	
	
}
