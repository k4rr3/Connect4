import acm.graphics.GCanvas;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.JTFTools;

import java.awt.*;

public class Display {

    private static final double PADDING = 0.3;

    private static final Color BACKGROUND = new Color(100, 100, 100);
    private static final Color PLAYER1    = new Color(200, 50, 50);
    private static final Color PLAYER2    = new Color(170, 170, 60);
    private static final Color BOARD      = new Color(60, 60, 200);

    private final int displayRows;
    private final int displayColumns;
    private final Geometry geometry;
    private final GCanvas gCanvas;
    private final GOval current;

    public Display(int boardRows, int boardColumns, GCanvas gCanvas) {
        this.displayRows    = boardRows + 1;
        this.displayColumns = boardColumns;
        this.gCanvas        = gCanvas;
        this.geometry       = new Geometry(gCanvas.getHeight(), gCanvas.getWidth(), displayRows, displayColumns, PADDING);
        this.current        = createCurrent();
    }

    public void showInitial() {
        paintBackground();
        fillInitial();
    }

    public void notifyMove(Move move) {
        Color color = colorFromPlayer(move.getPlayer());
        Position position = move.getPosition();
        notifyMove(color, position);
    }

    private void notifyMove(Color color, Position position) {
        // row and column expressed in Board coordinates
        int boardRow = position.getRow();
        int boardColumn = position.getColumn();
        notifyMove(color, boardRow + 1, boardColumn);
    }

    private void notifyMove(Color color, int displayRow, int displayColumn) {
        clearCurrent();
        dropDisk(color, displayRow, displayColumn);
        setDisk(color, displayRow, displayColumn);
    }

    public void clearCurrent() {
        current.setVisible(false);
    }

    public String getMessage(Status status) {
        switch (status) {
            case ONE_PLAYS: return "Juga el jugador VERMELL";
            case TWO_PLAYS: return "Juga el jugador GROC";
            case ONE_HAS_WON: return "Ha guanyat el jugador VERMELL";
            case TWO_HAS_WON: return "Ha guanyat el jugador GROC";
            default /* DRAW */ : return "Partida empatada";
        }
    }

    public void showCurrent(int displayColumn, Status status) {
        double x = this.geometry.columnToTopLeftX(displayColumn);
        current.setLocation(x, current.getY());
        Color color = colorFromStatus(status);
        current.setColor(color);
        current.setVisible(true);
    }

    public int xToColumn(int x) {
        return this.geometry.xToColumn(x);
    }

    private void paintBackground() {
        gCanvas.setBackground(BACKGROUND);
        double rowHeight = this.geometry.getRowHeight();
        GRect board = new GRect(0.0, rowHeight, gCanvas.getWidth(), gCanvas.getHeight() - rowHeight);
        board.setFilled(true);
        board.setColor(BOARD);
        gCanvas.add(board);
    }

    private void fillInitial() {
        for (int displayRow = 1; displayRow <= displayRows; displayRow++) {
            for (int displayColumn = 0; displayColumn < displayColumns; displayColumn++) {
                gCanvas.add(createDiskAt(displayRow, displayColumn));
            }
        }
    }

    private Color colorFromPlayer(Player player) {
        return player.isOne() ? PLAYER1 : PLAYER2;
    }

    private void dropDisk(Color color, int displayRow, int displayColumn) {
        double x = this.geometry.columnToCenterX(displayColumn);
        for (int dRow = 1; dRow < displayRow; dRow++) {
            blink(color, x, this.geometry.rowToCenterY(dRow));
        }
    }

    private void setDisk(Color color, int displayRow, int displayColumn) {
        double x = this.geometry.columnToCenterX(displayColumn);
        double y = this.geometry.rowToCenterY(displayRow);
        changeColorAt(color, x, y);
    }

    private GOval createDiskAt(int displayRow, int displayColumn) {
        double x = this.geometry.columnToTopLeftX(displayColumn);
        double y = this.geometry.rowToTopLeftY(displayRow);
        double width  = this.geometry.getDiskWidth();
        double height = this.geometry.getDiskHeight();
        GOval disk = new GOval(x, y, width, height);
        disk.setFilled(true);
        disk.setColor(BACKGROUND);
        return disk;
    }

    private GOval createCurrent() {
        GOval oval = createDiskAt(0, 0);
        oval.setVisible(false);
        gCanvas.add(oval);
        return oval;
    }

    private Color colorFromStatus(Status status) {
        return status == Status.ONE_PLAYS ? PLAYER1 : PLAYER2;
    }

    private void blink(Color color, double x, double y) {
        changeColorAt(color, x, y);
        JTFTools.pause(100);
        changeColorAt(BACKGROUND, x, y);
    }

    private void changeColorAt(Color color, double x, double y) {
        GOval disk = (GOval) gCanvas.getElementAt(x, y);
        disk.setColor(color);
    }
}
