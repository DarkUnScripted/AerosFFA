package net.darkunscripted.AerosFFA.events;

import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.managers.Arena;
import net.darkunscripted.AerosFFA.managers.InventoryManager;
import net.darkunscripted.AerosFFA.managers.Kit;
import net.darkunscripted.AerosFFA.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
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
                InventoryManager.arenaGUI(p);
            }
        }
        if(e.getClickedBlock() != null) {
            if (e.getClickedBlock().getType().equals(Material.ARMOR_STAND)) {
                if (SpawnData.kitArmorStands.containsKey(e.getClickedBlock())) {
                    Kit kit = SpawnData.kitArmorStands.get(e.getClickedBlock());
                    InventoryManager.giveKit(e.getPlayer(), kit);
                }
            }
        }
    }
}
