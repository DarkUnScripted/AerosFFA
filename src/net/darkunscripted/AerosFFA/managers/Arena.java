package net.darkunscripted.AerosFFA.managers;

import net.darkunscripted.AerosFFA.data.ArenaState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;

public class Arena {

    private String name;
    private int MaxPlayers;
    private World world;
    private ArrayList<Spawn> spawns = new ArrayList<Spawn>();
    private Location lobby;
    private ArenaState state;
    private Material guiItem;

    public Arena(String name, World world, Location location){
        this.name = name;
        this.world = world;
        this.lobby = location;
        this.state = ArenaState.MAINTENANCE;
        this.guiItem = Material.GRASS;
    }

    public String getName() {
        return name;
    }

    public World getWorld() {
        return world;
    }

    public int getMaxPlayers() {
        return MaxPlayers;
    }

    public ArrayList<Spawn> getSpawns() {
        return spawns;
    }

    public Location getLobby() {
        return lobby;
    }

    public ArenaState getState() {
        return state;
    }

    public Material getGuiItem() {
        return guiItem;
    }

    public void setSpawns(ArrayList<Spawn> spawns) {
        this.spawns = spawns;
    }

    public void setMaxPlayers(int maxPlayers) {
        MaxPlayers = maxPlayers;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSpawn(Spawn spawn){
        this.spawns.add(spawn);
    }

    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

    public void setState(ArenaState state) {
        this.state = state;
    }

    public void setGuiItem(Material guiItem) {
        this.guiItem = guiItem;
    }
}
