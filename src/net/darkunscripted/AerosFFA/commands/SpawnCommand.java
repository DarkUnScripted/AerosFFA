package net.darkunscripted.AerosFFA.commands;

import net.darkunscripted.AerosFFA.Main;
import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.managers.Arena;
import net.darkunscripted.AerosFFA.managers.ArenaManager;
import net.darkunscripted.AerosFFA.managers.Spawn;
import net.darkunscripted.AerosFFA.utils.Utils;
import net.minecraft.server.v1_12_R1.CommandExecute;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.ArrayList;

public class SpawnCommand extends CommandExecute implements CommandExecutor, Listener {

    String name = Main.getPlugin(Main.class).getConfig().getString("Name", "AerosFFA");
    Main plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(p.hasPermission("aerosnetwork.ffa.spawns")) {
                if (args.length == 0) {
                    p.sendMessage(Utils.chat("&b------&e&l" + name + "&b------"));
                    p.sendMessage(Utils.chat("&e- /spawn set (name)"));
                    p.sendMessage(Utils.chat("&e- /spawn remove (name)"));
                    p.sendMessage(Utils.chat("&e- /spawn list"));
                    p.sendMessage(Utils.chat("&e- /spawn (name)"));
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("list")){
                        World world = p.getWorld();
                        boolean checker = true;
                        boolean check = true;
                        for(Arena arena : SpawnData.Arenas){
                            if (arena.getWorld().equals(world)){
                                if(arena.getSpawns().size() > 0) {
                                    p.sendMessage(Utils.chat("&b&l------&b&lSpawns&b------"));
                                }else{
                                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere are no spawns in this world!"));
                                }
                                for(Spawn spawn : arena.getSpawns()){
                                    p.sendMessage(Utils.chat("&e- " + spawn.getName()));
                                }
                                check = false;
                            }
                        }
                        if(check){
                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere are no spawns in your map!"));
                        }
                    }else{
                        boolean checker = true;
                        World world = p.getWorld();
                        for(Arena arena : SpawnData.Arenas){
                            if (arena.getWorld().equals(world)){
                                checker = false;
                                boolean check = true;
                                for(Spawn spawn : arena.getSpawns()){
                                    if(spawn.getName().equalsIgnoreCase(args[0])){
                                        check = false;
                                        p.teleport(spawn.getLocation());
                                    }
                                }
                                if(check){
                                    p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThe map you are in doesn't have a spawn with that name!"));
                                }
                            }
                        }
                        if(checker){
                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThe world you are in has no Map!"));
                        }
                    }
                }else if(args.length == 2){
                    if(args[0].equalsIgnoreCase("set")){
                        if(p.hasPermission("aerosnetwork.ffa.spawns.create")){
                            World world = p.getWorld();
                            boolean checker = true;
                            for(Arena arena : SpawnData.Arenas){
                                if(arena.getWorld().equals(world)){
                                    checker = false;
                                    boolean check = true;
                                    for(Spawn spawn : arena.getSpawns()){
                                        if(spawn.getName().equalsIgnoreCase(args[1])){
                                            check = false;
                                        }
                                    }
                                    if(check){
                                        Spawn spawn = new Spawn(args[1], p.getLocation());
                                        arena.getSpawns().add(spawn);
                                        ArenaManager.saveArenas();
                                        p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aSpawn has been set!"));
                                        p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aArena: " + arena.getName() + ", SpawnName: " + spawn.getName()));
                                    }else{
                                        p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is already a spawn with that name in your map!"));
                                    }
                                }
                            }
                            if(checker){
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no Map in the world you are in!"));
                            }
                        }else{
                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cYou dont have permission to perform this command!"));
                        }
                    }else if(args[0].equalsIgnoreCase("remove")){
                        if(p.hasPermission("aerosnetwork.ffa.spawns.remove")){
                            World world = p.getWorld();
                            boolean checker = true;
                            for(Arena arena : SpawnData.Arenas){
                                if(arena.getWorld().equals(world)){
                                    checker = false;
                                    boolean check = true;
                                    Spawn spawn = null;
                                    for(Spawn spawns : arena.getSpawns()){
                                        if(spawns.getName().equalsIgnoreCase(args[1])){
                                            check = false;
                                            spawn = spawns;
                                        }
                                    }
                                    if(spawn != null){
                                        try {
                                            arena.getSpawns().remove(spawn);
                                            plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".spawns." + spawn.getName(), null);
                                            plugin.getManager().arenascfg.save(plugin.getManager().arenasfile);
                                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aSpawn removed!"));
                                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aArena: " + arena.getName() + ", SpawnName: " + spawn.getName()));
                                            spawn = null;
                                        }catch (IOException e){
                                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cError occurred could not save to file!"));
                                            plugin.getServer().getConsoleSender().sendMessage(Utils.chat("&c[ERROR] Could not remove spawn from file!"));
                                        }
                                    }
                                    if(check){
                                        p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no spawn in with that name in this map!"));
                                    }
                                }
                            }
                            if(checker){
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no Map in the world you are in!"));
                            }
                        }
                    }else{
                        p.sendMessage(Utils.chat("&b------&e&l" + name + "&b------"));
                        p.sendMessage(Utils.chat("&e- /spawn set (name)"));
                        p.sendMessage(Utils.chat("&e- /spawn remove (name)"));
                        p.sendMessage(Utils.chat("&e- /spawn list"));
                        p.sendMessage(Utils.chat("&e- /spawn (name)"));
                    }
                }else{
                    p.sendMessage(Utils.chat("&b------&e&l" + name + "&b------"));
                    p.sendMessage(Utils.chat("&e- /spawn set (name)"));
                    p.sendMessage(Utils.chat("&e- /spawn remove (name)"));
                    p.sendMessage(Utils.chat("&e- /spawn list"));
                    p.sendMessage(Utils.chat("&e- /spawn (name)"));
                }
            }else{
                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cYou do not have permission to perform this command!"));
            }
        }else{
            if(args.length < 3){
                s.sendMessage(Utils.chat("&b------&e&lAerosFFA&b------"));
                s.sendMessage(Utils.chat("&e -/spawn (arena) (name) (username)"));
            }else if(args.length == 3){
                boolean checker = true;
                for(Arena arena : SpawnData.Arenas){
                    if(arena.getName().equalsIgnoreCase(args[0])){
                        checker = false;
                        boolean check = true;
                        for(Spawn spawn : arena.getSpawns()){
                            if(spawn.getName().equalsIgnoreCase(args[1])){
                                check = false;
                                OfflinePlayer p = plugin.getServer().getOfflinePlayer(args[2]);
                                if(p != null){
                                    if(p.isOnline()){
                                        Player player = (Player) p;
                                        player.teleport(spawn.getLocation());
                                    }else{
                                        s.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThe player you named is not online!"));
                                    }
                                }else{
                                    s.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere never logged a player on with that name!"));
                                }
                            }
                        }
                        if(check){
                            s.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no spawn with that name in the arena you mentioned!"));
                        }
                    }
                }
                if(checker){
                    s.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no arena with that name!"));
                }
            }
        }
        return false;
    }

}
