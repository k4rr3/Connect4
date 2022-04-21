public class Position {

    private final int row;
    private final int column;

    public Position(int row, int column) {

        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Position move(Direction direction) {
        int row = this.row + direction.getChangeInRow();
        int column = this.column + direction.getChangeInColumn();
        return new Position(row, column);
    }

    public boolean isEqualTo(Position other) {

        return other != null && this.column == other.column && this.row == other.row    ;
    }

    public static int pathLength(Position pos1, Position pos2) {
        // pos1 aligned with pos2 horizontally, vertically or diagonally
        if (pos1.row == pos2.row) {
            return Math.abs(pos1.column - pos2.column) + 1;

        } else if (pos1.column == pos2.column) {
            return Math.abs(pos1.row - pos2.row) + 1;
        } else if (checkDiagonalPathLength(pos1, pos2) != 0) {
            return checkDiagonalPathLength(pos1, pos2);
        }
        return 0;
    }

    private static int checkDiagonalPathLength(Position pos1, Position pos2) {
        int position;
        if (Math.min(pos1.row, pos2.row) == pos1.row) {

            return (pos2.row - pos1.row) + 1;
        } else {
            return (pos1.row - pos2.row) + 1;
        }

    }
}

