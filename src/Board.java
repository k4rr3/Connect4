import javax.swing.*;
import java.util.StringTokenizer;

public class Board {

    private final int numRows;
    private final int numColumns;
    private final Player[][] cells;

    public Board(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        cells = new Player[numRows][numColumns];
    }

    public boolean canPlayColumn(int column) {

        return column >= 0 && column < numColumns && cells[0][column] == null;
    }

    public boolean hasValidMoves() {
        for (int i = 0; i < numColumns; i++) {
            if (canPlayColumn(i)) {
                return true;
            }
        }
        return false;
    }

    public Position play(int column, Player player) {
        if (!canPlayColumn(column)) {
            return null;
        }
        int row = lastEmptyRow(column);
        cells[row][column] = player; //anotamos el movimiento del jugador en la tabla
        return new Position(row, column);

    }

    private int getEmptyCells(int column) {
        int emptyCells = 0;
        for (int i = 0; i < numRows; i++) {
            if (cells[i][column] == null) {
                emptyCells++;
            }
        }
        return emptyCells;
    }

    public int lastEmptyRow(int column) {
        if (!canPlayColumn(column)) {
            return -1;
        }
        return getEmptyCells(column) - 1;

    }

    public int maxConnected(Position position) {
        // We assume posision is occupied by a player
        //checkingConnectedinRows;
        int maxConnected = 1;
        maxConnected = checkConnectedInDirection(position, Direction.DOWN) + checkConnectedInDirection(position, Direction.DOWN.invert());
        maxConnected = Math.max(maxConnected, checkConnectedInDirection(position, Direction.RIGHT) + checkConnectedInDirection(position, Direction.RIGHT.invert()));
        maxConnected = Math.max(maxConnected, checkConnectedInDirection(position, Direction.MAIN_DIAGONAL) + checkConnectedInDirection(position, Direction.MAIN_DIAGONAL.invert()));
        maxConnected = Math.max(maxConnected, checkConnectedInDirection(position, Direction.CONTRA_DIAGONAL) + checkConnectedInDirection(position, Direction.CONTRA_DIAGONAL.invert()));

        return maxConnected + 1;//+1 porque se cuenta a sí mismo
    }

    private int checkConnectedInDirection(Position position, Direction direction) {
        int maxConnected = 0;
        int mainRow = position.getRow();
        int mainColumn = position.getColumn();
        position = position.move(direction);
        while (isInRange(position)) {
            if (cells[mainRow][mainColumn] == cells[position.getRow()][position.getColumn()]) {
                maxConnected++;
                position = position.move(direction);
            } else {
                break;
            }

        }
        return maxConnected;
    }

    private boolean isInRange(Position position) {
        return position.getRow() < numRows && position.getRow() >= 0 && position.getColumn() < numColumns && position.getColumn() >= 0;
    }


    // Only for testing

    public int loadBoard(String str) {
        // Does not check if it corresponds to a "real" game
        StringTokenizer st = new StringTokenizer(str, "\n");
        int nonEmpty = 0;
        int row = 0;
        while (st.hasMoreTokens()) {
            String rowChars = st.nextToken();
            for (int column = 0; column < rowChars.length(); column++) {
                Player player = Player.fromChar(rowChars.charAt(column));
                if (player != null) {
                    nonEmpty += 1;
                }
                cells[row][column] = player;
            }
            row += 1;
        }
        return nonEmpty;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(42);
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                Player player = cells[row][column];
                sb.append(player != null ? player.toString() : "·");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
