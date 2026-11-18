package pvz.logic;

import utils.Position;

public class GameObjectContainer {

    private final GameObject[][] board;

    GameObjectContainer() {
        board = new GameObject[Game.NUM_ROWS][Game.NUM_COLS + 2];
    }

    public boolean isEmpty(Position pos) {
        return board[pos.getRow()][pos.getCol()] == null;
    }

    public void add(GameObject obj) {
        int row = obj.getPosition().getRow();
        int col = obj.getPosition().getCol();
        board[row][col] = obj;
        board[row][col].onEnter();
    }

    public boolean plantAtackReceived(Position atackPos, int damage) {
        int row = atackPos.getRow();
        int col = atackPos.getCol();
        if (!isInsideContainer(row, col)) return false;
        if (board[row][col] != null) {
            return board[row][col].plantAtackReceived(damage);
        }
        return false;
    }

    public void update() {
        for (int row = 0; row < Game.NUM_ROWS; row++) {
            for (int col = 0; col <= Game.NUM_COLS; col++) {
                GameObject obj = board[row][col];
                if (obj != null && obj.update()) {
                    Position next = new Position(row, col).left();
                    board[next.getRow()][next.getCol()] = obj;
                    board[row][col] = null;
                }
            }
        }

    }

    /** Removes dead objects in one pass and reports whether any were removed. */
    public boolean removeDead() {
        boolean removed = false;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                GameObject obj = board[row][col];
                if (obj != null && !obj.isAlive()) {
                    board[row][col] = null;
                    obj.onExit();
                    removed = true;
                }
            }
        }
        return removed;
    }

    public String iconInPosition(Position pos) {
        return board[pos.getRow()][pos.getCol()].getIcon();
    }

    public void reset() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = null;
            }
        }
    }

    public boolean zombieAtackReceived(Position atackPos, int damage) {
        int row = atackPos.getRow();
        int col = atackPos.getCol();
        if (!isInsideContainer(row, col)) return false;
        if (board[row][col] != null) {
            return board[row][col].zombieAtackReceived(damage);
        }
        return false;
    }

    private boolean isInsideContainer(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
    }
}
