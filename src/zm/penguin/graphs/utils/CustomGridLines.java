package zm.penguin.graphs.utils;

import java.util.ArrayList;
import java.util.List;

public class CustomGridLines {
    public List<CustomGridLine> gridlines;

    public CustomGridLines() {
        gridlines = new ArrayList<CustomGridLine>();
    }

    public void add(CustomGridLine gridline) {
        boolean unique = true;
        for (CustomGridLine gl : gridlines) {
            if (gridline.getValue() == gl.getValue()) {
                unique = false;
                break;
            }
        }
        if (unique) {
            gridlines.add(gridline);
        }
    }

    CustomGridLine get(int i) { return gridlines.get(i); }

    public int size() {
        return gridlines.size();
    }

    public void clear() {
        gridlines.clear();
    }

    public boolean contains(float value) {
        for (CustomGridLine gl : gridlines) {
            if (gl.value == value) {
                return true;
            }
        }
        return false;
    }
}