public class Merchant implements Fieldable{

    public void sellCritChance(Player player){
        if(player.getCoins()>=10){
            player.upgradeCritChance();
            player.addCoins(-10);
            System.out.println("Your Crit chance has successfully upgraded!");
        }
        else
            System.out.println("You have not Enough gold, my friend!");
    }
    public void sellTraps(Player player){
        if(player.getCoins()>=10){
            player.addTraps(5);
            player.addCoins(-10);
            System.out.println("Now you have 5 more Traps! Be careful with them...");
        }
        else
            System.out.println("You have not Enough gold, my friend!");
    }

    public void sellHP(Player player){
        if(player.getCoins()>=10){
            player.setHealthPoints(player.getHealthPoints()+1);
            player.addCoins(-10);
            System.out.println("Now you have 1 more HP. Don't waste it pointlessly.");
        }
        else
            System.out.println("You have not Enough gold, my friend!");
    }

    public void sellDoubleMove(Player player){
        if(player.getCoins()>=10){
            player.setDoubleMoves(true);
            player.addCoins(-10);
            System.out.println("Now you can do one more action per turn!");
        }
        else
            System.out.println("You have not Enough gold, my friend!");
    }

    @Override
    public String getSymbol() {
        return " M ";
    }
}
