package me.koba1.tfbblockbuild.WorldEdit;

import com.fastasyncworldedit.core.FaweAPI;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.util.io.Closer;
import com.sk89q.worldedit.world.DataException;
import me.koba1.tfbblockbuild.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import javax.xml.validation.Schema;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Schematics {
    private Main m = Main.getPlugin(Main.class);

    Clipboard clipboard;
    File schematic;

    public Schematics(String schematicName) {
        Plugin WE = Bukkit.getPluginManager().getPlugin("FastAsyncWorldEdit");
        this.schematic = new File(WE.getDataFolder() + "\\schematics\\" + schematicName + ".schem");

        ClipboardFormat format = ClipboardFormats.findByFile(this.schematic);
        try(ClipboardReader reader = format.getReader(new FileInputStream(this.schematic))) {
            this.clipboard = reader.read();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        /*this.schematic.mkdirs();
        if (!this.schematic.exists()) {
            try {
                this.schematic.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.name = schematicName;
    }

    public String getName() {
        return name;
    }*/

    public void loadSchematic(Block placedBlock, Player player) {
        BlockVector3 to = BlockVector3.at(
                placedBlock.getX(),
                placedBlock.getY(),
                placedBlock.getZ()
        );

        com.sk89q.worldedit.world.World world = (com.sk89q.worldedit.world.World) FaweAPI.getWorld(placedBlock.getWorld().getName());

        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {

            double transform = 180;

            if (dir(player).equalsIgnoreCase("S")) {
                transform = 0;
            } else if (dir(player).equalsIgnoreCase("E")) {
                transform = 90;
            } else if (dir(player).equalsIgnoreCase("W")) {
                transform = 270;
            }

            ClipboardHolder holder = new ClipboardHolder(this.clipboard);
            holder.setTransform(new AffineTransform().rotateY(transform));

            Operation operation = holder
                    .createPaste(editSession)
                    .to(to)
                    .ignoreAirBlocks(true)
                    .build();
            Operations.completeBlindly(operation);
        }
    }

    public static String dir(Player player) {
        double y = player.getLocation().getYaw();
        String direction = new String();

        if(y >= 135 || y < -135){
            direction = "N";
        }
        if(y >= -135 && y < -45){
            direction = "E";
        }
        if(y >= -45 && y < 45){
            direction = "S";
        }
        if(y >= 45 && y < 135){
            direction = "W";
        }
        return direction;
    }
}
