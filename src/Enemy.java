public class Enemy extends MovableFieldable {
    private int level;
    private boolean isDestroyed = false;

    public Enemy(int x, int y) {
        super(x, y, 5, 5);
        level = 2;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void Destroy() {
        isDestroyed = true;
    }

    @Override
    public String getSymbol() {
        return " @ ";
    }
}
