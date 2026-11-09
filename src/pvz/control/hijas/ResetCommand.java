package pvz.control.hijas;

import pvz.control.Command;
import pvz.control.Level;
import pvz.logic.GameModel;
import pvz.view.GameView;
import pvz.view.Messages;

public class ResetCommand extends Command {

	private Level level;
	private long seed;
	private boolean hasConfiguration;

	public ResetCommand() {
		name = Messages.COMMAND_RESET_NAME;
		shortcut = Messages.COMMAND_RESET_SHORTCUT;
		details = Messages.COMMAND_RESET_DETAILS;
		help = Messages.COMMAND_RESET_HELP;
	}

	private ResetCommand(Level level, long seed) {
		this();
		this.level = level;
		this.seed = seed;
		this.hasConfiguration = true;
	}

	@Override
	public Command create(String[] commandWords) {
		if (commandWords.length == 1) {
			return new ResetCommand();
		}
		if (commandWords.length != 3) {
			return null;
		}

		Level parsedLevel = Level.valueOfIgnoreCase(commandWords[1]);
		if (parsedLevel == null || !commandWords[2].matches("-?\\d+")) {
			return null;
		}
		try {
			return new ResetCommand(parsedLevel, Long.parseLong(commandWords[2]));
		} catch (NumberFormatException exception) {
			return null;
		}
	}

	@Override
	public void execute(GameModel model, GameView view) {
		// TODO: reset the model, using level and seed when hasConfiguration is true.
		model.reset();
	}
}
