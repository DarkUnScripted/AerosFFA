package net.darkunscripted.AerosFFA.commands;

import net.darkunscripted.AerosFFA.data.SpawnData;
import net.darkunscripted.AerosFFA.managers.InventoryManager;
import net.darkunscripted.AerosFFA.managers.Kit;
import net.darkunscripted.AerosFFA.utils.Utils;
import net.minecraft.server.v1_12_R1.CommandExecute;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class KitCommand extends CommandExecute implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(p.hasPermission("aerosnetwork.ffa.kits")){
                if(args.length == 0){
                    p.sendMessage(Utils.chat("&b------&e&lAerosFFA&b------"));
                    p.sendMessage(Utils.chat("&e- /kit create (name)"));
                    p.sendMessage(Utils.chat("&e- /kit remove (name)"));
                    p.sendMessage(Utils.chat("&e- /kit armorstand (name)"));
                    p.sendMessage(Utils.chat("&e- /kit armorstand remove"));
                    p.sendMessage(Utils.chat("&e- /kit (name)"));
                }else if(args.length == 1){
                    boolean checker = true;
                    for(Kit kit : SpawnData.kits){
                        if(kit.getName().equals(args[0])){
                            checker = false;
                            InventoryManager.giveKit(p, kit);
                        }
                    }
                    if(checker){
                        p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no kit with that name!"));
                    }
                }else if(args.length == 2){
                    if(args[0].equalsIgnoreCase("create")){
                        boolean checker = true;
                        for(Kit kit : SpawnData.kits){
                            if(kit.getName().equalsIgnoreCase(args[1])){
                                checker = false;
                            }
                        }
                        if(checker){
                            Kit kit = new Kit(args[1], p.getInventory().getContents());
                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aKit created!"));
                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &aKitName: " + kit.getName()));
                        }else{
                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is already a kit with that name!"));
                        }
                    }else if(args[0].equalsIgnoreCase("remove")){
                        boolean checker = true;
                        Kit selectedKit = null;
                        for(Kit kit : SpawnData.kits){
                            if(kit.getName().equalsIgnoreCase(args[1])){
                                checker = false;
                                kit = null;
                            }
                        }
                        if(selectedKit != null){
                            SpawnData.kits.remove(selectedKit);
                        }
                        if(checker){
                            p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no kit with that name!"));
                        }
                    }else if(args[0].equalsIgnoreCase("armorstand")){
                        if(args[1].equalsIgnoreCase("remove")){
                          Location location = p.getLocation();
                          if(SpawnData.kitArmorStands.containsKey(location)) {
                              if (location.getBlock().getType().equals(Material.ARMOR_STAND)) {
                                  location.getBlock().setType(Material.AIR);
                                  SpawnData.kitArmorStands.remove(location);
                              }else{
                                  p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no kit-armorstand at your position!"));
                              }
                          }else{
                              p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no kit-armorstand at your position!"));
                          }
                        }else {
                            boolean checker = true;
                            for (Kit kit : SpawnData.kits) {
                                if (kit.getName().equals(args[1])) {
                                    checker = false;
                                    Location location = p.getLocation();
                                    location.getBlock().setType(Material.ARMOR_STAND);
                                    if (location.getBlock() instanceof ArmorStand) {
                                        ArmorStand armorStand = (ArmorStand) location.getBlock();
                                        armorStand.setVisible(true);
                                        armorStand.setArms(true);
                                        armorStand.setCustomName(Utils.chat("&e&l" + kit.getName()));
                                        for (ItemStack item : kit.getItems()) {
                                            if (InventoryManager.isArmor(item)) {
                                                final String typeNameString = item.getType().name();
                                                if (typeNameString.endsWith("_HELMET")) {
                                                    armorStand.setHelmet(item);
                                                } else if (typeNameString.endsWith("_CHESTPLATE")) {
                                                    armorStand.setChestplate(item);
                                                } else if (typeNameString.endsWith("_LEGGINGS")) {
                                                    armorStand.setLeggings(item);
                                                } else if (typeNameString.endsWith("_BOOTS")) {
                                                    armorStand.setBoots(item);
                                                }
                                            } else if (item.getType().name().endsWith("_SWORD")) {
                                                armorStand.setItemInHand(item);
                                            }
                                        }

                                    }
                                    SpawnData.kitArmorStands.put(location, kit);
                                }
                            }
                            if (checker) {
                                p.sendMessage(Utils.chat("&b&lAerosFFA &7>> &cThere is no kit with that name!"));
                            }
                        }
                    }else{
                        p.sendMessage(Utils.chat("&b------&e&lAerosFFA&b------"));
                        p.sendMessage(Utils.chat("&e- /kit create (name)"));
                        p.sendMessage(Utils.chat("&e- /kit remove (name)"));
                        p.sendMessage(Utils.chat("&e- /kit armorstand (name)"));
                        p.sendMessage(Utils.chat("&e- /kit armorstand remove"));
                        p.sendMessage(Utils.chat("&e- /kit (name)"));
                    }
                }else{
                    p.sendMessage(Utils.chat("&b------&e&lAerosFFA&b------"));
                    p.sendMessage(Utils.chat("&e- /kit create (name)"));
                    p.sendMessage(Utils.chat("&e- /kit remove (name)"));
                    p.sendMessage(Utils.chat("&e- /kit armorstand (name)"));
                    p.sendMessage(Utils.chat("&e- /kit armorstand remove"));
                    p.sendMessage(Utils.chat("&e- /kit (name)"));
                }
            }
        }
        return false;
    }

}
