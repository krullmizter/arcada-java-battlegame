package com.assignment;
import java.util.Random;
import java.util.Scanner;

import java.io.File;

public class Gameplay {
    Player human;
    Player enemy;

    boolean enemyDead = false;

    Random rand         = new Random();
    Scanner inputObj    = new Scanner(System.in);
    Inventory inventory = new Inventory();

    String humanSaveFile = "human.txt";
    String enemySaveFile = "enemy.txt";

    public Gameplay() {
        if (FileUtil.deSerialize(humanSaveFile) != null) {
            human = (Player) FileUtil.deSerialize(humanSaveFile);
            enemy = (Player) FileUtil.deSerialize(enemySaveFile);

            System.out.println("Welcome back " + human.getName());

        } else {
            System.out.println("Welcome to the Ultimate Battle Game!\n\nWhat's your name champion?");

            String playerName = inputObj.nextLine().trim();
            
            // Name, HP, Coins, Weapon
            human = new Human(playerName, 200, 20, inventory.getItems().get(0));
            enemy = new Enemy("Kyle",     150, 0,  inventory.getItems().get(1));
        }

        stats();
        menu();
    }

    public void menu() {
        System.out.println("\n----------------------------\nWhat do you want to do next?\n#1:Open Inventory. #2:Check Your Stats. #3:Open Store. #4:Attack!. #9:Save & Quit");
        
        switch (inputObj.nextInt()) {
            case 1:
                inventory();
                break;

            case 2:
                stats();
                break;
            
            case 3:
                store();
                break;
            
            case 4:
                play();
                break;
            
            case 9:
                saveQuit();
                break;
        
            default:
                saveQuit();
                break;
        }
    }

    public void inventory() {
        System.out.println("\nBelow are all your items:\nYour equipped weapon is: " + human.getWeapon().getName() + ". Your coin purse: " + human.getCoins() + "€");

        for (int i = 0; i < inventory.getItems().size(); i++) {
            if (inventory.items.get(i).getType() == "weapon") {
                System.out.println(i+1 + ". " + inventory.items.get(i).getName() + ". Crit chance: " + inventory.items.get(i).getCrit());

            } else {
                System.out.println(i+1 + ". " + inventory.items.get(i).getName());
            }
        }

        System.out.print("Equip / Use item #: ");
        int inventoryInput = inputObj.nextInt() - 1;

        if (inventoryInput < inventory.items.size() && inventoryInput > inventory.items.size()) {
            if (inventory.items.get(inventoryInput).getType() == "hp") {
                human.setHealth(human.getHealth() + inventory.items.get(inventoryInput).getData());

                inventory.items.remove(inventoryInput);

                System.out.println("You used: " + inventory.items.get(inventoryInput).getName() + "\nYour HP is now: " + human.getHealth());
    
            } else if (inventory.items.get(inventoryInput).getType() == "weapon") {
                human.setWeapon(inventory.items.get(inventoryInput));
                System.out.println("You equipped: " + inventory.items.get(inventoryInput).getName());
            }
            
        } else {
            System.out.println("You can't equip / use that item.");
        }
        
        menu();
    }

    public void play() {

        if (enemyDead) {
            System.out.println(enemy.getName() + " is dead, but here comes a new opponent!");
            enemy = new Enemy("Chris", 250, 0, inventory.getItems().get(0));
            System.out.println("The opponent new opponent is: " + enemy.getName() + " HP: " + enemy.getHealth() + ". He's wielding: " + enemy.getWeapon().getName());
            attack();

        } else {
            attack();
        }
    }

    public void attack() {
        int humanHp = human.getHealth();
        int enemyHp = enemy.getHealth();

        boolean playing    = true;
        boolean playerTurn = true;

        System.out.println("\n" + enemy.getName() + ": You want to fight me?!");

        while (humanHp > 0 && enemyHp > 0 && playing) {
            int damage = rand.nextInt(50);

            if (playerTurn) {
                System.out.println("\nIt's your turn! Press ENTER to attack, or press Q to flee.\n");
                String input = inputObj.nextLine().trim();

                if ("q".equals(input)) {
                    flee();
                    playing = false;

                } else {
                    int totalDmg = damage + human.getWeapon().getCrit();
                    enemyHp -= totalDmg;
                    enemy.setHealth(enemyHp);
                    System.out.println(human.getName() + " attacked " + enemy.getName() + " with " + damage + " dmg + " + human.getWeapon().getCrit() + " crit ("+totalDmg+") " + enemy.getName() + "s HP is: " + enemyHp);
                    playerTurn = false;
                }

            } else {
                int totalDmg = damage + human.getWeapon().getCrit();
                humanHp -= totalDmg;
                human.setHealth(humanHp);
                System.out.println(enemy.getName() + " attacked " + human.getName() + " with " + damage + " dmg + " + enemy.getWeapon().getCrit() + " crit ("+totalDmg+") " + human.getName() + "s HP is: " + humanHp);
                playerTurn = true;
            }
        }

        if (humanHp <= 0) {
            System.out.println(enemy.getName() + " wins!\nBad luck " + human.getName() + " better luck next time, all your progress will be lost.\nGood bye.");
            File humanSave = new File(humanSaveFile); 
            File enemySave = new File(enemySaveFile);

            humanSave.delete();
            enemySave.delete();

            System.exit(0);

        } else {
            System.out.println(human.getName() + " wins!");
            enemyDead = true;
            playing = false;

            if (rand.nextInt(100) > 50) {
                int droppedCoins = rand.nextInt(25);
                human.setCoins(human.getCoins() + droppedCoins);
                System.out.println(enemy.getName() + " dropped some coins " + droppedCoins + ", they were added to your coin purse");
            }

            menu();
        }
    }

    public void flee() {
        int humanHp = human.getHealth();
        int damage  = rand.nextInt(50);
        
        System.out.println("I'm fleeing for my life!\nWhilst you're fleeing, " + enemy.getName() + " is chasing you! He might do some damage to you.");

        if (rand.nextInt(100) > 50) {
            System.out.println("You got away unharmed.");

        } else {
            humanHp -= damage;
            human.setHealth(humanHp);
            System.out.println(enemy.getName() + " caught up with you, he did " + damage + "DMG to you, your HP is now: " + humanHp);
        }

        menu();
    }

    public void store() {
        Store store     = new Store();
        int coinsAmount = human.getCoins();

        if (store.getItems().size() == 0) {
            System.out.println("There are no items in the shop...");

        } else if (coinsAmount == 0) {
            System.out.println("Sorry you can't purchase anything, you're broke...");

        } else {
            System.out.println("\nYour coin purse: " + coinsAmount + "€\nWhat do you want to buy?");

            for (int i = 0; i < store.getItems().size(); i++) {
                System.out.println(i+1 + ". " + store.items.get(i).getName() + ". Cost: " + store.items.get(i).getCost() + "€");
            }

            System.out.print("Purchase item #: ");
            int purchaseItem = inputObj.nextInt() - 1;

            if (purchaseItem < store.getItems().size() && purchaseItem > store.getItems().size()) {
                if (coinsAmount < store.items.get(purchaseItem).getCost()) {
                    System.out.println("You don't have enough money for that, move along!");
                
                } else {
                    coinsAmount -= store.items.get(purchaseItem).getCost();
                    inventory.items.add(store.items.get(purchaseItem));
                        
                    System.out.println(store.items.get(purchaseItem).getName() + " was added to your inventory.\nYour coin purse is now: " + coinsAmount + "€");
                }

            } else {
                System.out.println("That item doesn't exist, choose something else...");
                store();
            }
        }
        
        menu();
    }

    public void stats() {
        System.out.println("\n-----------\nGame stats:");
        System.out.println("\nYour name is: " + human.getName() + ", with: " + human.getHealth() + "HP. You're wielding: " + human.getWeapon().getName() + ". Your coin purse: " + human.getCoins() + "€");

        if (enemyDead) {
            System.out.println(enemy.getName() + " is dead. Maybe fight someone new???");

        } else {
            System.out.println("The enemy is: "   + enemy.getName() + ", with: " + enemy.getHealth() + "HP. He's wielding: "   + enemy.getWeapon().getName());
        }

        menu();
    }

    public void saveQuit() {
        FileUtil.serialize(human, "human.txt");
        FileUtil.serialize(enemy, "enemy.txt");

        System.out.println("Saving & exiting the game.");

        System.exit(0);
    }
	
}
