package pvz.control.hijas;

import pvz.control.Command;
import pvz.logic.GameModel;
import pvz.logic.PlantFactory;
import pvz.logic.gameobjects.Plant;
import pvz.view.GameView;
import pvz.view.Messages;

public class ListPlantsCommand extends Command {

	public ListPlantsCommand() {
		name = Messages.COMMAND_LIST_PLANTS_NAME;
		shortcut = Messages.COMMAND_LIST_PLANTS_SHORTCUT;
		details = Messages.COMMAND_LIST_PLANTS_DETAILS;
		help = Messages.COMMAND_LIST_PLANTS_HELP;
	}

	@Override
	public Command create(String[] commandWords) {
		if (commandWords.length == 1) {
			return new ListPlantsCommand();
		}
		return null;
	}

	@Override
	public void execute(GameModel model, GameView view) {
		StringBuilder h= new StringBuilder();
		for(Plant p :PlantFactory.getAvailablePlants()) {
			h.append(p.getDescription());
			h.append(System.lineSeparator());
		}
		String help = h.toString();
		view.showMessage(help);
	}
}
