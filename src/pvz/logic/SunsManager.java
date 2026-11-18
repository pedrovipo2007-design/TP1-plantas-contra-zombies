package pvz.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pvz.logic.gameobjects.Sun;
import utils.Position;

public class SunsManager {

    private static final int RANDOM_SUN_PERIOD = 5;

    private final Random random;
    private final List<Sun> suns;
    private int generatedSuns;
    private int caughtSuns;

    public SunsManager(Random random) {
        this.random = random;
        this.suns = new ArrayList<Sun>();
    }

    public void update(int cycle) {
        for (int i = suns.size() - 1; i >= 0; i--) {
            Sun sun = suns.get(i);
            sun.update();

            if (!sun.isAlive()) {
                suns.remove(i);
            }
        }

        if (cycle > 0 && cycle % RANDOM_SUN_PERIOD == 0) {
            Position position = new Position(
                random.nextInt(Game.NUM_ROWS),
                random.nextInt(Game.NUM_COLS)
            );
            addSun(position);
        }
    }

    public void generateSun() {
        Position position = new Position(
            random.nextInt(Game.NUM_ROWS),
            random.nextInt(Game.NUM_COLS)
        );
        addSun(position);
    }

    private void addSun(Position position) {
        if (position == null
                || position.getRow() < 0 || position.getRow() >= Game.NUM_ROWS
                || position.getCol() < 0 || position.getCol() >= Game.NUM_COLS) {
            return;
        }

        suns.add(new Sun(position));
        generatedSuns++;
    }

    public int catchSuns(Position position) {
        if (!isInsideBoard(position)) {
            return 0;
        }

        int collected = 0;

        for (int i = suns.size() - 1; i >= 0; i--) {
            if (samePosition(suns.get(i).getPosition(), position)) {
                suns.remove(i);
                collected++;
            }
        }

        caughtSuns += collected;
        return collected;
    }

    public String iconInPosition(Position position) {
        if (!isInsideBoard(position)) {
            return "";
        }

        for (Sun sun : suns) {
            if (samePosition(sun.getPosition(), position)) {
                return sun.getIcon();
            }
        }

        return "";
    }

    public int getGeneratedSuns() {
        return generatedSuns;
    }

    public int getCaughtSuns() {
        return caughtSuns;
    }

    public void reset() {
        suns.clear();
        generatedSuns = 0;
        caughtSuns = 0;
    }

    private boolean isInsideBoard(Position position) {
        return position != null
            && position.getRow() >= 0 && position.getRow() < Game.NUM_ROWS
            && position.getCol() >= 0 && position.getCol() < Game.NUM_COLS;
    }

    private boolean samePosition(Position first, Position second) {
        return first.getRow() == second.getRow() && first.getCol() == second.getCol();
    }
}
