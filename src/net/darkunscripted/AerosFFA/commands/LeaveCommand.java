package net.darkunscripted.AerosFFA.commands;

import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.managers.Arena;
import net.darkunscripted.AerosFFA.managers.InventoryManager;
import net.darkunscripted.AerosFFA.utils.Utils;
import net.minecraft.server.v1_12_R1.CommandExecute;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class LeaveCommand extends CommandExecute implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            World world = p.getWorld();
            boolean checker = false;
            for(Arena arena : SpawnData.Arenas){
                if(arena.getWorld().equals(world)){
                    checker = true;
                }
            }
            if(checker){
                InventoryManager.clearInventory(p);
                if(SpawnData.lobby != null) {
                    p.teleport(SpawnData.lobby);
                }
                InventoryManager.giveSelector(p);
            }else{
                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cYou are not in a Map!"));
            }
        }
        return false;
    }

}
