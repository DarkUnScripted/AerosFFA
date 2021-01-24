package net.darkunscripted.AerosFFA.events;

import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.utils.Utils;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage("");
        if(SpawnData.lobby != null) {
            p.teleport(SpawnData.lobby);
        }
        ItemStack mapItem = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = mapItem.getItemMeta();
        meta.setDisplayName(Utils.chat("&b&lChoose Map"));
        mapItem.setItemMeta(meta);
        p.getInventory().setItem(0, mapItem);
        World world = p.getWorld();
        List<Player> playersInMap = world.getPlayers();
        for(Player player : playersInMap){
            player.sendMessage(Utils.chat("&b&lAeros &7>> &e&l" + p.getName() + " has joined!"));
        }
    }

}
