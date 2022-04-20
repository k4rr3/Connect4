import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GeometryTest {

    private static final double ERROR = 0.001;
    private final Geometry geometry = new Geometry(
             600,  // windowHeight
            1200,  // windowWidth
               6,  // rows
               4,  // columns
            0.4);  // padding

    @Test
    public void test_column_width() {
        assertEquals(300.0, this.geometry.getColumnWidth(), ERROR);
    }

    @Test
    public void test_row_height() {
        assertEquals(100.0, this.geometry.getRowHeight(), ERROR);
    }

    @Test
    public void test_disk_width() {
        assertEquals(180.0, this.geometry.getDiskWidth(), ERROR);
    }

    @Test
    public void test_disk_height() {
        assertEquals(60.0, this.geometry.getDiskHeight(), ERROR);
    }

    @Test
    public void test_column_to_center_x() {
        assertEquals(150.0, this.geometry.columnToCenterX(0), ERROR);
        assertEquals(450.0, this.geometry.columnToCenterX(1), ERROR);
        assertEquals(750.0, this.geometry.columnToCenterX(2), ERROR);
    }

    @Test
    public void test_row_to_center_y() {
        assertEquals(50.0, this.geometry.rowToCenterY(0), ERROR);
        assertEquals(150.0, this.geometry.rowToCenterY(1), ERROR);
        assertEquals(250.0, this.geometry.rowToCenterY(2), ERROR);
    }

    @Test
    public void test_column_to_top_left_x() {
        assertEquals(60.0, this.geometry.columnToTopLeftX(0), ERROR);
        assertEquals(360.0, this.geometry.columnToTopLeftX(1), ERROR);
        assertEquals(660.0, this.geometry.columnToTopLeftX(2), ERROR);
    }

    @Test
    public void test_row_to_top_left_y() {
        assertEquals(20.0, this.geometry.rowToTopLeftY(0), ERROR);
        assertEquals(120.0, this.geometry.rowToTopLeftY(1), ERROR);
        assertEquals(220.0, this.geometry.rowToTopLeftY(2), ERROR);
    }

    @Test
    public void test_x_to_column() {
        assertEquals(2, this.geometry.xToColumn(750));
    }
}
