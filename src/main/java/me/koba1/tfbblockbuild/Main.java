package me.koba1.tfbblockbuild;

import me.koba1.tfbblockbuild.Commands.MainCommands;
import me.koba1.tfbblockbuild.Listeners.BlockPlace;
import me.koba1.tfbblockbuild.Listeners.InventoryClose;
import me.koba1.tfbblockbuild.Listeners.InventoryEvent;
import me.koba1.tfbblockbuild.Listeners.QuitEvent;
import me.koba1.tfbblockbuild.WorldEdit.Schematics;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public static HashMap<UUID, Block> placedBlocks;
    public static HashMap<Material, String> syncedMap;
    @Override
    public void onEnable() {
        placedBlocks = new HashMap<>();
        // Plugin startup logic
        setupCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setupCommands() {
        getCommand("tfbschematics").setExecutor(new MainCommands());
    }

    private void registerEvents() {
        PluginManager mng = getServer().getPluginManager();
        mng.registerEvents(new BlockPlace(), this);
        mng.registerEvents(new InventoryClose(), this);
        mng.registerEvents(new InventoryEvent(), this);
        mng.registerEvents(new QuitEvent(), this);
    }


    private void fillSynced() {
        syncedMap.put(Material.CHEST, "house1");
    }
}
