package net.darkunscripted.AerosFFA.events;

import net.darkunscripted.AerosFFA.data.ChatState;
import net.darkunscripted.AerosFFA.data.PlayerData;
import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.managers.InventoryManager;
import net.darkunscripted.AerosFFA.utils.Utils;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        PlayerData.playerChat.put(p.getUniqueId(), ChatState.ARENA);
        e.setJoinMessage("");
        if(SpawnData.lobby != null) {
            p.teleport(SpawnData.lobby);
        }
        InventoryManager.clearInventory(p);
        InventoryManager.giveSelector(p);
        World world = p.getWorld();
        List<Player> playersInMap = world.getPlayers();
        for(Player player : playersInMap){
            player.sendMessage(Utils.chat("&b&lAeros &7>> &e&l" + p.getName() + " has joined!"));
        }
    }

}
