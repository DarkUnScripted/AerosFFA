package net.darkunscripted.AerosFFA.managers;

import net.darkunscripted.AerosFFA.data.SpawnData;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class Kit {

    private String name;
    private ItemStack[] items;

    public Kit(String name, ItemStack[] items){
        this.name = name;
        this.items = items;
        SpawnData.kits.add(this);
    }

    public Kit(String name){
        this.name = name;
        SpawnData.kits.add(this);
    }

    public String getName() {
        return name;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public void setItems(ItemStack[] items) {
        this.items = items;
    }

}
