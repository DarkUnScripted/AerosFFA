package net.darkunscripted.AerosFFA.data;

import net.darkunscripted.AerosFFA.managers.Arena;
import net.darkunscripted.AerosFFA.managers.Kit;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

public class SpawnData {

    public static ArrayList<Arena> Arenas = new ArrayList<Arena>();
    public static ArrayList<Kit> kits = new ArrayList<Kit>();
    public static Location lobby;
    public static HashMap<Block, Kit> kitArmorStands = new HashMap<Block, Kit>();

}
