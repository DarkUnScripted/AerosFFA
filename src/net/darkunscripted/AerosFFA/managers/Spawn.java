package net.darkunscripted.AerosFFA.managers;

import org.bukkit.Location;

import java.util.ArrayList;

public class Spawn {

    private String name;
    private Location location;
    public static ArrayList<Spawn> spawns = new ArrayList<Spawn>();

    public Spawn(String name, Location location){
        this.name = name;
        this.location = location;
        spawns.add(this);
    }

    public Spawn(){
        spawns.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
