/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.World
 *  org.apache.logging.log4j.Level
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBiomes;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSpawning;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

public class SRPCommandRoot
implements ICommand {
    private final List aliases = new ArrayList();

    public SRPCommandRoot() {
        this.aliases.add("srparasites");
    }

    public int compareTo(ICommand arg0) {
        return 0;
    }

    public String func_71517_b() {
        return "srparasites";
    }

    public String func_71518_a(ICommandSender sender) {
        return "srparasites <text>";
    }

    public List<String> func_71514_a() {
        return this.aliases;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] argString) throws CommandException {
        World world = sender.func_130014_f_();
        if (!world.field_72995_K) {
            if (argString[0].equals("setgeneration")) {
                try {
                    String nani = argString[1];
                    int n = Integer.parseInt(nani);
                }
                catch (NumberFormatException nfe) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid arg"));
                    return;
                }
                SRPSaveData data = SRPSaveData.get(world, 68);
                int id = world.field_73011_w.getDimension();
                String nani = argString[1];
                int option = Integer.parseInt(nani);
                if (option > 5 || option < 0) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Number too low or too high, please enter a number between 0 - 5"));
                    return;
                }
                data.setGeneration((byte)option, id);
                data.setGenerationTime((int)world.field_73011_w.getWorldTime(), id);
                sender.func_145747_a((ITextComponent)new TextComponentString("Parasite Generation set to " + option));
                return;
            }
            if (argString[0].equals("getgeneration")) {
                sender.func_145747_a((ITextComponent)new TextComponentString("Current Parasite Generation " + SRPSaveData.get(world, 69).getGeneration(world.field_73011_w.getDimension())));
                return;
            }
            if (argString[0].equals("spawnmeteor")) {
                int rad;
                int z;
                int y;
                int x;
                try {
                    x = Integer.parseInt(argString[1]);
                    y = Integer.parseInt(argString[2]);
                    z = Integer.parseInt(argString[3]);
                    rad = Integer.parseInt(argString[4]);
                }
                catch (NumberFormatException nfe) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Invalid/Missing argument"));
                    return;
                }
                ParasiteSummon.spawnMeteor(x, y, z, rad, rad, world);
            } else {
                if (argString[0].equals("readconfigurationfile")) {
                    try {
                        if (SRPConfig.readConfig() && SRPConfigMobs.readConfig() && SRPConfigSystems.readConfig() && SRPConfigWorld.readConfig()) {
                            SRPAttributes.reset();
                            SRPAttributes.init();
                            SRPBlocks.init();
                            SRPBiomes.clearMobSpawnList();
                            SRPSpawning.initBiome();
                            SRPSpawning.removeInit();
                            SRPSpawning.init();
                            sender.func_145747_a((ITextComponent)new TextComponentString("Configutarion files were read successfully \n NOTE: Does not work for all options, such as registry or client-side options"));
                        }
                    }
                    catch (Exception e) {
                        SRPMain.logger.log(Level.ERROR, "Problem while reading configuration file", (Throwable)e);
                        sender.func_145747_a((ITextComponent)new TextComponentString("There was a problem while reading configuration file, check inputs \n NOTE: Does not work for all options, such as registry or client-side options"));
                    }
                    return;
                }
                if (argString[0].equals("toggle_dotiledrops")) {
                    boolean current;
                    SRPConfig.doTileDrops = current = !SRPConfig.doTileDrops;
                    sender.func_145747_a((ITextComponent)new TextComponentString("Current doTileDrop value is " + current));
                    return;
                }
                if (argString[0].equals("toggle_domobevolution")) {
                    boolean current;
                    ParasiteEventEntity.canSpawnNext = current = !ParasiteEventEntity.canSpawnNext;
                    sender.func_145747_a((ITextComponent)new TextComponentString("Current doMobEvolution value is " + current));
                    return;
                }
                if (argString[0].equals("resetdatafile")) {
                    SRPWorldData data = SRPWorldData.get(world);
                    data.resetInstance(world);
                    sender.func_145747_a((ITextComponent)new TextComponentString("Data file of this dimension has been reset"));
                    return;
                }
                if (argString[0].equals("parasites")) {
                    List serverList = world.field_72996_f;
                    int count = 0;
                    for (int x = 0; x < serverList.size(); ++x) {
                        if (!(serverList.get(x) instanceof EntityParasiteBase)) continue;
                        ++count;
                    }
                    int players = world.field_73010_i.size();
                    players *= SRPConfig.worldMobCapPlusPlayer;
                    SRPSaveData data = SRPSaveData.get(world, 70);
                    int id = world.field_73011_w.getDimension();
                    String[] here = new String[20];
                    here = data.getCurrentCodeU(id).split(";");
                    String atm = "";
                    for (int k = 0; k < here.length; ++k) {
                        atm = k == here.length - 1 ? atm + here[k] : atm + here[k] + " ";
                    }
                    sender.func_145747_a((ITextComponent)new TextComponentString(" ====== \n -> Dislodgment code: \n" + atm + " \n -> Current Parasite Mob Cap: " + (SRPConfig.worldMobCap + players) + " \n -> Number of current parasites: " + count));
                    return;
                }
            }
        }
    }

    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return sender.func_70003_b(2, this.func_71517_b());
    }

    public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        ArrayList<String> atm = new ArrayList<String>();
        if (args.length == 1) {
            atm.add("parasites");
            atm.add("spawnmeteor");
            atm.add("readconfigurationfile");
            atm.add("resetdatafile");
            atm.add("toggle_dotiledrops");
            atm.add("toggle_domobevolution");
            atm.add("setgeneration");
            atm.add("getgeneration");
        }
        if (args[0].equals("spawnmeteor") && args.length == 2) {
            atm.add("" + sender.func_180425_c().func_177958_n());
        }
        if (args.length == 3) {
            atm.add("" + sender.func_180425_c().func_177956_o());
        }
        if (args.length == 4) {
            atm.add("" + sender.func_180425_c().func_177952_p());
        }
        if (args.length == 5) {
            atm.add("" + new Random().nextInt(130));
        }
        if (args.length == 1) {
            return CommandBase.func_175762_a((String[])args, atm);
        }
        return atm;
    }

    public boolean func_82358_a(String[] args, int index) {
        return false;
    }
}

