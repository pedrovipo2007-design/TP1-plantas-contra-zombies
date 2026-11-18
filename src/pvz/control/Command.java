package pvz.control;

import java.util.Arrays;
import java.util.List;

import pvz.control.hijas.AddPlantCommand;
import pvz.control.hijas.AddZombieCommand;
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

public abstract class Command {

	// para sus hijas investigar el "d{1,3}" para coger formato

	protected static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(new AddPlantCommand(), new AddZombieCommand(),
			new CheatPlantCommand(), new CatchCommand(), new ListPlantsCommand(), new ListZombieCommand(),
			new ResetCommand(), new HelpCommand(), new ExitCommand(), new UpdateCommand());
	// entender bien List<> (es una interfaz, similar a los vectores de c++)

	protected String name = Messages.EMPTY_STRING;
	protected String shortcut = Messages.EMPTY_STRING;
	protected String details = Messages.EMPTY_STRING;
	protected String help = Messages.EMPTY_STRING;

	// crea una instancia del comando
	public abstract Command create(String[] commandWords);

	// ejecuta el comando
	public abstract boolean execute(GameModel model, GameView view);

	// comprueba el comando
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

	public static Command parse(String[] commandWords) { // parsear es convertir un texto a un objeto
		if (commandWords == null || commandWords.length == 0)
			return null; // cuando es vacio

		for (Command command : AVAILABLE_COMMANDS) {
			if (command.matchCommand(commandWords[0]))
				return command.create(commandWords);
		}
		return null;
	}

	public static String errorFor(String[] commandWords) {
		if (commandWords == null || commandWords.length == 0) return Messages.UNKNOWN_COMMAND;
		for (Command command : AVAILABLE_COMMANDS) {
			if (command.matchCommand(commandWords[0])) {
				int requiredWords = 1;
				if (command.name.equals(Messages.COMMAND_ADD_PLANT_NAME)
						|| command.name.equals(Messages.COMMAND_ADD_ZOMBIE_NAME)
						|| command.name.equals(Messages.COMMAND_CHEAT_PLANT_NAME)) {
					requiredWords = 4;
				} else if (command.name.equals(Messages.COMMAND_CATCH_NAME)) {
					requiredWords = 3;
				} else if (command.name.equals(Messages.COMMAND_RESET_NAME) && commandWords.length == 2) {
					return Messages.COMMAND_PARAMETERS_MISSING;
				}
				return commandWords.length < requiredWords
						? Messages.COMMAND_PARAMETERS_MISSING
						: Messages.COMMAND_INCORRECT_PARAMETER_NUMBER;
			}
		}
		return Messages.UNKNOWN_COMMAND;
	}
}
