import acm.program.GraphicsProgram;
import acm.program.ProgramMenuBar;

import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Connect4 extends GraphicsProgram {

    public static final int APPLICATION_HEIGHT = 700;
    public static final int APPLICATION_WIDTH  = 700;

    private static final int ROWS    = 6;
    private static final int COLUMNS = 7;
    private static final int TO_WIN  = 4;

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    
    private Game game;
    private Display display;

    public void run() {
        addMouseListeners();
        runGame();
    }

    private void runGame() {
        display = new Display(ROWS, COLUMNS, getGCanvas());
        game = new Game(ROWS,COLUMNS, TO_WIN);
        display.showInitial();
        updateTitle();
    }
    
    private void updateTitle() {
        setTitle(display.getMessage(game.getStatus()));
    }

    private void updateCurrent(int column) {
        // We must re-check canPlay because we have played a move
        if (game.isFinished() || !game.canPlayColumn(column)) {
            display.clearCurrent();
        } else {
            display.showCurrent(column, game.getStatus());
        }
    }
    @Override
    public void mouseClicked(final MouseEvent e) {
        // This code is invoked on the EVT tread
        // I have to move its execution to a "normal" thread
        // because to show the animation it pauses the
        // execution thread and EVT should not be paused.
        executor.submit(new Runnable() {
            @Override
            public void run() {
                if (game.isFinished()) return;
                int column = display.xToColumn(e.getX());
                if (game.canPlayColumn(column)) {
                    Move move = game.play(column);
                    display.notifyMove(move);
                    updateTitle();
                    updateCurrent(column);
                }
            }
        });

    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        // This code is invoked on the EVT tread
        // I move it to the same executor as the mouseClicked code
        // to avoid being executed while the drop animation is
        // taking place.
        executor.submit(new Runnable() {
            @Override
            public void run() {
                int column = display.xToColumn(e.getX());
                updateCurrent(column);
            }
        });
    }

    // Removes the menu bar
    protected ProgramMenuBar createMenuBar() {
        return null;
    }

    public static void main(String[] args) {
        new Connect4().start(args);
    }
}
