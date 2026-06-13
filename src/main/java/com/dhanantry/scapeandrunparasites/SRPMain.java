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
package com.dhanantry.scapeandrunparasites;

import com.dhanantry.scapeandrunparasites.proxy.CommonProxy;
import com.dhanantry.scapeandrunparasites.util.SRPCreativeTabs;
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

@Mod(modid="srparasites", name="Scape and Run Parasites", version="1.10.6", dependencies="before:jeid@[2.2.5,)")
public class SRPMain {
    @Mod.Instance
    public static SRPMain instance;
    @SidedProxy(clientSide="com.dhanantry.scapeandrunparasites.proxy.ClientProxy", serverSide="com.dhanantry.scapeandrunparasites.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static final SRPCreativeTabs SRP_CREATIVETAB;
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
        return new ResourceLocation("srparasites", path);
    }

    static {
        SRP_CREATIVETAB = new SRPCreativeTabs("SRParasites");
        FluidRegistry.enableUniversalBucket();
    }
}

