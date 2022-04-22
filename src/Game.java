import java.util.Random;
import java.util.Scanner;

public class Game {
    private Field field;
    protected boolean isGameFinished = false;
    private Player player;
    private int amountOfCoinBags;
    private CoinBag[] coinBagArr;
    private int amountOfEnemies;
    private Enemy[] enemies;
    private Random random;
    private int amountOfTraps;
    private int amountOfHealTyles;

    public Game(int sizeX, int sizeY) {
        this.field = new Field(sizeX, sizeY);
        this.player = new Player(1, 1);
    }

    public Game() {
        this.field = new Field(24, 24);
        this.player = new Player(1, 1);
        this.amountOfCoinBags = 20;
        coinBagArr = new CoinBag[amountOfCoinBags];
        this.amountOfEnemies = 5;
        enemies = new Enemy[amountOfEnemies];
        random = new Random();
        amountOfTraps = 15;
        amountOfHealTyles = 2;
    }


    public void startGame(int possesPlayerX, int possesPlayerY) {
        boolean computerTurnBool = true;
        field.fillFieldEmpty();
        player.setCords(possesPlayerX, possesPlayerY, field);
        generateCoinBags();
        generateEnemies();
        generateTraps();
        generateHealTyles();
        while (!isGameFinished) {
            field.showField();
            if (computerTurnBool)
                computerTurn();
            computerTurnBool = !computerTurnBool;
            playerTurn();
            if (player.hasDoubleMoves())
                playerTurn();
            checkIfGameIsFinished();
            if (levelIsCleared())
                generateMerchant();
        }
    }


    private void playerTurn() {
        int command = Menu.playerTurnMenu();
        switch (command) {
            case 1 -> {
                if (player.getCords().getX() > 0) {
                    player.collectCoin(field, -1, 0);
                    player.attackEnemy(field, -1, 0);
                    player.stepOnTrap(field, -1, 0);
                    player.stepOnHealTyle(field, -1, 0);
                    player.interactWithMerchant(field, -1, 0);
                    player.moveUp(field);
                } else if (levelIsCleared()) {
                    System.out.println("You entered  new level!");
                    player.LVLup();
                    startGame(23, player.getCords().getY());
                } else
                    System.out.println("You have to kill all enemies to enter new level!");
            }
            case 2 -> {
                if (player.getCords().getX() < 23) {
                    player.collectCoin(field, 1, 0);
                    player.attackEnemy(field, 1, 0);
                    player.stepOnTrap(field, 1, 0);
                    player.stepOnHealTyle(field, 1, 0);
                    player.interactWithMerchant(field, 1, 0);
                    player.moveDown(field);
                } else if (levelIsCleared()) {
                    System.out.println("You entered  new level!");
                    player.LVLup();
                    startGame(0, player.getCords().getY());
                } else
                    System.out.println("You have to kill all enemies to enter new level!");
            }
            case 3 -> {
                if (player.getCords().getY() > 0) {
                    player.collectCoin(field, 0, -1);
                    player.attackEnemy(field, 0, -1);
                    player.stepOnTrap(field, 0, -1);
                    player.stepOnHealTyle(field, 0, -1);
                    player.interactWithMerchant(field, 0, -1);
                    player.moveLeft(field);
                } else if (levelIsCleared()) {
                    System.out.println("You entered  new level!");
                    player.LVLup();
                    startGame(player.getCords().getX(), 23);
                } else
                    System.out.println("You have to kill all enemies to enter new level!");
            }
            case 4 -> {
                if (player.getCords().getY() < 23) {
                    player.collectCoin(field, 0, 1);
                    player.attackEnemy(field, 0, 1);
                    player.stepOnTrap(field, 0, 1);
                    player.stepOnHealTyle(field, 0, 1);
                    player.interactWithMerchant(field, 0, 1);
                    player.moveRight(field);
                } else if (levelIsCleared()) {
                    System.out.println("You entered  new level!");
                    player.LVLup();
                    startGame(player.getCords().getX(), 0);
                } else
                    System.out.println("You have to kill all enemies to enter new level!");
            }
            case 5 -> {
                player.placeTrap(field);
            }
        }
        System.out.println("Coins collected: " + player.getCoins() + "   health left: " + player.getHealthPoints() + "    level: " + player.getLevel());
    }

    private void computerTurn() {
        for (Enemy enemy : enemies) {
            if (!enemy.isDestroyed()) {
                int command = random.nextInt(4) + 1;
                switch (command) {
                    case 1 -> {
                        if (enemy.getCords().getX() > 0) {
                            enemy.collectCoin(field, -1, 0);
                            enemy.stepOnTrap(field, -1, 0);
                            enemy.moveUp(field);
                        } else
                            System.out.println("not yet");
                    }
                    case 2 -> {
                        if (enemy.getCords().getX() < 23) {
                            enemy.collectCoin(field, 1, 0);
                            enemy.stepOnTrap(field, 1, 0);
                            enemy.moveDown(field);
                        } else
                            System.out.println("not yet");
                    }
                    case 3 -> {
                        if (enemy.getCords().getY() > 0) {
                            enemy.collectCoin(field, 0, -1);
                            enemy.stepOnTrap(field, 0, -1);
                            enemy.moveLeft(field);
                        } else
                            System.out.println("not yet");
                    }
                    case 4 -> {
                        if (enemy.getCords().getY() < 23) {
                            enemy.collectCoin(field, 0, 1);
                            enemy.stepOnTrap(field, 0, 1);
                            enemy.moveRight(field);
                        } else
                            System.out.println("not yet");
                    }
                }
            }
        }
    }


    private void generateCoinBags() {
        for (int i = 0; i < amountOfCoinBags; i++) {
            int coinBagAmountOfCoins = random.nextInt(9) + 1;
            int coinBagXPosition = random.nextInt(22) + 1;
            int coinBagYPosition = random.nextInt(22) + 1;

            if (field.getFieldable(coinBagYPosition, coinBagXPosition) instanceof EmptyField) {
                CoinBag coinBag = new CoinBag(coinBagAmountOfCoins);
                field.setFieldable(coinBagYPosition, coinBagXPosition, coinBag);
                coinBagArr[i] = coinBag;
            } else
                i--;
        }
    }

    private void generateEnemies() {
        for (int i = 0; i < amountOfEnemies; i++) {
            int enemyXPosition = random.nextInt(22) + 1;
            int enemyYPosition = random.nextInt(22) + 1;

            if (field.getFieldable(enemyYPosition, enemyXPosition) instanceof EmptyField) {
                Enemy enemy = new Enemy(enemyYPosition, enemyXPosition);
                field.setFieldable(enemyYPosition, enemyXPosition, enemy);
                enemies[i] = enemy;
            } else
                i--;
        }
    }

    private void generateMerchant() {
        field.setFieldable(11, 11, new Merchant());
    }

    private void generateTraps() {
        for (int i = 0; i < amountOfTraps; i++) {
            int trapXPosition = random.nextInt(22) + 1;
            int trapYPosition = random.nextInt(22) + 1;
            if (field.getFieldable(trapYPosition, trapXPosition) instanceof EmptyField) {
                field.setFieldable(trapYPosition, trapXPosition, new Trap());
            } else
                i--;
        }
    }

    private void generateHealTyles() {
        for (int i = 0; i < amountOfHealTyles; i++) {
            int healXPosition = random.nextInt(22) + 1;
            int healYPosition = random.nextInt(22) + 1;
            if (field.getFieldable(healYPosition, healXPosition) instanceof EmptyField) {
                field.setFieldable(healYPosition, healXPosition, new HealField());
            } else
                i--;
        }
    }


    private void checkIfGameIsFinished() {
        if (player.getHealthPoints() <= 0) {
            isGameFinished = true;
            System.out.println("You loose(");
        }
    }

    private boolean levelIsCleared() {
        boolean enemiesAreDead = true;
        for (Enemy enemy : enemies) {
            if (!enemy.isDestroyed())
                enemiesAreDead = false;
        }
        return !enemiesAreDead;
    }

}
