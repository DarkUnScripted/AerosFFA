package net.darkunscripted.AerosFFA.events;

import net.darkunscripted.AerosFFA.Main;
import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.managers.Arena;
import net.darkunscripted.AerosFFA.managers.InventoryManager;
import net.darkunscripted.AerosFFA.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class onClick implements Listener {

    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getWhoClicked().getGameMode().equals(GameMode.SURVIVAL)){
            if(e.getClickedInventory().getName().equalsIgnoreCase(Utils.chat("&b&lChoose a Map"))){
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                for(Arena arena : SpawnData.Arenas){
                    if(arena.getName().equalsIgnoreCase(Utils.stripColor(name))){
                        e.getWhoClicked().teleport(arena.getLobby());
                        InventoryManager.clearInventory((Player) e.getWhoClicked());
                        InventoryManager.giveLobbyItem((Player) e.getWhoClicked());
                    }
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e){
        if(e.getWhoClicked().getGameMode().equals(GameMode.SURVIVAL)){
            e.setCancelled(true);
        }
    }

}
