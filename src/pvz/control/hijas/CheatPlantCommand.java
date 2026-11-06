package pvz.control.hijas;

import pvz.control.Command;
import pvz.logic.GameModel;
import pvz.view.GameView;
import pvz.view.Messages;

public class CheatPlantCommand extends Command {

	private String plantName;
	private int col;
	private int row;

	public CheatPlantCommand() {
		name = Messages.COMMAND_CHEAT_PLANT_NAME;
		shortcut = Messages.COMMAND_CHEAT_PLANT_SHORTCUT;
		details = Messages.COMMAND_CHEAT_PLANT_DETAILS;
		help = Messages.COMMAND_CHEAT_PLANT_HELP;
	}

	private CheatPlantCommand(String plantName, int col, int row) {
		this();
		this.plantName = plantName;
		this.col = col;
		this.row = row;
	}

	@Override
	public Command create(String[] commandWords) {
		if (commandWords.length != 4) {
			return null;
		}
		if (!commandWords[2].matches("\\d{1,3}") || !commandWords[3].matches("\\d{1,3}")) {
			return null;
		}
		return new CheatPlantCommand(commandWords[1], Integer.parseInt(commandWords[2]),
				Integer.parseInt(commandWords[3]));
	}

	@Override
	public void execute(GameModel model, GameView view) {
		// TODO: delegate free plant placement to GameModel.
	}
}
