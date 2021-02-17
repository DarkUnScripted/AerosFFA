package net.darkunscripted.AerosFFA.commands;

import net.darkunscripted.AerosFFA.data.ChatState;
import net.darkunscripted.AerosFFA.data.PlayerData;
import net.darkunscripted.AerosFFA.utils.Utils;
import net.minecraft.server.v1_12_R1.CommandExecute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ChatCommand extends CommandExecute implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(p.hasPermission("aerosnetwork.ffa.chat")){
                if(args.length == 0){
                    p.sendMessage(Utils.chat("&b------&e&lAeros&b------"));
                    p.sendMessage(Utils.chat("&e- /chat <Global | Arena>"));
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("arena")){
                        if(PlayerData.playerChat.containsKey(p.getUniqueId())){
                            if(PlayerData.playerChat.get(p.getUniqueId()).equals(ChatState.ARENA)){
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cYou are already in Arena chat mode!"));
                            }else{
                                PlayerData.playerChat.put(p.getUniqueId(), ChatState.ARENA);
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aChanged chat to Arena mode!"));
                            }
                        }else{
                            PlayerData.playerChat.put(p.getUniqueId(), ChatState.ARENA);
                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aChanged chat to Arena mode!"));
                        }
                    }else if(args[0].equalsIgnoreCase("global")){
                        if(PlayerData.playerChat.containsKey(p.getUniqueId())){
                            if(PlayerData.playerChat.get(p.getUniqueId()).equals(ChatState.GLOBAL)){
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cYou are already in Global chat mode!"));
                            }else{
                                PlayerData.playerChat.put(p.getUniqueId(), ChatState.GLOBAL);
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aChanged chat to Global mode!"));
                            }
                        }else{
                            PlayerData.playerChat.put(p.getUniqueId(), ChatState.GLOBAL);
                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aChanged chat to Global mode!"));
                        }
                    }else{
                        p.sendMessage(Utils.chat("&b------&e&lAeros&b------"));
                        p.sendMessage(Utils.chat("&e- /chat <Global | Arena>"));
                    }
                }else if(args.length > 1){
                    p.sendMessage(Utils.chat("&b------&e&lAeros&b------"));
                    p.sendMessage(Utils.chat("&e- /chat <Global | Arena>"));
                }
            }else{
                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cYou dont have permission to perform this command!"));
            }
        }else{
            s.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cOnly a player can perform this command!"));
        }
        return false;
    }

}
