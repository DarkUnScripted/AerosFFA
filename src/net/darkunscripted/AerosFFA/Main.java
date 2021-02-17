package net.darkunscripted.AerosFFA;

import net.darkunscripted.AerosFFA.commands.*;
import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.events.*;
import net.darkunscripted.AerosFFA.managers.ArenaManager;
import net.darkunscripted.AerosFFA.managers.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private FileManager cfgm;

    @Override
    public void onEnable() {
        loadConfigManager();
        loadConfig();
        registerManagers();
        registerEvents();
        registerCommands();
        ArenaManager.loadArenas();
        loadLobby();
//        loadSpawns();
    }

    @Override
    public void onDisable() {
        ArenaManager.saveArenas();
        saveLobby();
    }

    public void registerManagers(){
        getCommand("arena").setExecutor(new ArenaCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setlobby").setExecutor(new LobbyCommand());
        getCommand("leave").setExecutor(new LeaveCommand());
        getCommand("chat").setExecutor(new ChatCommand());
        getCommand("kit").setExecutor(new KitCommand());
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new onDrop(), this);
        getServer().getPluginManager().registerEvents(new onJoin(), this);
        getServer().getPluginManager().registerEvents(new onClick(), this);
        getServer().getPluginManager().registerEvents(new onInteract(), this);
        getServer().getPluginManager().registerEvents(new onChat(), this);
    }

    public void registerCommands(){

    }

    public void loadConfigManager(){
        cfgm = new FileManager();
        cfgm.setupSpawns();
        cfgm.saveSpawns();
        cfgm.reloadSpawns();
        cfgm.setupKits();
        cfgm.saveKits();
        cfgm.reloadKits();
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public FileManager getManager(){
        return cfgm;
    }

//    public void loadSpawns(){
//        if(cfgm.spawnscfg.contains("spawns")) {
//            ConfigurationSection SpawnConfigurationSection = cfgm.spawnscfg.getConfigurationSection("spawns");
//            List<String> Spawns = new ArrayList<String>(SpawnConfigurationSection.getKeys(false));
//            for (String spawn : Spawns) {
//                double X = cfgm.spawnscfg.getDouble("spawns." + spawn + ".location.X");
//                double Y = cfgm.spawnscfg.getDouble("spawns." + spawn + ".location.Y");
//                double Z = cfgm.spawnscfg.getDouble("spawns." + spawn + ".location.Z");
//                World world = this.getServer().getWorld(cfgm.spawnscfg.getString("spawns." + spawn + ".location.world"));
//                Location location = new Location(world, X, Y, Z);
//                SpawnData.spawnLocations.put(spawn, location);
//                SpawnData.spawnNames.add(spawn);
//            }
//        }
//    }

    public void saveLobby(){
        cfgm.arenascfg.set("lobby.location.x", SpawnData.lobby.getX());
        cfgm.arenascfg.set("lobby.location.y", SpawnData.lobby.getY());
        cfgm.arenascfg.set("lobby.location.z", SpawnData.lobby.getX());
        cfgm.arenascfg.set("lobby.location.pitch", SpawnData.lobby.getPitch());
        cfgm.arenascfg.set("lobby.location.yaw", SpawnData.lobby.getYaw());
        cfgm.arenascfg.set("lobby.location.world", SpawnData.lobby.getWorld().getName());
    }

    public void loadLobby(){
        if(cfgm.arenascfg.contains("lobby.location")) {
            World world = Bukkit.getServer().getWorld(cfgm.arenascfg.getString("lobby.location.world"));
            int X = cfgm.arenascfg.getInt("lobby.location.x");
            int Y = cfgm.arenascfg.getInt("lobby.location.y");
            int Z = cfgm.arenascfg.getInt("lobby.location.z");
            Location Lobby = new Location(world, X, Y, Z);
            SpawnData.lobby = Lobby;
        }
    }

}
