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
