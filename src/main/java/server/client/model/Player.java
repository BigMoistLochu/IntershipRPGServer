package server.client.model;

public class Player {

    private int id;
    private int x;
    private int y;
    private int direction;

    public Player(int id, int x, int y, int direction) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


    public byte[] serializedPlayerPosition(){
        byte[] playerInfo = {(byte) id , (byte) x , (byte) y , (byte) direction};
        return playerInfo;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }
}
