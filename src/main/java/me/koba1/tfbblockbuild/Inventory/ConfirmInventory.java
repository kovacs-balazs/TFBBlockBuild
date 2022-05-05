package me.koba1.tfbblockbuild.Inventory;

import com.sk89q.worldedit.world.registry.ItemMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ConfirmInventory {

    public static Inventory inv() {
        Inventory inv = Bukkit.createInventory(null, 3*9, "§8Biztos?");

        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, glass());
        }

        inv.setItem(11, confirm());
        inv.setItem(15, cancel());
        return inv;
    }

    public static ItemStack glass() {
        ItemStack is = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§5");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack confirm() {
        ItemStack is = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta im = is.getItemMeta();
        //im.setDisplayName("§8§m   " + "§5 Ház építmény " + "§8§m   ");
        im.setDisplayName("§aMegerősítés");
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack cancel() {
        ItemStack is = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§cMégsem");
        is.setItemMeta(im);
        return is;
    }
}
