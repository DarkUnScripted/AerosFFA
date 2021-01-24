package net.darkunscripted.AerosFFA.commands;

import net.darkunscripted.AerosFFA.utils.Utils;
import net.minecraft.server.v1_12_R1.CommandExecute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class KitCommand extends CommandExecute implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(p.hasPermission("aerosnetwork.ffa.kits")){
                if(args.length == 0){
                    p.sendMessage(Utils.chat("&b------&e&lAerosFFA&b------"));
                    p.sendMessage(Utils.chat("&e- /kit create (name)"));
                    p.sendMessage(Utils.chat("&e- /kit remove (name)"));
                    p.sendMessage(Utils.chat("&e- /kit (name)"));
                }else if(args.length == 1){

                }
            }
        }
        return false;
    }

}
