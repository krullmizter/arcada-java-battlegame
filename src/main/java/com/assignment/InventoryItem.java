package com.assignment;
import java.io.Serializable;
public class InventoryItem implements Serializable {
    private String name;
    private String type;
    private int cost;
    private int data;
    private int critChance;

    InventoryItem(String name, String type, int cost, int data, int critChance) {
        setName(name);
        setType(type);
        setCost(cost);
        setData(data);
        setCrit(critChance);
    }

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getCrit() {
        return critChance;
    }

    public void setCrit(int critChance) {
        this.critChance = critChance;
    }
}
