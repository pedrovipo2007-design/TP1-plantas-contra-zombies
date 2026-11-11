package pvz.control.hijas;

import pvz.control.Command;
import pvz.logic.GameModel;
import pvz.view.GameView;
import pvz.view.Messages;

public class HelpCommand extends Command {

	public HelpCommand() {
		name = Messages.COMMAND_HELP_NAME;
		shortcut = Messages.COMMAND_HELP_SHORTCUT;
		details = Messages.COMMAND_HELP_DETAILS;
		help = Messages.COMMAND_HELP_HELP;
	}

	@Override
	public Command create(String[] commandWords) {
		if (commandWords.length == 1) {
			return new HelpCommand();
		}
		return null;
	}

	@Override
	public void execute(GameModel model, GameView view) {
		// TODO: ask the model or view to display the command help.
		
		StringBuilder h= new StringBuilder();
		h.append(Messages.HELP_AVAILABLE_COMMANDS);
		for(Command com :AVAILABLE_COMMANDS) {
			h.append(com.getDetails());
			h.append(Messages.HELP_DETAILS_COMMAND_HELP_SEPARATOR);
			h.append(com.getHelp());
			h.append(System.lineSeparator());
		}
		String help = h.toString();
		view.showMessage(help);
	}
}
