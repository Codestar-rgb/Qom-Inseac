package com.dhanantry.scapeandrunparasites;

import com.dhanantry.scapeandrunparasites.compatibility.ModCompatibility;
import com.dhanantry.scapeandrunparasites.proxy.CommonProxy;
import com.dhanantry.scapeandrunparasites.util.SRPCreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;

@Mod(modid = "srparasites", name = "Scape and Run Parasites", version = "1.10.7", dependencies = "before:jeid@[2.2.5,)")
public class SRPMain {
   @Instance
   public static SRPMain instance;
   @SidedProxy(clientSide = "com.dhanantry.scapeandrunparasites.proxy.ClientProxy", serverSide = "com.dhanantry.scapeandrunparasites.proxy.CommonProxy")
   public static CommonProxy proxy;
   public static final SRPCreativeTabs SRP_CREATIVETAB = new SRPCreativeTabs("SRParasites");
   public static Logger logger;
   public static SimpleNetworkWrapper network;

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      logger = event.getModLog();
      proxy.preInit(event);
      ModCompatibility.FLUIDLOGGED_API = Loader.isModLoaded("fluidlogged_api");
   }

   @EventHandler
   public void Init(FMLInitializationEvent event) {
      proxy.init(event);
   }

   @EventHandler
   public void Postinit(FMLPostInitializationEvent event) {
      proxy.postInit(event);
   }

   @EventHandler
   public void Serverinit(FMLServerStartingEvent event) {
      proxy.serverInit(event);
   }

   public static ResourceLocation rl(String path) {
      return new ResourceLocation("srparasites", path);
   }

   static {
      FluidRegistry.enableUniversalBucket();
   }
}
