/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.world.GameType
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.network;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.network.SPPacketGuiDistortionState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class SPCommandGuiDistortion
implements ICommand {
    private final List<String> aliases = new ArrayList<String>();

    public SPCommandGuiDistortion() {
        this.aliases.add("srpguidist");
        this.aliases.add("srpdistortion");
    }

    public int compareTo(ICommand other) {
        return this.func_71517_b().compareTo(other.func_71517_b());
    }

    public String func_71517_b() {
        return "srpguidistortion";
    }

    public String func_71518_a(ICommandSender sender) {
        return "commands.subspaceparasite.guidistortion.usage";
    }

    public List<String> func_71514_a() {
        return this.aliases;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.guidistortion.usage.text", new Object[]{"/" + this.func_71517_b() + " <on|off|toggle|status> [player|all]"}));
            return;
        }
        String mode = args[0].toLowerCase();
        if (!("on".equals(mode) || "off".equals(mode) || "toggle".equals(mode) || "status".equals(mode))) {
            sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.guidistortion.invalid_argument", new Object[0]));
            return;
        }
        List<EntityPlayerMP> targets = this.getTargets(server, sender, args);
        if (targets.isEmpty()) {
            return;
        }
        for (EntityPlayerMP target : targets) {
            this.handleTarget(sender, target, mode, targets.size() > 1);
        }
        if (targets.size() > 1 && !"status".equals(mode)) {
            sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.guidistortion.set_all", new Object[]{targets.size()}));
        }
    }

    private List<EntityPlayerMP> getTargets(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length >= 2) {
            String targetName = args[1];
            if ("all".equalsIgnoreCase(targetName) || "@a".equalsIgnoreCase(targetName)) {
                return new ArrayList<EntityPlayerMP>(server.func_184103_al().func_181057_v());
            }
            EntityPlayerMP target = server.func_184103_al().func_152612_a(targetName);
            if (target == null) {
                sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.guidistortion.player_not_found", new Object[]{targetName}));
                return Collections.emptyList();
            }
            ArrayList<EntityPlayerMP> one = new ArrayList<EntityPlayerMP>();
            one.add(target);
            return one;
        }
        if (sender.func_174793_f() instanceof EntityPlayerMP) {
            ArrayList<EntityPlayerMP> one = new ArrayList<EntityPlayerMP>();
            one.add((EntityPlayerMP)sender.func_174793_f());
            return one;
        }
        sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.guidistortion.console_requires_player", new Object[0]));
        return Collections.emptyList();
    }

    private void handleTarget(ICommandSender sender, EntityPlayerMP target, String mode, boolean multiTarget) {
        boolean newDisabled;
        boolean effectivelyDisabled;
        NBTTagCompound persisted = SPCommandGuiDistortion.getPersistedData(target);
        boolean currentlyDisabled = persisted.func_74767_n("SPGuiDistortionDisabled");
        boolean creativeOverride = persisted.func_74767_n("SPGuiDistortionCreativeOverride");
        boolean creativeOrSpectator = SPCommandGuiDistortion.isCreativeOrSpectator(target);
        boolean bl = effectivelyDisabled = currentlyDisabled || creativeOrSpectator && !creativeOverride;
        if ("status".equals(mode)) {
            sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.guidistortion.status.detailed", new Object[]{target.func_70005_c_(), currentlyDisabled ? new TextComponentTranslation("commands.subspaceparasite.guidistortion.state.disabled", new Object[0]) : new TextComponentTranslation("commands.subspaceparasite.guidistortion.state.enabled", new Object[0]), creativeOverride ? new TextComponentTranslation("commands.subspaceparasite.guidistortion.creative_override.enabled", new Object[0]) : new TextComponentTranslation("commands.subspaceparasite.guidistortion.creative_override.disabled", new Object[0]), creativeOrSpectator ? new TextComponentTranslation("commands.subspaceparasite.guidistortion.gamemode.creative_or_spectator", new Object[0]) : new TextComponentTranslation("commands.subspaceparasite.guidistortion.gamemode.normal", new Object[0])}));
            return;
        }
        boolean newCreativeOverride = creativeOverride;
        if ("on".equals(mode)) {
            newDisabled = false;
            if (creativeOrSpectator) {
                newCreativeOverride = true;
            }
        } else if ("off".equals(mode)) {
            newDisabled = true;
            newCreativeOverride = false;
        } else if (effectivelyDisabled) {
            newDisabled = false;
            if (creativeOrSpectator) {
                newCreativeOverride = true;
            }
        } else {
            newDisabled = true;
            newCreativeOverride = false;
        }
        persisted.func_74757_a("SPGuiDistortionDisabled", newDisabled);
        persisted.func_74757_a("SPGuiDistortionCreativeOverride", newCreativeOverride);
        SPMain.network.sendTo((IMessage)new SPPacketGuiDistortionState(newDisabled, newCreativeOverride), target);
        if (!multiTarget) {
            sender.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.guidistortion.set_other", new Object[]{target.func_70005_c_(), newDisabled ? new TextComponentTranslation("commands.subspaceparasite.guidistortion.state.disabled", new Object[0]) : new TextComponentTranslation("commands.subspaceparasite.guidistortion.state.enabled", new Object[0])}));
        }
        if (target != sender) {
            target.func_145747_a((ITextComponent)new TextComponentTranslation("commands.subspaceparasite.guidistortion.set_self", new Object[]{newDisabled ? new TextComponentTranslation("commands.subspaceparasite.guidistortion.state.disabled", new Object[0]) : new TextComponentTranslation("commands.subspaceparasite.guidistortion.state.enabled", new Object[0])}));
        }
    }

    private static NBTTagCompound getPersistedData(EntityPlayerMP player) {
        NBTTagCompound persisted;
        NBTTagCompound entityData = player.getEntityData();
        if (entityData.func_150297_b("PlayerPersisted", 10)) {
            persisted = entityData.func_74775_l("PlayerPersisted");
        } else {
            persisted = new NBTTagCompound();
            entityData.func_74782_a("PlayerPersisted", (NBTBase)persisted);
        }
        return persisted;
    }

    private static boolean isCreativeOrSpectator(EntityPlayerMP player) {
        if (player == null) {
            return false;
        }
        if (player.func_175149_v()) {
            return true;
        }
        if (player.field_71134_c != null) {
            GameType type = player.field_71134_c.func_73081_b();
            return type == GameType.CREATIVE || type == GameType.SPECTATOR;
        }
        return false;
    }

    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return sender.func_70003_b(2, this.func_71517_b());
    }

    public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        ArrayList<String> out = new ArrayList<String>();
        if (args.length == 1) {
            out.add("on");
            out.add("off");
            out.add("toggle");
            out.add("status");
            return CommandBase.func_175762_a((String[])args, out);
        }
        if (args.length == 2) {
            String[] names;
            out.add("all");
            out.add("@a");
            for (String name : names = server.func_71213_z()) {
                out.add(name);
            }
            return CommandBase.func_175762_a((String[])args, out);
        }
        return out;
    }

    public boolean func_82358_a(String[] args, int index) {
        return index == 1;
    }
}

