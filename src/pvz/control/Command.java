package pvz.control;

import java.util.Arrays;
import java.util.List;

import pvz.control.hijas.AddPlantCommand;
import pvz.control.hijas.CatchCommand;
import pvz.control.hijas.CheatPlantCommand;
import pvz.control.hijas.ExitCommand;
import pvz.control.hijas.HelpCommand;
import pvz.control.hijas.ListPlantsCommand;
import pvz.control.hijas.ListZombieCommand;
import pvz.control.hijas.ResetCommand;
import pvz.control.hijas.UpdateCommand;
import pvz.logic.GameModel;
import pvz.view.GameView;
import pvz.view.Messages;

/** Common structure and command parser for the game's user actions. */
public abstract class Command {

	protected static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(new AddPlantCommand(),
			new ListPlantsCommand(), new ListZombieCommand(), new ResetCommand(), new HelpCommand(), new ExitCommand(),
			new UpdateCommand(), new CheatPlantCommand(), new CatchCommand());

	protected String name = Messages.EMPTY_STRING;
	protected String shortcut = Messages.EMPTY_STRING;
	protected String details = Messages.EMPTY_STRING;
	protected String help = Messages.EMPTY_STRING;

	/** Creates a command instance from its words, or returns null if invalid. */
	public abstract Command create(String[] commandWords);

	/** Executes this command using the model and view contracts. */
	public abstract  boolean execute(GameModel model, GameView view);

	public boolean matchCommand(String word) {
		return name.equals(word) || shortcut.equals(word);
	}

	public String getDetails() {
		return details;
	}

	public String getHelp() {
		return help;
	}

	public String getName() {
		return name;
	}

	public String getShortcut() {
		return shortcut;
	}

	public static Command parse(String[] commandWords) {
		if (commandWords == null || commandWords.length == 0)
			return null;

		for (Command command : AVAILABLE_COMMANDS) {
			if (command.matchCommand(commandWords[0]))
				return command.create(commandWords);
		}
		return null;
	}
	
	//public abstract boolean consumedTurn();//Al final este lo meto en el execute que si no el addplant es dificil
}
