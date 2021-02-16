package com.assignment;
import java.util.ArrayList;

public class Inventory {
    
    ArrayList<InventoryItem> items = new ArrayList<>();

    public Inventory() { // Name, type, cost, data, crit chance
        items.add(new Weapon("Axe", "weapon", 0, 0, 15));
        items.add(new Weapon("Lorem staff", "weapon", 0, 0, 25));
        items.add(new InventoryItem("Minor HP Potion", "hp", 5, 15, 0));
    }

    public ArrayList<InventoryItem> getItems() {
        return items;
    }
}
