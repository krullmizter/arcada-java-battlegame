package com.assignment;
import java.util.ArrayList;

public class Store {
    
    ArrayList<InventoryItem> items = new ArrayList<>();

    public Store() { // Name, type, cost, data, crit chance
        items.add(new InventoryItem("Major HP Potion", "hp", 25, 50, 0));
        items.add(new InventoryItem("Minor HP Potion", "hp", 5, 15, 0));
    }

    public ArrayList<InventoryItem> getItems() {
        return items;
    }
}
