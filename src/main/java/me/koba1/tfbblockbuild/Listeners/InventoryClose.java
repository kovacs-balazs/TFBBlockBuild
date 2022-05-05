package me.koba1.tfbblockbuild.Listeners;

import me.koba1.tfbblockbuild.Inventory.ConfirmInventory;
import me.koba1.tfbblockbuild.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryClose implements Listener {

    private Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("§8Biztos?")) {
            if (e.getReason() == InventoryCloseEvent.Reason.PLAYER) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(Main.placedBlocks.containsKey(e.getPlayer().getUniqueId())) {
                            e.getPlayer().openInventory(ConfirmInventory.inv());
                        }
                    }
                }.runTaskLater(m, 1L);
            }
        }
    }
}
