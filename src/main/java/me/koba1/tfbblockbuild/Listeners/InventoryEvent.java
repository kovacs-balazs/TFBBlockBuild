package me.koba1.tfbblockbuild.Listeners;

import me.koba1.tfbblockbuild.Blocks.Blocks;
import me.koba1.tfbblockbuild.Main;
import me.koba1.tfbblockbuild.WorldEdit.Schematics;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.concurrent.BlockingQueue;

public class InventoryEvent implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("§8Biztos?")) {
            e.setCancelled(true);
            if(e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType() != null) {
                    if (!e.getCurrentItem().hasItemMeta()) return;
                    if (Main.placedBlocks.containsKey(e.getWhoClicked().getUniqueId())) {
                        if (e.getCurrentItem().getType() == Material.GREEN_STAINED_GLASS_PANE) {
                            Block block = Main.placedBlocks.get(e.getWhoClicked().getUniqueId());
                            block.setType(Material.AIR);
                            block.breakNaturally();

                            new Schematics("house1").loadSchematic(Main.placedBlocks.get(e.getWhoClicked().getUniqueId()), ((Player) e.getWhoClicked()));

                            e.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);

                            float yaw = 0.0F;

                            if (Schematics.dir((Player) e.getWhoClicked()).equalsIgnoreCase("S")) {
                                yaw = 180;
                            } else if (Schematics.dir((Player) e.getWhoClicked()).equalsIgnoreCase("E")) {
                                yaw = 90;
                            } else if (Schematics.dir((Player) e.getWhoClicked()).equalsIgnoreCase("W")) {
                                yaw = 270;
                            }

                            Location loc = block.getLocation();
                            loc.setYaw(yaw);

                            e.getWhoClicked().teleport(loc.add(0.5, 0, 0.5));
                            Main.placedBlocks.remove(e.getWhoClicked().getUniqueId());

                        } else if (e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) {
                            e.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
                            Block block = Main.placedBlocks.get(e.getWhoClicked().getUniqueId());
                            block.setType(Material.AIR);
                            block.breakNaturally();

                            e.getWhoClicked().getInventory().addItem(Blocks.house1());

                            Main.placedBlocks.remove(e.getWhoClicked().getUniqueId());
                        }
                    }
                }
            }
        }
    }
}
