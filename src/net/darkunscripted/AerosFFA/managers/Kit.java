package net.darkunscripted.AerosFFA.managers;

import net.darkunscripted.AerosFFA.data.SpawnData;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class Kit {

    private String name;
    private ArrayList<ItemStack> items = new ArrayList<ItemStack>();
    private HashMap<ItemStack, Integer> itemSlots = new HashMap<ItemStack, Integer>();

    public Kit(String name, ArrayList<ItemStack> items, HashMap<ItemStack, Integer> itemslots){
        this.name = name;
        this.items = items;
        this.itemSlots = itemslots;
        SpawnData.kits.add(this);
    }

    public Kit(String name){
        this.name = name;
        SpawnData.kits.add(this);
    }

    public String getName() {
        return name;
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }

    public HashMap<ItemStack, Integer> getItemSlots() {
        return itemSlots;
    }

    public void setItems(ArrayList<ItemStack> items) {
        this.items = items;
    }

    public void setItemSlots(HashMap<ItemStack, Integer> itemSlots) {
        this.itemSlots = itemSlots;
    }

}
