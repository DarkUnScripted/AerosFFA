package net.darkunscripted.AerosFFA.managers;

import net.darkunscripted.AerosFFA.Main;
import net.darkunscripted.AerosFFA.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private Main plugin = Main.getPlugin(Main.class);

    //Files & File Configs

    public FileConfiguration arenascfg;
    public File arenasfile;
    public FileConfiguration kitscfg;
    public File kitsfile;

    //--------------------

    public void setupSpawns(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        arenasfile = new File(plugin.getDataFolder(), "arenas.yml");

        if(!arenasfile.exists()){
            try{
                arenasfile.createNewFile();
            }catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not create arenas.yml file!"));
            }
        }

        arenascfg = YamlConfiguration.loadConfiguration(arenasfile);
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&aarenas.yml file has been created!!"));
    }

    public FileConfiguration getSpawns(){
        return arenascfg;
    }

    public void saveSpawns(){
        try{
            arenascfg.save(arenasfile);
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&aarenas.yml has been saved"));
        }catch (IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not save the arenas.yml file"));
        }
    }

    public void reloadSpawns(){
        arenascfg = YamlConfiguration.loadConfiguration(arenasfile);
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&aarenas.yml has been reloaded"));
    }

    public void setupKits(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        kitsfile = new File(plugin.getDataFolder(), "kits.yml");

        if(!kitsfile.exists()){
            try{
                kitsfile.createNewFile();
            }catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not create kits.yml file!"));
            }
        }

        kitscfg = YamlConfiguration.loadConfiguration(kitsfile);
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&akits.yml file has been created!!"));
    }

    public FileConfiguration getKits(){
        return kitscfg;
    }

    public void saveKits(){
        try{
            kitscfg.save(kitsfile);
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&akits.yml has been saved"));
        }catch (IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not save the kits.yml file"));
        }
    }

    public void reloadKits(){
        kitscfg = YamlConfiguration.loadConfiguration(kitsfile);
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&akits.yml has been reloaded"));
    }

}
