public class CoinBag implements Fieldable {
    private int coins;

    public CoinBag(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    @Override
    public String getSymbol() {
        return " " + String.valueOf(coins) + " ";
    }
}
