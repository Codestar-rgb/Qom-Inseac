/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.IMetaName;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.world.SPSaveData;
import java.util.List;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBase
extends Block {
    public BlockBase(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
        super(material);
        this.setRegistryName(name);
        this.func_149663_c("subspaceparasite." + name);
        this.func_149711_c(hardness);
        this.func_149675_a(tickRandom);
        if (creative) {
            this.func_149647_a(SPMain.SP_CREATIVETAB);
        }
        SPBlocks.SP_BLOCKS.add(this);
        ItemBlock itemBlock = this instanceof IMetaName ? ((IMetaName)((Object)this)).getItemBlock() : new ItemBlock((Block)this);
        SPItems.SP_ITEMS.add((Item)itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    public BlockBase(Material material, String name, float hardness, boolean creative, boolean tickRandom, float resistance) {
        this(material, name, hardness, creative, tickRandom);
        this.func_149752_b(resistance);
    }

    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (!world.field_72995_K) {
            int goo;
            ParasiteEventWorld.setDisloWorldPhase(world, SPAttributes.EVENTPARABLOCKBR, SPConfigSystems.chanceEventParaBlockB, 0, null);
            SPSaveData dataLol = SPSaveData.get(world, 1);
            int n = goo = SPConfigSystems.disloParasiteBlock ? dataLol.getCurrentCode(world.field_73011_w.getDimension(), 25) : 0;
            if (goo != 0 && world.field_73012_v.nextDouble() < SPConfigSystems.disloParasiteBlockChance) {
                List serverList = world.field_72996_f;
                int count = 0;
                for (int x = 0; x < serverList.size(); ++x) {
                    if (!(serverList.get(x) instanceof EntityParasiteBase)) continue;
                    ++count;
                }
                if (count > SPConfig.worldMobCap) {
                    count = 0;
                    return super.removedByPlayer(state, world, pos, player, willHarvest);
                }
                EntityParasiteBase halo = ParasiteEventEntity.getRandomFeral(world);
                if (goo >= SPConfigSystems.disloParasiteBlockValue1) {
                    halo = ParasiteEventEntity.getRandomPrimitive(world);
                }
                if (goo >= SPConfigSystems.disloParasiteBlockValue2) {
                    halo = ParasiteEventEntity.getRandomAdapted(world);
                }
                if (goo >= SPConfigSystems.disloParasiteBlockValue3) {
                    halo = ParasiteEventEntity.getRandomPure(world);
                }
                halo.func_70107_b((double)pos.func_177958_n() + 0.5, pos.func_177956_o(), (double)pos.func_177952_p() + 0.5);
                halo.func_180482_a(world.func_175649_E(new BlockPos((Entity)halo)), null);
                world.func_72838_d((Entity)halo);
                world.func_180498_a(null, 1026, new BlockPos((Entity)halo), 0);
                halo.particleStatus((byte)7);
                halo.func_70690_d(new PotionEffect(SPPotions.EPEL_E, 600, 0, false, false));
            }
        }
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    public boolean func_189872_a(IBlockState state, Entity entityIn) {
        if (!(entityIn instanceof EntityParasiteBase)) {
            return false;
        }
        return super.func_189872_a(state, entityIn);
    }
}

