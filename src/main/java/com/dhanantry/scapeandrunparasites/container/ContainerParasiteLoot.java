/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IContainerListener
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 */
package com.dhanantry.scapeandrunparasites.container;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityParasiteLoot;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class ContainerParasiteLoot
extends Container {
    private final TileEntityParasiteLoot te;
    private int interactionPulse = 0;
    public int clientPulse = 0;
    private static final float MIN_DAMAGE = 0.5f;
    private static final float MAX_DAMAGE = 8.0f;
    private static final DamageSource PARASITIC = DamageSource.field_76376_m;

    public ContainerParasiteLoot(InventoryPlayer playerInv, final TileEntityParasiteLoot te, EntityPlayer player) {
        int c;
        int r;
        this.te = te;
        int idx = 0;
        for (r = 0; r < 3; ++r) {
            for (c = 0; c < 9; ++c) {
                this.func_75146_a(new Slot(te, idx++, 8 + c * 18, 30 + r * 18){

                    public ItemStack func_190901_a(EntityPlayer p, ItemStack stack) {
                        float f = te.getFullness();
                        float dmg = 0.5f + 7.5f * (1.0f - f);
                        if (!p.field_70170_p.field_72995_K) {
                            ContainerParasiteLoot.this.interactionPulse = ContainerParasiteLoot.this.interactionPulse + 1 & Short.MAX_VALUE;
                            if (SRPConfigWorld.parasiteLootDamageOnTake && dmg > 0.0f) {
                                p.func_70097_a(PARASITIC, dmg);
                            }
                            ContainerParasiteLoot.this.applyParasiteEffects(p, f);
                        }
                        return super.func_190901_a(p, stack);
                    }
                });
            }
        }
        for (r = 0; r < 3; ++r) {
            for (c = 0; c < 9; ++c) {
                this.func_75146_a(new Slot((IInventory)playerInv, c + r * 9 + 9, 8 + c * 18, 88 + r * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)playerInv, i, 8 + i * 18, 146));
        }
    }

    public boolean func_75145_c(EntityPlayer playerIn) {
        return this.te.func_70300_a(playerIn);
    }

    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        ItemStack ret = ItemStack.field_190927_a;
        Slot slot = (Slot)this.field_75151_b.get(index);
        if (slot != null && slot.func_75216_d()) {
            ItemStack in = slot.func_75211_c();
            ret = in.func_77946_l();
            boolean teStart = false;
            int teEnd = 27;
            int invStart = 27;
            int invEnd = 54;
            int hotStart = 54;
            int hotEnd = 63;
            if (index < 27) {
                float fBefore = this.te.getFullness();
                if (!this.func_75135_a(in, 27, 63, true)) {
                    return ItemStack.field_190927_a;
                }
                if (!playerIn.field_70170_p.field_72995_K) {
                    float dmg;
                    if (SRPConfigWorld.parasiteLootDamageOnTake && (dmg = 0.5f + 7.5f * (1.0f - fBefore)) > 0.0f) {
                        playerIn.func_70097_a(PARASITIC, dmg);
                    }
                    if (fBefore < 1.0f) {
                        int viralDur;
                        int viralAmp;
                        int corrosDur;
                        int corrosAmp;
                        if (fBefore < 0.25f) {
                            corrosAmp = 1;
                            corrosDur = 400;
                            viralAmp = 3;
                            viralDur = 600;
                        } else if (fBefore < 0.5f) {
                            corrosAmp = 1;
                            corrosDur = 200;
                            viralAmp = 2;
                            viralDur = 400;
                        } else if (fBefore < 0.75f) {
                            corrosAmp = 0;
                            corrosDur = 200;
                            viralAmp = 1;
                            viralDur = 200;
                        } else {
                            corrosAmp = 0;
                            corrosDur = 100;
                            viralAmp = 1;
                            viralDur = 100;
                        }
                        playerIn.func_70690_d(new PotionEffect(SRPPotions.CORRO_E, corrosDur, corrosAmp, false, false));
                        SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)playerIn, viralDur, viralAmp);
                    }
                    this.interactionPulse = this.interactionPulse + 1 & Short.MAX_VALUE;
                }
            } else if (index < 54) {
                if (!this.func_75135_a(in, 0, 27, false)) {
                    if (!this.func_75135_a(in, 54, 63, false)) {
                        return ItemStack.field_190927_a;
                    }
                } else if (!playerIn.field_70170_p.field_72995_K) {
                    this.interactionPulse = this.interactionPulse + 1 & Short.MAX_VALUE;
                }
            } else if (index < 63) {
                if (!this.func_75135_a(in, 0, 27, false)) {
                    if (!this.func_75135_a(in, 27, 54, false)) {
                        return ItemStack.field_190927_a;
                    }
                } else if (!playerIn.field_70170_p.field_72995_K) {
                    this.interactionPulse = this.interactionPulse + 1 & Short.MAX_VALUE;
                }
            }
            if (in.func_190926_b()) {
                slot.func_75215_d(ItemStack.field_190927_a);
            } else {
                slot.func_75218_e();
            }
            if (in.func_190916_E() == ret.func_190916_E()) {
                return ItemStack.field_190927_a;
            }
            slot.func_190901_a(playerIn, in);
        }
        return ret;
    }

    private void applyParasiteEffects(EntityPlayer p, float fullness) {
        int viralDur;
        int viralAmp;
        int corrosDur;
        int corrosAmp;
        if (p.field_70170_p.field_72995_K) {
            return;
        }
        if (fullness < 0.25f) {
            corrosAmp = 1;
            corrosDur = 400;
            viralAmp = 3;
            viralDur = 600;
        } else if (fullness < 0.5f) {
            corrosAmp = 1;
            corrosDur = 200;
            viralAmp = 2;
            viralDur = 400;
        } else if (fullness < 0.75f) {
            corrosAmp = 0;
            corrosDur = 200;
            viralAmp = 1;
            viralDur = 200;
        } else if (fullness < 1.0f) {
            corrosAmp = 0;
            corrosDur = 100;
            viralAmp = 1;
            viralDur = 100;
        } else {
            return;
        }
        p.func_70690_d(new PotionEffect(SRPPotions.CORRO_E, corrosDur, corrosAmp, false, false));
        SRPPotions.applyStackPotion(SRPPotions.VIRA_E, (EntityLivingBase)p, viralDur, viralAmp);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (IContainerListener l : this.field_75149_d) {
            l.func_71112_a((Container)this, 0, this.te.getFullnessField());
            l.func_71112_a((Container)this, 1, this.interactionPulse);
        }
    }

    public void func_75137_b(int id, int data) {
        if (id == 1) {
            this.clientPulse = data;
        }
    }

    public void func_75132_a(IContainerListener l) {
        super.func_75132_a(l);
        l.func_71112_a((Container)this, 0, this.te.getFullnessField());
        l.func_71112_a((Container)this, 1, this.interactionPulse);
    }
}

