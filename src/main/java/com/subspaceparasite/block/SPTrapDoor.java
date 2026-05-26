/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockTrapDoor
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class SPTrapDoor
extends BlockTrapDoor {
    public SPTrapDoor(Material material, String regName, String modid) {
        super(material);
        this.setRegistryName(regName);
        this.func_149663_c(modid + "." + regName);
        this.func_149711_c(3.0f);
        this.func_149672_a(material == Material.field_151575_d ? SoundType.field_185848_a : SoundType.field_185852_e);
        SPBlocks.SP_BLOCKS.add((Block)this);
        Item item = ((Item)new ItemBlock((Block)this).setRegistryName(this.getRegistryName())).func_77655_b(modid + "." + regName).func_77637_a((CreativeTabs)SPMain.SP_CREATIVETAB);
        SPItems.SP_ITEMS.add(item);
    }

    public SPTrapDoor(Material material, String regName) {
        this(material, regName, "subspaceparasite");
    }

    public SPTrapDoor(String regName) {
        this(Material.field_151575_d, regName, "subspaceparasite");
    }
}

