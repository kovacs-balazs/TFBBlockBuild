package me.koba1.tfbblockbuild.Listeners;

import me.koba1.tfbblockbuild.Blocks.Blocks;
import me.koba1.tfbblockbuild.Inventory.ConfirmInventory;
import me.koba1.tfbblockbuild.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class BlockPlace implements Listener {

    private Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(e.isCancelled()) return;

        if(e.getItemInHand().isSimilar(Blocks.house1())) {
            for (Map.Entry<Enchantment, Integer> enchant : e.getItemInHand().getEnchantments().entrySet()) {
                if (enchant.getKey().equals(Enchantment.DURABILITY) && enchant.getValue() == 10) {
                    e.getPlayer().openInventory(ConfirmInventory.inv());
                    Main.placedBlocks.put(e.getPlayer().getUniqueId(), e.getBlockPlaced());
                }
            }
        }
    }
}
