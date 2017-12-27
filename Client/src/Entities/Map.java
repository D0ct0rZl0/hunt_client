package Entities;

import java.util.ArrayList;

/**
 * Created by DoctorZlo on 27.12.2017.
 */
public class Map {
    private String[][] map;
    public Map() {
        map = new String[5][5];
        fillMap(5, 5);
    }

    public Map(int cols, int rows) {
        map = new String[cols][rows];
        fillMap(cols, rows);
    }

    private void fillMap(int cols, int rows) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                map[i][j] = "U";
            }
        }
    }

    public void setByCoords(int col, int row, String value) {
        map[col][row] = value;
    }

    public String getByCoords(int col, int row) {
        return map[col][row];
    }

    public String[][] getMatryx() {
        return map;
    }
}
