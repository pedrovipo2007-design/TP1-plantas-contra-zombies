package pvz.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utils.Position;

public class GameObjectContainer {

    private final List<GameObject>[][] board;

    @SuppressWarnings("unchecked")
    GameObjectContainer() {
        board = (List<GameObject>[][]) new List[Game.NUM_ROWS][Game.NUM_COLS + 2];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new ArrayList<>();
            }
        }
    }

    public boolean isEmpty(Position pos) {
        return board[pos.getRow()][pos.getCol()].isEmpty();
    }

    public void add(GameObject obj) {
        int row = obj.getPosition().getRow();
        int col = obj.getPosition().getCol();
        board[row][col].add(obj);
        obj.onEnter();
    }

    public boolean plantAtackReceived(Position atackPos, int damage) {
        int row = atackPos.getRow();
        int col = atackPos.getCol();
        if (!isInsideContainer(row, col)) return false;
        for (GameObject object : board[row][col]) {
            if (object.plantAtackReceived(damage)) return true;
        }
        return false;
    }

    public void update() {
        for (int row = 0; row < Game.NUM_ROWS; row++) {
            for (int col = 0; col <= Game.NUM_COLS; col++) {
                List<GameObject> objects = new ArrayList<>(board[row][col]);
                for (GameObject obj : objects) {
                    if (!board[row][col].contains(obj) || !obj.update()) continue;
                    Position next = new Position(row, col).left();
                    board[row][col].remove(obj);
                    board[next.getRow()][next.getCol()].add(obj);
                }
            }
        }
    }

    /** Removes dead objects in one pass and reports whether any were removed. */
    public boolean removeDead() {
        boolean removed = false;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Iterator<GameObject> iterator = board[row][col].iterator();
                while (iterator.hasNext()) {
                    GameObject obj = iterator.next();
                    if (!obj.isAlive()) {
                        iterator.remove();
                        obj.onExit();
                        removed = true;
                    }
                }
            }
        }
        return removed;
    }

    public String iconInPosition(Position pos) {
        return board[pos.getRow()][pos.getCol()].get(0).getIcon();
    }

    public void reset() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col].clear();
            }
        }
    }

    public boolean zombieAtackReceived(Position atackPos, int damage) {
        int row = atackPos.getRow();
        int col = atackPos.getCol();
        if (!isInsideContainer(row, col)) return false;
        for (GameObject object : board[row][col]) {
            if (object.zombieAtackReceived(damage)) return true;
        }
        return false;
    }

    private boolean isInsideContainer(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
    }
}
