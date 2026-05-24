/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.material.Material
 */
package com.subspaceparasite.block.slabs;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.IMetaName;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public abstract class BlockSlabBase
extends BlockSlab
implements IMetaName {
    public BlockSlabBase(Material materialIn, String name, float hardness, boolean creative, boolean tickRandom, BlockSlab half) {
        super(materialIn);
        this.setRegistryName(name);
        this.func_149663_c("subspaceparasite." + name);
        this.func_149711_c(hardness);
        if (creative) {
            this.func_149647_a(SPMain.SP_CREATIVETAB);
        }
        this.field_149783_u = !this.func_176552_j();
    }

    public abstract BlockSlab getHalfBlock();

    public abstract BlockSlab getDoubleBlock();
}

