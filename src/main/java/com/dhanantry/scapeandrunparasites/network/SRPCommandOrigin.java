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
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class SRPCommandOrigin
implements ICommand {
    private final List aliases = new ArrayList();

    public SRPCommandOrigin() {
        this.aliases.add("srpvectors");
    }

    public int compareTo(ICommand arg0) {
        return 0;
    }

    public String func_71517_b() {
        return "srpvectors";
    }

    public String func_71518_a(ICommandSender sender) {
        return "srpvectors <text>";
    }

    public List<String> func_71514_a() {
        return this.aliases;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] argString) throws CommandException {
        World world = sender.func_130014_f_();
        if (!world.field_72995_K) {
            int a;
            int h;
            int z;
            int y;
            int x;
            if (!SRPConfigWorld.originActivated) {
                sender.func_145747_a((ITextComponent)new TextComponentString("Emerging Infestation Vector are not activated"));
                return;
            }
            if (argString.length == 0) {
                sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument"));
                return;
            }
            if (argString[0].equals("viewall")) {
                SRPWorldData data = SRPWorldData.get(world);
                ArrayList<Integer> coloniessX = data.getorigins("x");
                ArrayList<Integer> coloniessY = data.getorigins("y");
                ArrayList<Integer> coloniessZ = data.getorigins("z");
                ArrayList<Integer> coloniessA = data.getorigins("a");
                ArrayList<Integer> coloniessH = data.getorigins("h");
                String out = "Current Emerging Infestation Vectors in the world (x, y, z, health, radius): \n";
                for (int i = 0; i < coloniessX.size(); ++i) {
                    out = out + "-> [" + coloniessX.get(i) + ", " + coloniessY.get(i) + ", " + coloniessZ.get(i) + ", " + coloniessH.get(i) + ", " + coloniessA.get(i) + "] \n";
                }
                sender.func_145747_a((ITextComponent)new TextComponentString(out));
                return;
            }
            if (argString[0].equals("clearworld")) {
                SRPWorldData data = SRPWorldData.get(world);
                data.clearOriginList();
                sender.func_145747_a((ITextComponent)new TextComponentString("There are no longer Emerging Infestation Vectors in this world"));
                return;
            }
            if (argString.length != 6) {
                sender.func_145747_a((ITextComponent)new TextComponentString("Invalid argument."));
                sender.func_145747_a((ITextComponent)new TextComponentString("To set an Emerging Infestation Vector type [x y z health radius]."));
                return;
            }
            try {
                x = Integer.parseInt(argString[1]);
                y = Integer.parseInt(argString[2]);
                z = Integer.parseInt(argString[3]);
                h = Integer.parseInt(argString[4]);
                a = Integer.parseInt(argString[5]);
            }
            catch (NumberFormatException nfe) {
                sender.func_145747_a((ITextComponent)new TextComponentString("Invalid arguments"));
                return;
            }
            if (argString[0].equals("setvector")) {
                switch (ParasiteEventWorld.placeOriginInWorld(world, new BlockPos(x, y, z), h, a)) {
                    case 1: {
                        sender.func_145747_a((ITextComponent)new TextComponentString("Emerging Infestation Vector placed at " + x + " " + y + " " + z + " with " + h + " health and " + a + " radius"));
                        break;
                    }
                    case 2: {
                        sender.func_145747_a((ITextComponent)new TextComponentString("Emerging Infestation Vector type Outbreak placed at " + x + " " + y + " " + z + " with " + h + " health and " + a + " radius"));
                        break;
                    }
                    case 3: {
                        sender.func_145747_a((ITextComponent)new TextComponentString("Emerging Infestation Vectors are not activated"));
                        break;
                    }
                    case 6: {
                        sender.func_145747_a((ITextComponent)new TextComponentString("Emerging Infestation Vector too close to another EIV "));
                        break;
                    }
                    case 7: {
                        sender.func_145747_a((ITextComponent)new TextComponentString("Maximum number of Emerging Infestation Vector reached "));
                        break;
                    }
                    default: {
                        sender.func_145747_a((ITextComponent)new TextComponentString("Unknown - EIV "));
                    }
                }
                return;
            }
            if (argString[0].equals("removevector")) {
                if (ParasiteEventWorld.removeOriginInWorld(world, new BlockPos(x, y, z))) {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Emerging Infestation Vector removed at " + x + " " + y + " " + z));
                } else {
                    sender.func_145747_a((ITextComponent)new TextComponentString("Emerging Infestation Vector cannot be removed at " + x + " " + y + " " + z));
                }
                return;
            }
        }
    }

    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return sender.func_70003_b(2, this.func_71517_b());
    }

    public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        ArrayList<String> atm = new ArrayList<String>();
        if (args.length == 1) {
            atm.add("viewall");
            atm.add("clearworld");
            atm.add("setvector");
            atm.add("removevector");
        }
        if ((args[0].equals("setvector") || args[0].equals("removevector")) && args.length == 2) {
            atm.add("" + sender.func_180425_c().func_177958_n());
        }
        if (args.length == 3) {
            atm.add("" + sender.func_180425_c().func_177956_o());
        }
        if (args.length == 4) {
            atm.add("" + sender.func_180425_c().func_177952_p());
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

