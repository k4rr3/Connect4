public class Game {

    private final Board board;
    private final int toWin;
    private Status status;

    public Game(int rows, int columns, int toWin) {

        board = new Board(rows, columns);
        this.toWin = toWin;
        status = Status.ONE_PLAYS;
    }

    public Status getStatus() {
        return status;
    }

    public boolean canPlayColumn(int column) {
        return board.canPlayColumn(column);
    }

    public Move play(int column) {
        // !isFinished
        Player player = whoIsPlaying();
        Move move = new Move(player, board.play(column, player));
        checkMatchStatus(move);
        if (!isFinished()) {
            nextTurn();
        }
        return move;

    }

    private void nextTurn() {
        if (status == Status.ONE_PLAYS) {
            status = Status.TWO_PLAYS;
        } else {
            status = Status.ONE_PLAYS;
        }
    }

    private Player whoIsPlaying() {
        if (status == Status.ONE_PLAYS) {
            return Player.ONE;
        } else {
            return Player.TWO;
        }

    }

    public boolean isFinished() {

        // checkMatchStatus();
        if (status == Status.ONE_HAS_WON || status == Status.TWO_HAS_WON || status == Status.DRAW) {
            return true;
        }
        return false;
    }
    // Only for testing

    private void checkMatchStatus(Move move) {

        if (board.maxConnected(move.getPosition()) == toWin) {
            if (move.getPlayer() == Player.ONE) {
                status = Status.ONE_HAS_WON;
            } else {
                status = Status.TWO_HAS_WON;
            }
        } else if (!board.hasValidMoves()) {
            status = Status.DRAW;
        }
    }

    public void loadGame(String str) {
        // Does not check if it corresponds to a "real" game
        int played = board.loadBoard(str);
        status = played % 2 == 0
                ? Status.ONE_PLAYS
                : Status.TWO_PLAYS;
    }

    public String toString() {
        return this.board.toString();
    }
}
