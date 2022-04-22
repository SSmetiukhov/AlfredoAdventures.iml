public abstract class MovableFieldable implements Fieldable {
    private final Cords cords;
    private int coins;
    protected double damage;

    public MovableFieldable(int x, int y, int damage, int coins) {
        this.cords = new Cords(x, y);
        this.damage = damage;
        this.coins = coins;
    }

    public void setCords(int x, int y, Field field) {
        this.cords.setCords(x, y);
        field.setFieldable(x, y, this);
    }

    public void moveRight(Field field) {
        if (cords.getY() < 23) {
            field.setFieldable(cords.getX(), cords.getY(), new EmptyField());
            setCords(cords.getX(), cords.getY() + 1, field);
        } else
            System.out.println("not yet");
    }

    public void moveLeft(Field field) {
        if (cords.getY() > 0) {
            field.setFieldable(cords.getX(), cords.getY(), new EmptyField());
            setCords(cords.getX(), cords.getY() - 1, field);
        } else
            System.out.println("not yet");
    }

    public void moveDown(Field field) {
        if (cords.getX() < 24) {
            field.setFieldable(cords.getX(), cords.getY(), new EmptyField());
            setCords(cords.getX() + 1, cords.getY(), field);
        } else
            System.out.println("not yet");
    }

    public void moveUp(Field field) {
        if (cords.getX() > 0) {
            field.setFieldable(cords.getX(), cords.getY(), new EmptyField());
            setCords(cords.getX() - 1, cords.getY(), field);
        } else
            System.out.println("not yet");
    }

    public int getCoins() {
        return coins;
    }

    public void collectCoin(Field field, int Xmove, int Ymove) {
        if (field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove) instanceof CoinBag) {
            this.addCoins(((CoinBag) field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove)).getCoins());
            field.setFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove, new EmptyField());
        }
    }

    public void stepOnTrap(Field field, int Xmove, int Ymove) {
        if (field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove) instanceof Trap) {
            if (this instanceof Enemy)
                ((Enemy) this).Destroy();
            else if (this instanceof Player)
                ((Player) this).setHealthPoints(((Player) this).getHealthPoints() - 1);

            field.setFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove, new EmptyField());
        }
    }


    public Cords getCords() {
        return cords;
    }

    public double getDamage() {
        return damage * (coins / 5.0);
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }


    abstract public String getSymbol();
}
