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

        return column >= 0 && column <= numRows && columnNotFilled(column);
    }

    private boolean columnNotFilled(int column) {
        /*int filledCells = getFilledCells(column);
        if (filledCells == numRows) {
            return false;
        }
        return true;
        */
        return getFilledCells(column) != numRows;
    }

    private int getFilledCells(int column) {
        int filledCells = 0;
        for (int i = 0; i < numRows; i++) {
            if (cells[i][column] != null) {
                filledCells++;
            }
        }
        return filledCells;
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
        int row = getLastEmptyRow(column);
        cells[row][column] = player; //anotamos el movimiento del jugador en la tabla
        return new Position(row, column);

    }

    private int getLastEmptyRow(int column) {
        return Math.abs(numRows - (getFilledCells(column) + 1));
    }

    public int lastEmptyRow(int column) {
        if (!canPlayColumn(column)) {
            return -1;
        }
        return getLastEmptyRow(column);

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
        Position mainPosition = new Position(position.getRow(), position.getColumn());
        position = position.move(direction);
        while (position.getRow() < numRows && position.getRow() >= 0 && position.getColumn() < numColumns && position.getColumn() >= 0) {
            if (cells[mainPosition.getRow()][mainPosition.getColumn()] == cells[position.getRow()][position.getColumn()]) {
                maxConnected++;
                position = position.move(direction);
            } else {
                break;
            }

        }
        return maxConnected;
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
