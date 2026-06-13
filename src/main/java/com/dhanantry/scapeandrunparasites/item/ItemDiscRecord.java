/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockJukebox
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemRecord
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.network.SRPPacketMovingSound;
import java.util.List;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDiscRecord
extends ItemRecord {
    protected byte version;
    private final SoundEvent sound;

    public ItemDiscRecord(String name, int maxStack, byte id, SoundEvent soundIn) {
        super(name, soundIn);
        this.setRegistryName(name);
        this.func_77655_b("srparasites." + name);
        this.func_77637_a(SRPMain.SRP_CREATIVETAB);
        this.sound = soundIn;
        this.field_77777_bU = maxStack;
        this.version = id;
    }

    public EnumActionResult func_180614_a(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = worldIn.func_180495_p(pos);
        if (iblockstate.func_177230_c() == Blocks.field_150421_aI && !((Boolean)iblockstate.func_177229_b((IProperty)BlockJukebox.field_176432_a)).booleanValue()) {
            if (!worldIn.field_72995_K) {
                SRPMain.network.sendToDimension((IMessage)new SRPPacketMovingSound(104), worldIn.field_73011_w.getDimension());
                ItemStack itemstack = player.func_184586_b(hand);
                ((BlockJukebox)Blocks.field_150421_aI).func_176431_a(worldIn, pos, iblockstate, itemstack);
                worldIn.func_180498_a((EntityPlayer)null, 1010, pos, Item.func_150891_b((Item)this));
                itemstack.func_190918_g(1);
                player.func_71029_a(StatList.field_188092_Z);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.func_150927_i());
    }
}

