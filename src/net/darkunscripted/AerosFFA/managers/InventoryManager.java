package net.darkunscripted.AerosFFA.managers;

import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    public static void giveSelector(Player player){
        ItemStack mapItem = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = mapItem.getItemMeta();
        meta.setDisplayName(Utils.chat("&b&lChoose Map"));
        mapItem.setItemMeta(meta);
        player.getInventory().setItem(0, mapItem);
    }

    public static void clearInventory(Player player){
        player.getInventory().clear();
    }

    public static void giveKit(Player player, Kit kit){
        for(ItemStack item : kit.getItems()){
            player.getInventory().setItem(kit.getItemSlots().get(item), item);
        }
    }

    public static void giveLobbyItem(Player player){
        ItemStack lobbyItem = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = lobbyItem.getItemMeta();
        meta.setDisplayName(Utils.chat("&c&lLeave"));
        lobbyItem.setItemMeta(meta);
        player.getInventory().setItem(8, lobbyItem);
    }

    public static void arenaGUI(Player player){
        int slots = SpawnData.Arenas.size();
        while (slots % 9 != 0) {
            slots += 1;
        }
        Inventory MapGUI = Bukkit.createInventory(null, slots + 9, Utils.chat("&b&lChoose a Map"));
        for (int i = 0; i < SpawnData.Arenas.size(); i++) {
            Arena arena = SpawnData.Arenas.get(i);
            ItemStack arenaItem = new ItemStack(arena.getGuiItem());
            ItemMeta meta = arenaItem.getItemMeta();
            meta.setDisplayName(Utils.chat("&e&l" + arena.getName()));
            List<String> lore = new ArrayList<String>();
            lore.add(Utils.chat("&bPlayers: " + arena.getWorld().getPlayers().size()));
            String status;
            switch (arena.getState()) {
                case ONLINE:
                    status = "&aStatus: ONLINE";
                    break;
                case OFFLINE:
                    status = "&8Status: OFFLINE";
                    break;
                case MAINTENANCE:
                    status = "&cStatus: MAINTENANCE";
                    break;
                default:
                    status = "&7Status: UNDEFINED";
                    break;
            }

            lore.add(Utils.chat(status));
            meta.setLore(lore);
            arenaItem.setItemMeta(meta);
            MapGUI.setItem(i, arenaItem);
        }
        player.openInventory(MapGUI);
    }

}
