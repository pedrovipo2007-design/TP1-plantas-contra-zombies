package pvz.control.hijas;

import pvz.control.Command;
import pvz.logic.GameModel;
import pvz.logic.ZombieFactory;
import pvz.view.GameView;
import pvz.view.Messages;
import utils.Position;

public class AddZombieCommand extends Command {

    private String zombieName;
    private Position pos;

    public AddZombieCommand() {
        name = Messages.COMMAND_ADD_ZOMBIE_NAME;
        shortcut = Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
        details = Messages.COMMAND_ADD_ZOMBIE_DETAILS;
        help = Messages.COMMAND_ADD_ZOMBIE_HELP;
    }

    private AddZombieCommand(String zombieName, int col, int row) {
        this();
        this.zombieName = zombieName;
        this.pos = new Position(row, col);
    }

    @Override
    public Command create(String[] commandWords) {
        if (commandWords.length != 4) {
            return null;
        }
        if (!commandWords[2].matches("\\d{1,3}") || !commandWords[3].matches("\\d{1,3}")) {
            return null;
        }
        return new AddZombieCommand(commandWords[1], Integer.parseInt(commandWords[2]),
                Integer.parseInt(commandWords[3]));
    }

    @Override
    public boolean execute(GameModel model, GameView view) {
        boolean added = model.addZombie(zombieName, pos);
        if (added) {
            model.update();
        } else if (!model.isInsideBoard(pos) || !model.isEmpty(pos)) {
            view.showError(Messages.INVALID_POSITION);
        } else if (!ZombieFactory.isZombieName(zombieName)) {
            view.showError(Messages.INVALID_GAME_OBJECT);
        }
        return added;
    }
}
