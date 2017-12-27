package Entities;

/**
 * Created by DoctorZlo on 27.12.2017.
 */
public class Player {
    private Map playerMap;
    private int id;

    public Player(int id) {
        this.id = id;
        playerMap = new Map();
    }

    public void updateMap(int col, int row, String value) {
        playerMap.setByCoords(col, row, value);
    }

    public int getId() {
        return id;
    }
    public Map getMap() {
        return playerMap;
    }
}
