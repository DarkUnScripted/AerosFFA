package net.darkunscripted.AerosFFA.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class onDrop implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            e.setCancelled(true);
        }
    }

}
