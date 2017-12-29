package Entities;

/**
 * Created by DoctorZlo on 27.12.2017.
 */
public class Player {
    private Map playerMap;
    private int id;
    private int coordX;
    private int coordY;

    public Player(int id) {
        this.id = id;
        playerMap = new Map();
        coordX = playerMap.getHeight() / 2;
        coordY = playerMap.getWeight() / 2;

        playerMap.setByCoords(coordX, coordY, "@");
    }


    public void move(Direction direction) {
        Map map = this.getMap();
        switch (direction) {
            case up:
                this.updateMap(this.getCoordX(), this.getCoordY(), "U");
                this.setCoordY(this.getCoordY() - 1);
                this.updateMap(this.getCoordX(), this.getCoordY(), "@");
                break;
            case down:
                this.updateMap(this.getCoordX(), this.getCoordY(), "U");
                this.setCoordY(this.getCoordY() + 1);
                this.updateMap(this.getCoordX(), this.getCoordY(), "@");
                break;
            case right:
                this.updateMap(this.getCoordX(), this.getCoordY(), "U");
                this.setCoordX(this.getCoordX() + 1);
                this.updateMap(this.getCoordX(), this.getCoordY(), "@");
                break;
            case left:
                this.updateMap(this.getCoordX(), this.getCoordY(), "U");
                this.setCoordX(this.getCoordX() + 1);
                this.updateMap(this.getCoordX(), this.getCoordY(), "@");
                break;
        }
    }

    public void look(Direction direction, Block block) {
        Map map = this.getMap();
        switch (direction) {
            case up:
                map.setByCoords(this.getCoordX(), this.getCoordY() - 1, getBlockSymbol(block));
                break;
            case down:
                map.setByCoords(this.getCoordX(), this.getCoordY() + 1, getBlockSymbol(block));
                break;
            case right:
                map.setByCoords(this.getCoordX() + 1, this.getCoordY(), getBlockSymbol(block));
                break;
            case left:
                map.setByCoords(this.getCoordX() - 1, this.getCoordY() - 1, getBlockSymbol(block));
                break;
        }
    }

    public void throwBomb(Direction direction) {

    }

    private String getBlockSymbol(Block block) {
        switch (block) {
            case unknown:
                return "*";
            case indestructible:
                return "#";
            case destructible:
                return "%";
            case destructed:
                return "o";
            case floor:
                return ".";
            default:
                return "?";
        }
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

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }
}
