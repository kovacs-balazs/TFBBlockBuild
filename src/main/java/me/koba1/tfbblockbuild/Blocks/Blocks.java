package me.koba1.tfbblockbuild.Blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;

public class Blocks {

    public static ItemStack house1() {
        ItemStack is = new ItemStack(Material.CHEST);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§8§m    " + "§5 Ház építmény " + "§8§m    ");
        //im.addEnchant(Enchantment.DURABILITY, 10, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.setLore(Arrays.asList(
                "§5",
                "§7Block lerakása után egy ház fog a chestből épülni")
        );
        is.setItemMeta(im);
        is.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        return is;
    }
}
