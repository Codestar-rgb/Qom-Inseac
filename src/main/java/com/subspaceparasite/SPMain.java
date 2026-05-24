/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.SidedProxy
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLServerStartingEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  org.apache.logging.log4j.Logger
 */
package com.subspaceparasite;

import com.subspaceparasite.proxy.CommonProxy;
import com.subspaceparasite.util.SPCreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;

@Mod(modid="subspaceparasite", name="Subspace Parasite", version="1.10.5", dependencies="before:jeid@[2.2.5,)")
public class SPMain {
    @Mod.Instance
    public static SPMain instance;
    @SidedProxy(clientSide="com.subspaceparasite.proxy.ClientProxy", serverSide="com.subspaceparasite.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static final SPCreativeTabs SP_CREATIVETAB;
    public static Logger logger;
    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void Postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void Serverinit(FMLServerStartingEvent event) {
        proxy.serverInit(event);
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation("subspaceparasite", path);
    }

    static {
        SP_CREATIVETAB = new SPCreativeTabs("SParasites");
        FluidRegistry.enableUniversalBucket();
    }
}

