package net.darkunscripted.AerosFFA.commands;

import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.utils.Utils;
import net.minecraft.server.v1_12_R1.CommandExecute;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class LobbyCommand extends CommandExecute implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(p.hasPermission("aerosnetwork.ffa.lobby")){
                if(args.length == 0){
                    Location spawn = p.getLocation();
                    SpawnData.lobby = spawn;
                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aLobby has been set!"));
                }else{
                    p.sendMessage(Utils.chat("&b------&e&lAerosFFA&b------"));
                    p.sendMessage(Utils.chat("&e- /setlobby"));
                }
            }
        }
        return false;
    }

}
