import java.util.Random;

public class Player extends MovableFieldable {
    private int healthPoints;
    private boolean doubleMoves;
    private int critChance;
    private double critDamage;
    private int level;
    private Random random;
    private int traps;


/*    public Player(int maxHealth, int moves, double critChance, int damage, int level) {
        this.maxHealth = maxHealth;
        this.moves = moves;
        this.critChance = critChance;
        this.damage = damage;
        this.level = level;
    }*/

    public Player(int x, int y) {
        super(x, y, 1, 0);
        healthPoints = 3;
        doubleMoves = false;
        critChance = 10;
        critDamage = 5;
        level = 1;
        random = new Random();
        traps = 3;
    }

    public void setHealthPoints(int heath) {
        this.healthPoints = heath;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getLevel() {
        return level;
    }

    public void LVLup() {
        level++;
    }

    public void upgradeCritChance() {
        this.critChance = critChance / 2;
    }

    public void addTraps(int traps) {
        this.traps += traps;
    }

    public int getTraps() {
        return traps;
    }

    public boolean hasDoubleMoves() {
        return doubleMoves;
    }

    public void setDoubleMoves(boolean doubleMoves) {
        this.doubleMoves = doubleMoves;
    }

    @Override
    public String getSymbol() {
        return " p ";
    }

    public void attackEnemy(Field field, int Xmove, int Ymove) {
        if (field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove) instanceof Enemy) {
            if ((((Enemy) field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove)).getDamage()) * this.level <= this.getDamage() * this.level + this.critDamage * (random.nextInt(critChance) / critChance)) {
                this.addCoins(((Enemy) field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove)).getCoins());
                ((Enemy) field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove)).Destroy();
                field.setFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove, new EmptyField());
                System.out.println("BOOOOM!");
            } else {
                this.healthPoints--;
                System.out.println("Opponent turned out to be stronger!");
            }
        }
    }

    public void interactWithMerchant(Field field, int Xmove, int Ymove) {
        if (field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove) instanceof Merchant) {
            int command = Menu.merchantMenu();

            switch (command) {
                case 1:
                    ((Merchant) field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove)).sellDoubleMove(this);
                    break;
                case 2:
                    ((Merchant) field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove)).sellTraps(this);
                    break;
                case 3:
                    ((Merchant) field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove)).sellCritChance(this);
                    break;
                case 4:
                    ((Merchant) field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove)).sellHP(this);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("wrong symbol");
            }
        }
    }

    public void placeTrap(Field field) {
        int command = Menu.placeTrapMenu();
        switch (command) {
            case 1 -> {
                if (this.getCords().getX() > 0) {
                    field.setFieldable(this.getCords().getX() - 1, this.getCords().getY(), new Trap());
                }
            }
            case 2 -> {
                if (this.getCords().getX() < 23) {
                    field.setFieldable(this.getCords().getX() + 1, this.getCords().getY(), new Trap());
                }
            }
            case 3 -> {
                if (this.getCords().getY() > 0) {
                    field.setFieldable(this.getCords().getX(), this.getCords().getY() - 1, new Trap());
                }
            }
            case 4 -> {
                if (this.getCords().getY() < 23) {
                    field.setFieldable(this.getCords().getX(), this.getCords().getY() + 1, new Trap());
                }
            }
        }
    }

    public void stepOnHealTyle(Field field, int Xmove, int Ymove) {
        if (field.getFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove) instanceof HealField) {

            ((Player) this).setHealthPoints(((Player) this).getHealthPoints() + 1);

            field.setFieldable(getCords().getX() + Xmove, getCords().getY() + Ymove, new EmptyField());
        }
    }

}
