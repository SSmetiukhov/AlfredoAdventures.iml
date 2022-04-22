public class Cords {
    private int x;
    private int y;

    public Cords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setCords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Cords getCords(){
        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
