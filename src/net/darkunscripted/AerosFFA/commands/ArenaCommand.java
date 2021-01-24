package net.darkunscripted.AerosFFA.commands;

import net.darkunscripted.AerosFFA.Main;
import net.darkunscripted.AerosFFA.data.ArenaState;
import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.managers.Arena;
import net.darkunscripted.AerosFFA.managers.ArenaManager;
import net.darkunscripted.AerosFFA.managers.Spawn;
import net.darkunscripted.AerosFFA.utils.Utils;
import net.minecraft.server.v1_12_R1.CommandExecute;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.IOException;

public class ArenaCommand extends CommandExecute implements CommandExecutor, Listener {
    // -/arena create (name)
    // -/arena remove (name)
    // -/arena setlobby

    Main plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(p.hasPermission("aerosnetwork.ffa.arena")) {
                if (args.length == 0) {
                    p.sendMessage(Utils.chat("&b------&e&lArena&b------"));
                    p.sendMessage(Utils.chat("&e- /arena create (name)"));
                    p.sendMessage(Utils.chat("&e- /arena remove (name)"));
                    p.sendMessage(Utils.chat("&e- /arena setstatus (status)"));
                    p.sendMessage(Utils.chat("&e- /arena setlobby"));
                    p.sendMessage(Utils.chat("&e- /arena setitem"));
                    p.sendMessage(Utils.chat("&e- /arena list"));
                } else if (args.length == 1) {
                    if (p.hasPermission("aerosnetwork.ffa.arena.setlobby")){
                        if(args[0].equalsIgnoreCase("setlobby")) {
                            Location location = p.getLocation();
                            World world = location.getWorld();
                            boolean checker = true;
                            for (Arena arena : SpawnData.Arenas) {
                                if (arena.getWorld().equals(world)) {
                                    checker = false;
                                    arena.setLobby(location);
                                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aLobby has been set!"));
                                }
                            }
                            if (checker) {
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no Arena in this world!"));
                            }
                        }else if(args[0].equalsIgnoreCase("list")){
                            if(SpawnData.Arenas.size() == 0){
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere are no Arena's!"));
                            }else{
                                p.sendMessage(Utils.chat("&b------&e&lArenas&b------"));
                                for(Arena arena : SpawnData.Arenas){
                                    p.sendMessage(Utils.chat("&e- " + arena.getName()));
                                }
                            }
                        }else if(args[0].equalsIgnoreCase("setitem")){
                            World world = p.getWorld();
                            Material guiItem = p.getItemInHand().getType();
                            boolean checker = true;
                            for(Arena arena : SpawnData.Arenas){
                                if(arena.getWorld().equals(world)){
                                    checker = false;
                                    arena.setGuiItem(guiItem);
                                }
                            }
                            if(checker){
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThe world you are in is not an arena!"));
                            }
                        }
                    }else{
                        p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cYou do not have permission to perform this command!"));
                    }
                } else if(args.length == 2){
                    if(p.hasPermission("aerosnetwork.ffa.arena.setup")){
                        if(args[0].equalsIgnoreCase("create")){
                            boolean checker = true;
                            boolean check = true;
                            for(Arena arena : SpawnData.Arenas){
                                if(arena.getWorld().equals(p.getWorld())){
                                    check = false;
                                }
                            }
                            for(Arena arena : SpawnData.Arenas){
                                if(arena.getName().equalsIgnoreCase(args[1])){
                                    checker = false;
                                }
                            }
                            if(check) {
                                if (checker) {
                                    Arena arena = new Arena(args[1], p.getWorld(), p.getLocation());
                                    SpawnData.Arenas.add(arena);
                                    ArenaManager.saveArenas();
                                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aArena has been created!"));
                                } else {
                                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cA arena with that name already exists!"));
                                }
                            }else{
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is already and Arena in this World!"));
                            }
                        }else if(args[0].equalsIgnoreCase("remove")){
                            boolean checker = false;
                            Arena arena = null;
                            for(Arena arenas : SpawnData.Arenas){
                                if(arenas.getName().equalsIgnoreCase(args[1])){
                                    checker = true;
                                    arena = arenas;
                                }
                            }
                            if(checker){
                                try {
                                    SpawnData.Arenas.remove(arena);
                                    for(Spawn spawn : arena.getSpawns()){
                                        spawn = null;
                                    }
                                    plugin.getManager().arenascfg.set("arenas." + arena.getName(), null);
                                    plugin.getManager().arenascfg.save(plugin.getManager().arenasfile);
                                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aArena removed!"));
                                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aArenaName: " + arena.getName()));
                                    arena = null;
                                }catch (IOException e){
                                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cCould not remove arena from file!"));
                                }
                            }else{
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no arena with that name!"));
                            }
                        }else if(args[0].equalsIgnoreCase("setstatus")){
                            if(args[1].equalsIgnoreCase("OFFLINE")){
                                World world = p.getWorld();
                                boolean checker = true;
                                for(Arena arena : SpawnData.Arenas){
                                    if(arena.getWorld().equals(world)){
                                        checker = false;
                                        arena.setState(ArenaState.OFFLINE);
                                        p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aSetted Status of " + arena.getName() + " to OFFLINE!"));
                                    }
                                }
                                if(checker){
                                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no Arena in your world!"));
                                }
                            }else if(args[1].equalsIgnoreCase("ONLINE")){
                                World world = p.getWorld();
                                boolean checker = true;
                                for(Arena arena : SpawnData.Arenas){
                                    if(arena.getWorld().equals(world)){
                                        checker = false;
                                        arena.setState(ArenaState.ONLINE);
                                        p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aSetted Status of " + arena.getName() + " to ONLINE!"));
                                    }
                                }
                                if(checker){
                                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no Arena in your world!"));
                                }
                            }else if(args[1].equalsIgnoreCase("MAINTENANCE")){
                                World world = p.getWorld();
                                boolean checker = true;
                                for(Arena arena : SpawnData.Arenas){
                                    if(arena.getWorld().equals(world)){
                                        checker = false;
                                        arena.setState(ArenaState.MAINTENANCE);
                                        p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aSetted Status of " + arena.getName() + " to in MAINTENANCE!"));
                                    }
                                }
                                if(checker){
                                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no Arena in your world!"));
                                }
                            }else{
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThe status can only be: ONLINE, OFFLINE, MAINTENANCE!"));
                            }
                        }else{
                            p.sendMessage(Utils.chat("&b------&e&lArena&b------"));
                            p.sendMessage(Utils.chat("&e- /arena create (name)"));
                            p.sendMessage(Utils.chat("&e- /arena remove (name)"));
                            p.sendMessage(Utils.chat("&e- /arena setstatus (status)"));
                            p.sendMessage(Utils.chat("&e- /arena setlobby"));
                            p.sendMessage(Utils.chat("&e- /arena setitem"));
                            p.sendMessage(Utils.chat("&e- /arena list"));
                        }
                    }else{
                        p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cYou do not have permission to perform this command!"));
                    }
                }else{
                    p.sendMessage(Utils.chat("&b------&e&lArena&b------"));
                    p.sendMessage(Utils.chat("&e- /arena create (name)"));
                    p.sendMessage(Utils.chat("&e- /arena remove (name)"));
                    p.sendMessage(Utils.chat("&e- /arena setstatus (status)"));
                    p.sendMessage(Utils.chat("&e- /arena setlobby"));
                    p.sendMessage(Utils.chat("&e- /arena setitem"));
                    p.sendMessage(Utils.chat("&e- /arena list"));
                }
            }else{
                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cYou do not have permission to perform this command!"));
            }
        }
        return false;
    }
    
}
