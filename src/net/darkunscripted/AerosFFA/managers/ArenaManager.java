package net.darkunscripted.AerosFFA.managers;

import net.darkunscripted.AerosFFA.Main;
import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

        static Main plugin = Main.getPlugin(Main.class);

        public static void saveArenas(){
            try {
                for (Arena arena : SpawnData.Arenas) {
                    plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".world", arena.getWorld().getName());
                    plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".maxPlayers", arena.getMaxPlayers());
                    plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".lobby.X", arena.getLobby().getX());
                    plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".lobby.Y", arena.getLobby().getY());
                    plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".lobby.Z", arena.getLobby().getZ());
                    plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".lobby.pitch", arena.getLobby().getPitch());
                    plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".lobby.yaw", arena.getLobby().getYaw());
                    for (Spawn spawn : arena.getSpawns()) {
                        plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".spawns." + spawn.getName() + ".location.X", spawn.getLocation().getX());
                        plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".spawns." + spawn.getName() + ".location.Y", spawn.getLocation().getY());
                        plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".spawns." + spawn.getName() + ".location.Z", spawn.getLocation().getZ());
                        plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".spawns." + spawn.getName() + ".location.pitch", spawn.getLocation().getPitch());
                        plugin.getManager().arenascfg.set("arenas." + arena.getName() + ".spawns." + spawn.getName() + ".location.yaw", spawn.getLocation().getYaw());
                    }
                }
                plugin.getManager().arenascfg.save(plugin.getManager().arenasfile);
            }catch (IOException e){
                plugin.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not save arena!"));
            }
        }

        public static void loadArenas(){
            if(plugin.getManager().arenascfg.contains("arenas")) {
                ConfigurationSection arenaConfigurationSection = plugin.getManager().arenascfg.getConfigurationSection("arenas");
                List<String> ArenaNames = new ArrayList<String>(arenaConfigurationSection.getKeys(false));
                for (String arenaname : ArenaNames) {
                    int MaxPlayers = plugin.getManager().arenascfg.getInt("arenas." + arenaname + ".maxPlayers");
                    World world = Bukkit.getWorld(plugin.getManager().arenascfg.getString("arenas." + arenaname + ".world"));
                    int X = plugin.getManager().arenascfg.getInt("arenas." + arenaname + ".lobby.X");
                    int Y = plugin.getManager().arenascfg.getInt("arenas." + arenaname + ".lobby.Y");
                    int Z = plugin.getManager().arenascfg.getInt("arenas." + arenaname + ".lobby.Z");
                    Location Lobby = new Location(world, X, Y, Z);
                    Arena arena = new Arena(arenaname, world, Lobby);
                    SpawnData.Arenas.add(arena);
                    arena.setMaxPlayers(MaxPlayers);
                    if(plugin.getManager().arenascfg.contains("arenas." + arenaname + ".spawns")) {
                        ConfigurationSection spawnConfigurationSection = plugin.getManager().arenascfg.getConfigurationSection("arenas." + arenaname + ".spawns");
                        List<String> SpawnNames = new ArrayList<String>(spawnConfigurationSection.getKeys(false));
                        for (String spawnname : SpawnNames) {
                            int SpawnX = plugin.getManager().arenascfg.getInt("arenas." + arenaname + ".spawns." + spawnname + ".location.X");
                            int SpawnY = plugin.getManager().arenascfg.getInt("arenas." + arenaname + ".spawns." + spawnname + ".location.Y");
                            int SpawnZ = plugin.getManager().arenascfg.getInt("arenas." + arenaname + ".spawns." + spawnname + ".location.Z");
                            Location SpawnLocation = new Location(world, SpawnX, SpawnY, SpawnZ);
                            Spawn spawn = new Spawn(spawnname, SpawnLocation);
                            arena.addSpawn(spawn);
                        }
                    }
                }
            }
        }

}
