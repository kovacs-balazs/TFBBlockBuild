package me.koba1.tfbblockbuild.Listeners;

import me.koba1.tfbblockbuild.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(Main.placedBlocks.containsKey(e.getPlayer().getUniqueId()))
            Main.placedBlocks.remove(e.getPlayer().getUniqueId());
    }
}
