package pvz.control.hijas;

import pvz.control.Command;
import pvz.logic.GameModel;
import pvz.view.GameView;
import pvz.view.Messages;

public class CatchCommand extends Command {

	private int col;
	private int row;

	public CatchCommand() {
		name = Messages.COMMAND_CATCH_NAME;
		shortcut = Messages.COMMAND_CATCH_SHORTCUT;
		details = Messages.COMMAND_CATCH_DETAILS;
		help = Messages.COMMAND_CATCH_HELP;
	}

	private CatchCommand(int col, int row) {
		this();
		this.col = col;
		this.row = row;
	}

	@Override
	public Command create(String[] commandWords) {
		if (commandWords.length != 3) {
			return null;
		}
		if (!commandWords[1].matches("\\d{1,3}") || !commandWords[2].matches("\\d{1,3}")) {
			return null;
		}
		return new CatchCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
	}

	@Override
	public boolean execute(GameModel model, GameView view) {
		//Todavia no lo implemento porque no caen soles en nuestra version VOLVER
		return false;
	}
	
	
}
