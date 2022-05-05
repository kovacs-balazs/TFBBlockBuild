package me.koba1.tfbblockbuild.Commands;

import me.koba1.tfbblockbuild.Blocks.Blocks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(cmd.getName().equalsIgnoreCase("tfbschematics")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                p.getInventory().addItem(Blocks.house1());
                if(sender instanceof Player){

                    double y = p.getLocation().getYaw();
                    String direction = new String();

                    if(y >= 135 || y < -135){
                        direction = "North";
                    }
                    if(y >= -135 && y < -45){
                        direction = "East";
                    }
                    if(y >= -45 && y < 45){
                        direction = "South";
                    }
                    if(y >= 45 && y < 135){
                        direction = "West";
                    }

                    p.sendMessage(String.valueOf(p.getLocation().getYaw()));
                    p.sendMessage(direction);

                    return true;

                }
                return false;
            }
        }
        return false;
    }
}
