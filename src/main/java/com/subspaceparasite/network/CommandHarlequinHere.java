/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.SPacketChunkData
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.PlayerChunkMap
 *  net.minecraft.server.management.PlayerChunkMapEntry
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.Biome
 *  net.minecraft.world.chunk.Chunk
 */
package com.subspaceparasite.network;

import com.subspaceparasite.init.SPBiomes;
import java.lang.reflect.Method;
import java.util.Arrays;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerChunkMap;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

public class CommandHarlequinHere
extends CommandBase {
    public String func_71517_b() {
        return "harlequin_here";
    }

    public String func_71518_a(ICommandSender sender) {
        return "/harlequin_here [radiusChunks=1]";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP p = CommandHarlequinHere.func_71521_c((ICommandSender)sender);
        WorldServer world = p.func_71121_q();
        if (SPBiomes.biomeHarlequin == null) {
            throw new CommandException("Harlequin biome is not registered (SPBiomes.biomeHarlequin is null).", new Object[0]);
        }
        int r = args.length >= 1 ? Math.max(0, CommandHarlequinHere.func_175755_a((String)args[0])) : 1;
        int baseChunkX = p.func_180425_c().func_177958_n() >> 4;
        int baseChunkZ = p.func_180425_c().func_177952_p() >> 4;
        byte id = (byte)Biome.func_185362_a((Biome)SPBiomes.biomeHarlequin);
        int changed = 0;
        for (int dx = -r; dx <= r; ++dx) {
            for (int dz = -r; dz <= r; ++dz) {
                Chunk c = world.func_72964_e(baseChunkX + dx, baseChunkZ + dz);
                Arrays.fill(c.func_76605_m(), id);
                c.func_76630_e();
                CommandHarlequinHere.sendChunkBiomeUpdate(world, c);
                c.func_76613_n();
                ++changed;
            }
        }
        sender.func_145747_a((ITextComponent)new TextComponentString("Applied Harlequin to " + changed + " chunk(s) in a " + (2 * r + 1) + "x" + (2 * r + 1) + " square."));
        Biome bNow = world.func_180494_b(p.func_180425_c());
        String bName = bNow.getRegistryName() != null ? bNow.getRegistryName().toString() : bNow.func_185359_l();
        sender.func_145747_a((ITextComponent)new TextComponentString("Server biome at your position now: " + bName));
    }

    private static void sendChunkBiomeUpdate(WorldServer world, Chunk c) {
        PlayerChunkMap pcm = world.func_184164_w();
        try {
            Method m = pcm.getClass().getMethod("markBlockRangeForSend", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
            m.invoke((Object)pcm, c.field_76635_g << 4, 0, c.field_76647_h << 4, (c.field_76635_g << 4) + 15, 255, (c.field_76647_h << 4) + 15);
            return;
        }
        catch (NoSuchMethodException m) {
        }
        catch (Throwable m) {
            // empty catch block
        }
        PlayerChunkMapEntry entry = pcm.func_187301_b(c.field_76635_g, c.field_76647_h);
        if (entry != null) {
            SPacketChunkData pkt = new SPacketChunkData(c, 65535);
            entry.func_187267_a((Packet)pkt);
        }
    }
}

