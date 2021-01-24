package net.darkunscripted.AerosFFA.events;

import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.managers.Arena;
import net.darkunscripted.AerosFFA.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class onInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getItem() != null) {
            if (e.getItem().getType().equals(Material.NETHER_STAR) && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat("&b&lChoose Map"))) {
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
                p.openInventory(MapGUI);
            }
        }
    }
}
