package com.assignment;

import java.io.Serializable;
abstract class Player implements Serializable {
    private String name; 
    private int health;
    private int coins;
    private InventoryItem weapon;

    Player(String name, int health, int coins, InventoryItem weapon) {
        setName(name);
        setHealth(health);
        setCoins(coins);
        setWeapon(weapon);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public InventoryItem getWeapon() {
        return weapon;
    }

    public void setWeapon (InventoryItem weapon) {
        this.weapon = weapon;
    }
}
