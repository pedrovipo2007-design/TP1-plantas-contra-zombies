package pvz.control.hijas;

import pvz.control.Command;
import pvz.logic.GameModel;
import pvz.view.GameView;
import pvz.view.Messages;

public class UpdateCommand extends Command {

	public UpdateCommand() {
		name = Messages.COMMAND_UPDATE_NAME;
		shortcut = Messages.COMMAND_UPDATE_SHORTCUT;
		details = Messages.COMMAND_UPDATE_DETAILS;
		help = Messages.COMMAND_UPDATE_HELP;
	}

	@Override
	public boolean matchCommand(String word) {
		return super.matchCommand(word) || Messages.EMPTY_STRING.equals(word);
	}

	@Override
	public Command create(String[] commandWords) {
		if (commandWords.length == 1) {
			return new UpdateCommand();
		}
		return null;
	}

	@Override
	public boolean execute(GameModel model, GameView view) {
		model.update();
		return false;
	}
	
	
}
