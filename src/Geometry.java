public class Geometry {

    private final int windowHeight;
    private final int windowWidth;
    private final int displayRows;
    private final int displayColumns;
    private final double padding;

    public Geometry(int windowHeight, int windowWidth, int displayRows, int displayColumns, double padding) {
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.displayRows = displayRows;
        this.displayColumns = displayColumns;
        this.padding = padding;
    }

    public double getRowHeight() {
        //return (double) ((Math.round(windowHeight / displayRows) / 100) * 100);//redondeamos a las centenas
        return (double) windowHeight / displayRows;
    }

    public double getColumnWidth() {
        //return (double) ((Math.round() / 100) * 100);//redondeamos a las centenas
        return (double) windowWidth / displayColumns;
    }

    public double getDiskHeight() {
        return (double) getRowHeight() - (padding * getRowHeight());
    }

    public double getDiskWidth() { return (double) getColumnWidth() - (padding * getColumnWidth());}

    public double columnToCenterX(int column) {
        return (double) getColumnWidth() * column + getColumnWidth() / 2;
    }

    public double rowToCenterY(int row) {
        return (double) getRowHeight() * row + getRowHeight() / 2;
    }

    public double columnToTopLeftX(int column) {

        return (double) getColumnWidth() * column + (getColumnWidth() - getDiskWidth()) / 2;
    }

    public double rowToTopLeftY(int row) {

        return (double) getRowHeight() * row + (getRowHeight() - getDiskHeight()) / 2;
    }

    public int xToColumn(int x) {

        for (int i = 0; i < displayColumns; i++) {
            if (x <= getColumnWidth() * (i + 1)) {
                return i;
            }
        }
        return displayColumns - 1;
    }
}
