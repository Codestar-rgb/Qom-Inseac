/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.client.ClientCommandHandler
 *  net.minecraftforge.client.IClientCommand
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.client.shader;

import com.subspaceparasite.client.fog.SPFogManager;
import com.subspaceparasite.client.shader.BreatheShaderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.IClientCommand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class BreatheShaderCommand
extends CommandBase
implements IClientCommand {
    public String func_71517_b() {
        return "srp_breathe";
    }

    public String func_71518_a(ICommandSender sender) {
        return "/srp_breathe <on|off|toggle>";
    }

    public int func_82362_a() {
        return 0;
    }

    public boolean allowUsageWithoutPrefix(ICommandSender sender, String message) {
        return false;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) {
        boolean enable;
        String mode;
        switch (mode = args.length == 0 ? "toggle" : args[0].toLowerCase()) {
            case "on": {
                enable = true;
                break;
            }
            case "off": {
                enable = false;
                break;
            }
            default: {
                boolean bl = enable = Minecraft.func_71410_x().field_71460_t.func_147706_e() == null;
            }
        }
        if (enable) {
            BreatheShaderManager.get().enable();
            SPFogManager.get().enable();
            sender.func_145747_a((ITextComponent)new TextComponentString("[SRP] Breathe shader: ON"));
        } else {
            BreatheShaderManager.get().disable();
            SPFogManager.get().disable();
            sender.func_145747_a((ITextComponent)new TextComponentString("[SRP] Breathe shader: OFF"));
        }
    }

    public static void register() {
        ClientCommandHandler.instance.func_71560_a((ICommand)new BreatheShaderCommand());
    }
}

