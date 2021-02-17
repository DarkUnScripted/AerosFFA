package net.darkunscripted.AerosFFA.events;

import net.darkunscripted.AerosFFA.data.ChatState;
import net.darkunscripted.AerosFFA.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class onChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        if(PlayerData.playerChat.containsKey(player.getUniqueId())) {
            if(PlayerData.playerChat.get(player.getUniqueId()).equals(ChatState.ARENA)) {
                List<Player> playersInMap = player.getWorld().getPlayers();
                e.getRecipients().clear();
                for (Player p : playersInMap) {
                    e.getRecipients().add(p);
                }
            }
        }
    }

}
