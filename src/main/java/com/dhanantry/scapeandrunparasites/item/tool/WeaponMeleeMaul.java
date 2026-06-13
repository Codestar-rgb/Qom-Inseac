/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.IEntityOwnable
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.player.CriticalHitEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.item.tool;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.item.tool.WeaponToolMeleeBase;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WeaponMeleeMaul
extends WeaponToolMeleeBase {
    private static final int MAUL_COOLDOWN_TICKS = 200;
    private static final int DEBUFF_TICKS = 200;
    private static final double SLAM_RADIUS = 4.0;
    private static final double KNOCKUP = 0.85;
    private static final double PUSH = 1.25;
    private static final String NBT_DASH = "srp_maul_dash";
    private static final String NBT_DASH_TICKS = "srp_maul_dash_t";
    private static final String NBT_DASH_DX = "srp_maul_dash_dx";
    private static final String NBT_DASH_DZ = "srp_maul_dash_dz";
    private static final String NBT_DASH_ITEM = "srp_maul_dash_item";
    private static final String NBT_DASH_DY = "srp_maul_dash_dy";
    private static final String NBT_DASH_TOTAL = "srp_maul_dash_total";
    private static final String NBT_DASH_ORIG = "srp_maul_dash_orig";
    private static final String NBT_SLAM_PENDING = "srp_maul_slam_pending";
    private static final String NBT_SLAM_PENDING_T = "srp_maul_slam_pending_t";
    private static final String NBT_SLAM_DELAY = "srp_maul_slam_delay";
    private static final String NBT_CHARGED_AOE = "srp_maul_charged_aoe";
    private static final double DASH_MAX_UP = 0.95;
    private static final double DASH_MAX_DOWN = -0.25;
    private static final int SLAM_WAIT_MAX_TICKS = 200;
    private static final int SENTIENT_DASH_TICKS = 4;
    private static final double SENTIENT_DASH_DIST = 16.0;
    private static final int SENTIENT_COOLDOWN_TICKS = 500;
    private static final int SENTIENT_MIN_CHARGE_TICKS = 6;

    public WeaponMeleeMaul(Item.ToolMaterial material, String name, double attackspeed, float range, float attackD, boolean fear, byte id) {
        super(material, name, attackspeed, range, attackD, fear, id);
    }

    @Override
    public void func_77622_d(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.func_77622_d(stack, worldIn, playerIn);
    }

    @Override
    public Item getNext() {
        if (this == SRPItems.weapon_maul) {
            return SRPItems.weapon_maulSentient;
        }
        return null;
    }

    @Override
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        boolean flag = super.func_77644_a(stack, target, attacker);
        if (flag && this.calling && attacker instanceof EntityPlayer && target.func_110143_aJ() <= 0.0f) {
            EntityPlayer player = (EntityPlayer)attacker;
            if (!player.getEntityData().func_74767_n(NBT_CHARGED_AOE)) {
                return flag;
            }
            int times = 2;
            if (this.calling) {
                times = 5;
            }
            for (int i = 0; i <= times; ++i) {
                this.hitTarget(stack, target, player);
            }
            if (target.func_110143_aJ() <= 0.0f) {
                NBTTagCompound compound = stack.func_77978_p();
                if (compound == null) {
                    compound = new NBTTagCompound();
                }
                if (compound.func_74764_b("srpkills")) {
                    int key = (int)((float)compound.func_74762_e("srpkills") + target.func_110138_aP());
                    compound.func_74768_a("srpkills", key);
                } else {
                    compound.func_74768_a("srpkills", (int)target.func_110138_aP());
                }
            }
        }
        return flag;
    }

    private void hitTarget(ItemStack stack, EntityLivingBase targetEntity, EntityPlayer attacker) {
        if (!ForgeHooks.onPlayerAttackTarget((EntityPlayer)attacker, (Entity)targetEntity)) {
            return;
        }
        if (targetEntity.func_70075_an() && !targetEntity.func_85031_j((Entity)attacker)) {
            float f = (float)attacker.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
            float f1 = EnchantmentHelper.func_152377_a((ItemStack)attacker.func_184614_ca(), (EnumCreatureAttribute)targetEntity.func_70668_bt());
            float f2 = attacker.func_184825_o(0.5f);
            f1 *= f2;
            attacker.func_184821_cY();
            if ((f *= 0.2f + f2 * f2 * 0.8f) > 0.0f || f1 > 0.0f) {
                ItemStack itemstack;
                boolean flag = f2 > 0.9f;
                boolean flag1 = false;
                int i = 0;
                i += EnchantmentHelper.func_77501_a((EntityLivingBase)attacker);
                if (attacker.func_70051_ag() && flag) {
                    attacker.field_70170_p.func_184148_a((EntityPlayer)null, attacker.field_70165_t, attacker.field_70163_u, attacker.field_70161_v, SoundEvents.field_187721_dT, attacker.func_184176_by(), 1.0f, 1.0f);
                    ++i;
                    flag1 = true;
                }
                boolean flag2 = flag && attacker.field_70143_R > 0.0f && !attacker.field_70122_E && !attacker.func_70617_f_() && !attacker.func_70090_H() && !attacker.func_70644_a(MobEffects.field_76440_q) && !attacker.func_184218_aH() && targetEntity instanceof EntityLivingBase;
                flag2 = flag2 && !attacker.func_70051_ag();
                CriticalHitEvent hitResult = ForgeHooks.getCriticalHit((EntityPlayer)attacker, (Entity)targetEntity, (boolean)flag2, (float)(flag2 ? 1.5f : 1.0f));
                boolean bl = flag2 = hitResult != null;
                if (flag2) {
                    f *= hitResult.getDamageModifier();
                }
                f += f1;
                boolean flag3 = false;
                double d0 = attacker.field_70140_Q - attacker.field_70141_P;
                if (flag && !flag2 && !flag1 && attacker.field_70122_E && d0 < (double)attacker.func_70689_ay() && (itemstack = attacker.func_184586_b(EnumHand.MAIN_HAND)).func_77973_b() instanceof ItemSword) {
                    flag3 = true;
                }
                float f4 = 0.0f;
                boolean flag4 = false;
                int j = EnchantmentHelper.func_90036_a((EntityLivingBase)attacker);
                f4 = targetEntity.func_110143_aJ();
                if (j > 0 && !targetEntity.func_70027_ad()) {
                    flag4 = true;
                    targetEntity.func_70015_d(1);
                }
                targetEntity.func_70606_j(targetEntity.func_110143_aJ() - f);
                targetEntity.func_70097_a(DamageSource.func_76365_a((EntityPlayer)attacker), 0.0f);
                boolean flag5 = true;
                if (flag5) {
                    if (flag3) {
                        attacker.field_70170_p.func_184148_a((EntityPlayer)null, attacker.field_70165_t, attacker.field_70163_u, attacker.field_70161_v, SoundEvents.field_187730_dW, attacker.func_184176_by(), 1.0f, 1.0f);
                        attacker.func_184810_cG();
                    }
                    if (flag2) {
                        attacker.field_70170_p.func_184148_a((EntityPlayer)null, attacker.field_70165_t, attacker.field_70163_u, attacker.field_70161_v, SoundEvents.field_187718_dS, attacker.func_184176_by(), 1.0f, 1.0f);
                        attacker.func_71009_b((Entity)targetEntity);
                    }
                    if (!flag2 && !flag3) {
                        if (flag) {
                            attacker.field_70170_p.func_184148_a((EntityPlayer)null, attacker.field_70165_t, attacker.field_70163_u, attacker.field_70161_v, SoundEvents.field_187727_dV, attacker.func_184176_by(), 1.0f, 1.0f);
                        } else {
                            attacker.field_70170_p.func_184148_a((EntityPlayer)null, attacker.field_70165_t, attacker.field_70163_u, attacker.field_70161_v, SoundEvents.field_187733_dX, attacker.func_184176_by(), 1.0f, 1.0f);
                        }
                    }
                    if (f1 > 0.0f) {
                        attacker.func_71047_c((Entity)targetEntity);
                    }
                    attacker.func_130011_c((Entity)targetEntity);
                    EnchantmentHelper.func_151384_a((EntityLivingBase)targetEntity, (Entity)attacker);
                    EnchantmentHelper.func_151385_b((EntityLivingBase)attacker, (Entity)targetEntity);
                    ItemStack itemstack1 = attacker.func_184614_ca();
                    if (!itemstack1.func_190926_b()) {
                        ItemStack beforeHitCopy = itemstack1.func_77946_l();
                        stack.func_77972_a(1, (EntityLivingBase)attacker);
                        if (itemstack1.func_190926_b()) {
                            ForgeEventFactory.onPlayerDestroyItem((EntityPlayer)attacker, (ItemStack)beforeHitCopy, (EnumHand)EnumHand.MAIN_HAND);
                            attacker.func_184611_a(EnumHand.MAIN_HAND, ItemStack.field_190927_a);
                        }
                    }
                    float f5 = f4 - targetEntity.func_110143_aJ();
                    attacker.func_71064_a(StatList.field_188111_y, Math.round(f5 * 10.0f));
                    if (j > 0) {
                        targetEntity.func_70015_d(j * 4);
                    }
                    if (attacker.field_70170_p instanceof WorldServer && f5 > 2.0f) {
                        int k = (int)((double)f5 * 0.5);
                        ((WorldServer)attacker.field_70170_p).func_175739_a(EnumParticleTypes.DAMAGE_INDICATOR, targetEntity.field_70165_t, targetEntity.field_70163_u + (double)(targetEntity.field_70131_O * 0.5f), targetEntity.field_70161_v, k, 0.1, 0.0, 0.1, 0.2, new int[0]);
                    }
                    attacker.func_71020_j(0.1f);
                } else {
                    attacker.field_70170_p.func_184148_a((EntityPlayer)null, attacker.field_70165_t, attacker.field_70163_u, attacker.field_70161_v, SoundEvents.field_187724_dU, attacker.func_184176_by(), 1.0f, 1.0f);
                    if (flag4) {
                        targetEntity.func_70066_B();
                    }
                }
            }
        }
    }

    @Override
    public EnumRarity func_77613_e(ItemStack stack) {
        return super.func_77613_e(stack);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.func_77624_a(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.YELLOW + I18n.func_135052_a((String)("tootip.srparasites.weaponm." + this.idTool), (Object[])new Object[0]));
        tooltip.add(TextFormatting.RED + I18n.func_135052_a((String)("tootip.srparasites.weaponm." + this.idTool * 10), (Object[])new Object[0]));
        if (this.calling && SRPConfigSystems.useScent) {
            tooltip.add(TextFormatting.BLACK + I18n.func_135052_a((String)("tootip.srparasites.weaponm." + this.idTool * 100), (Object[])new Object[0]));
        }
    }

    public EnumAction func_77661_b(ItemStack stack) {
        return this.calling ? EnumAction.BOW : EnumAction.NONE;
    }

    public int func_77626_a(ItemStack stack) {
        return this.calling ? 72000 : 0;
    }

    private void doMaulSlam(World world, EntityPlayer player, ItemStack stack) {
        AxisAlignedBB bb = player.func_174813_aQ().func_72314_b(4.0, 1.5, 4.0);
        List list = world.func_72872_a(EntityLivingBase.class, bb);
        ItemStack held = player.func_184614_ca();
        float base = (float)player.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
        for (EntityLivingBase e : list) {
            if (e == player || !e.func_70089_S() || e.func_184191_r((Entity)player) || player.func_184191_r((Entity)e) || e instanceof IEntityOwnable && ((IEntityOwnable)e).func_184753_b() != null) continue;
            float bonus = 0.0f;
            if (!held.func_190926_b()) {
                bonus = EnchantmentHelper.func_152377_a((ItemStack)held, (EnumCreatureAttribute)e.func_70668_bt());
            }
            float slamDamage = (base + bonus) * 2.0f;
            e.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), slamDamage);
            double dx = e.field_70165_t - player.field_70165_t;
            double dz = e.field_70161_v - player.field_70161_v;
            double len = Math.sqrt(dx * dx + dz * dz);
            if (len < 1.0E-4) {
                dx = 0.0;
                dz = 0.0;
                len = 1.0;
            }
            double nx = dx / len;
            double nz = dz / len;
            e.field_70181_x = Math.max(e.field_70181_x, 0.85);
            e.field_70159_w += nx * 1.25;
            e.field_70179_y += nz * 1.25;
            e.field_70133_I = true;
            e.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 200, 0));
            e.func_70690_d(new PotionEffect(MobEffects.field_76437_t, 200, 0));
        }
        if (world instanceof WorldServer) {
            ((WorldServer)world).func_175739_a(EnumParticleTypes.SWEEP_ATTACK, player.field_70165_t, player.field_70163_u + 1.0, player.field_70161_v, 16, 1.4, 0.25, 1.4, 0.0, new int[0]);
        }
        if (!world.field_72995_K) {
            player.getEntityData().func_74757_a(NBT_CHARGED_AOE, false);
        }
        this.spawnGroundShake(world, player);
        world.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SRPSounds.VENGEANCE_ROCK, SoundCategory.PLAYERS, 0.9f, 0.9f);
        stack.func_77972_a(1, (EntityLivingBase)player);
    }

    private void armPendingSlam(NBTTagCompound ptag, int maxTicks) {
        ptag.func_74757_a(NBT_SLAM_PENDING, true);
        ptag.func_74768_a(NBT_SLAM_PENDING_T, maxTicks);
        ptag.func_74768_a(NBT_SLAM_DELAY, 1);
    }

    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.func_184586_b(hand);
        if (this.calling) {
            if (!world.field_72995_K) {
                NBTTagCompound ptag = player.getEntityData();
                if (player.func_70093_af() && ptag.func_74767_n(NBT_SLAM_PENDING) && !player.field_70122_E) {
                    this.forceDrop(player);
                    return new ActionResult(EnumActionResult.SUCCESS, (Object)stack);
                }
            }
            if (player.func_184811_cZ().func_185141_a((Item)this)) {
                return new ActionResult(EnumActionResult.PASS, (Object)stack);
            }
            player.func_184598_c(hand);
            return new ActionResult(EnumActionResult.SUCCESS, (Object)stack);
        }
        if (player.func_184811_cZ().func_185141_a((Item)this)) {
            return new ActionResult(EnumActionResult.PASS, (Object)stack);
        }
        player.func_184609_a(hand);
        if (!world.field_72995_K) {
            this.doMaulSlam(world, player, stack);
            player.func_184811_cZ().func_185145_a((Item)this, 200);
        }
        return new ActionResult(EnumActionResult.SUCCESS, (Object)stack);
    }

    private void forceDrop(EntityPlayer player) {
        player.field_70181_x = -3.5;
        player.field_70133_I = true;
        player.field_70143_R = 0.0f;
    }

    public boolean func_111207_a(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        if (this.calling) {
            return false;
        }
        if (player.func_184811_cZ().func_185141_a((Item)this)) {
            return false;
        }
        player.func_184609_a(hand);
        if (!player.field_70170_p.field_72995_K) {
            this.doMaulSlam(player.field_70170_p, player, stack);
            player.func_184811_cZ().func_185145_a((Item)this, 200);
        }
        return true;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (this.calling) {
            return false;
        }
        return false;
    }

    private void spawnGroundShake(World world, EntityPlayer player) {
        if (!(world instanceof WorldServer)) {
            return;
        }
        WorldServer ws = (WorldServer)world;
        int r = (int)Math.ceil(4.0);
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int dx = -r; dx <= r; ++dx) {
            for (int dz = -r; dz <= r; ++dz) {
                double dist2 = dx * dx + dz * dz;
                if (dist2 > 16.0) continue;
                int x = (int)Math.floor(player.field_70165_t) + dx;
                int z = (int)Math.floor(player.field_70161_v) + dz;
                int baseY = (int)Math.floor(player.func_174813_aQ().field_72338_b);
                int groundY = -1;
                for (int dy = 2; dy >= -6; --dy) {
                    int y = baseY + dy;
                    pos.func_181079_c(x, y, z);
                    IBlockState state = world.func_180495_p((BlockPos)pos);
                    if (state.func_185904_a() == Material.field_151579_a || state.func_185904_a() == Material.field_151586_h || state.func_185904_a() == Material.field_151587_i) continue;
                    groundY = y;
                    break;
                }
                if (groundY < 0) continue;
                pos.func_181079_c(x, groundY, z);
                IBlockState ground = world.func_180495_p((BlockPos)pos);
                if (ground.func_185904_a() == Material.field_151579_a) continue;
                int stateId = Block.func_176210_f((IBlockState)ground);
                ws.func_175739_a(EnumParticleTypes.BLOCK_CRACK, (double)x + 0.5, (double)groundY + 1.02, (double)z + 0.5, 6, 0.35, 0.06, 0.35, 0.15, new int[]{stateId});
            }
        }
    }

    public void func_77615_a(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
        Vec3d dir;
        if (!this.calling) {
            return;
        }
        if (!(entityLiving instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)entityLiving;
        if (player.func_184811_cZ().func_185141_a((Item)this)) {
            return;
        }
        int used = this.func_77626_a(stack) - timeLeft;
        if (used < 6) {
            return;
        }
        float charge = this.getBowCharge(used);
        if (charge <= 0.0f) {
            return;
        }
        Vec3d look = player.func_70040_Z();
        if (look.func_72433_c() < 0.001) {
            return;
        }
        double y = look.field_72448_b;
        if (y > 0.95) {
            y = 0.95;
        }
        if (y < -0.25) {
            y = -0.25;
        }
        if ((dir = new Vec3d(look.field_72450_a, y, look.field_72449_c)).func_72433_c() < 0.001) {
            return;
        }
        dir = dir.func_72432_b();
        double totalDist = 16.0 * (double)charge;
        int dashTicks = Math.max(3, (int)Math.ceil(4.0f * charge));
        if (!world.field_72995_K) {
            NBTTagCompound ptag = player.getEntityData();
            ptag.func_74757_a(NBT_DASH, true);
            ptag.func_74768_a(NBT_DASH_TICKS, dashTicks);
            ptag.func_74768_a(NBT_DASH_ORIG, dashTicks);
            ptag.func_74757_a(NBT_CHARGED_AOE, true);
            ptag.func_74780_a(NBT_DASH_DX, dir.field_72450_a);
            ptag.func_74780_a(NBT_DASH_DY, dir.field_72448_b);
            ptag.func_74780_a(NBT_DASH_DZ, dir.field_72449_c);
            ptag.func_74780_a(NBT_DASH_TOTAL, totalDist);
            ptag.func_74778_a(NBT_DASH_ITEM, this.getRegistryName().toString());
            ptag.func_74757_a(NBT_SLAM_PENDING, false);
            ptag.func_74768_a(NBT_SLAM_PENDING_T, 0);
            ptag.func_74768_a(NBT_SLAM_DELAY, 0);
            player.func_184811_cZ().func_185145_a((Item)this, 500);
        }
    }

    private float getBowCharge(int chargeTicks) {
        float f = (float)chargeTicks / 20.0f;
        if ((f = (f * f + f * 2.0f) / 3.0f) > 1.0f) {
            f = 1.0f;
        }
        return f;
    }

    private RayTraceResult rayTraceEntities(World world, EntityPlayer player, Vec3d start, Vec3d end) {
        Entity closest = null;
        Vec3d closestHit = null;
        double closestDist = start.func_72436_e(end);
        AxisAlignedBB box = player.func_174813_aQ().func_72321_a(end.field_72450_a - start.field_72450_a, end.field_72448_b - start.field_72448_b, end.field_72449_c - start.field_72449_c).func_186662_g(1.0);
        List entities = world.func_72839_b((Entity)player, box);
        for (Entity e : entities) {
            double d;
            AxisAlignedBB eb;
            RayTraceResult r;
            if (!e.func_70067_L() || (r = (eb = e.func_174813_aQ().func_186662_g(0.3)).func_72327_a(start, end)) == null || !((d = start.func_72436_e(r.field_72307_f)) < closestDist)) continue;
            closestDist = d;
            closest = e;
            closestHit = r.field_72307_f;
        }
        if (closest == null) {
            return null;
        }
        RayTraceResult out = new RayTraceResult(closest, closestHit);
        out.field_72313_a = RayTraceResult.Type.ENTITY;
        return out;
    }

    @Override
    public void func_77663_a(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        ItemStack slamStack;
        super.func_77663_a(stack, world, entity, slot, selected);
        if (!this.calling) {
            return;
        }
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)entity;
        NBTTagCompound ptag = player.getEntityData();
        ItemStack held = player.func_184614_ca();
        ItemStack itemStack = slamStack = !held.func_190926_b() && held.func_77973_b() == this ? held : stack;
        if (!world.field_72995_K && ptag.func_74767_n(NBT_SLAM_PENDING)) {
            boolean landed;
            int delay = ptag.func_74762_e(NBT_SLAM_DELAY);
            if (delay > 0) {
                ptag.func_74768_a(NBT_SLAM_DELAY, delay - 1);
                player.field_70143_R = 0.0f;
                return;
            }
            int t = ptag.func_74762_e(NBT_SLAM_PENDING_T);
            boolean bl = landed = player.field_70122_E || player.field_70124_G && player.field_70181_x <= 0.0 || player.func_180799_ab() || player.func_70090_H();
            if (landed) {
                this.doMaulSlam(world, player, slamStack);
                ptag.func_74757_a(NBT_SLAM_PENDING, false);
                ptag.func_74768_a(NBT_SLAM_PENDING_T, 0);
                ptag.func_74768_a(NBT_SLAM_DELAY, 0);
            } else if (t <= 0) {
                ptag.func_74757_a(NBT_SLAM_PENDING, false);
                ptag.func_74768_a(NBT_SLAM_PENDING_T, 0);
                ptag.func_74768_a(NBT_SLAM_DELAY, 0);
            } else {
                ptag.func_74768_a(NBT_SLAM_PENDING_T, t - 1);
                player.field_70143_R = 0.0f;
            }
        }
        if (!ptag.func_74767_n(NBT_DASH)) {
            return;
        }
        ItemStack heldMain = player.func_184614_ca();
        if (!selected || heldMain.func_190926_b() || heldMain.func_77973_b() != this) {
            ptag.func_74757_a(NBT_DASH, false);
            if (!world.field_72995_K) {
                this.armPendingSlam(ptag, 200);
            }
            return;
        }
        if (!ptag.func_74779_i(NBT_DASH_ITEM).equals(this.getRegistryName().toString())) {
            ptag.func_74757_a(NBT_DASH, false);
            if (!world.field_72995_K) {
                this.armPendingSlam(ptag, 200);
            }
            return;
        }
        int ticksLeft = ptag.func_74762_e(NBT_DASH_TICKS);
        int origTicks = ptag.func_74762_e(NBT_DASH_ORIG);
        if (ticksLeft <= 0 || origTicks <= 0) {
            if (!world.field_72995_K) {
                this.armPendingSlam(ptag, 200);
            }
            ptag.func_74757_a(NBT_DASH, false);
            return;
        }
        double dx = ptag.func_74769_h(NBT_DASH_DX);
        double dy = ptag.func_74769_h(NBT_DASH_DY);
        double dz = ptag.func_74769_h(NBT_DASH_DZ);
        double totalDist = ptag.func_74769_h(NBT_DASH_TOTAL);
        double step = totalDist / (double)origTicks;
        player.field_70143_R = 0.0f;
        if (!world.field_72995_K) {
            Vec3d end;
            Vec3d start = new Vec3d(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v);
            RayTraceResult hitE = this.rayTraceEntities(world, player, start, end = start.func_72441_c(dx * step, dy * step, dz * step));
            if (hitE != null && hitE.field_72313_a == RayTraceResult.Type.ENTITY && hitE.field_72308_g instanceof EntityLivingBase) {
                this.doMaulSlam(world, player, slamStack);
                EntityLivingBase t = (EntityLivingBase)hitE.field_72308_g;
                float base = (float)player.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
                t.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), base * 2.0f);
                ptag.func_74757_a(NBT_DASH, false);
                return;
            }
            RayTraceResult hitB = world.func_147447_a(start, end, false, true, false);
            if (hitB != null && hitB.field_72313_a == RayTraceResult.Type.BLOCK) {
                this.armPendingSlam(ptag, 200);
                ptag.func_74757_a(NBT_DASH, false);
                return;
            }
            player.field_70159_w = dx * step;
            player.field_70179_y = dz * step;
            player.field_70181_x = dy * step;
            if (player.field_70181_x < -1.25) {
                player.field_70181_x = -1.25;
            }
            if (player.field_70181_x > 1.25) {
                player.field_70181_x = 1.25;
            }
            player.field_70133_I = true;
            ptag.func_74768_a(NBT_DASH_TICKS, ticksLeft - 1);
        }
    }
}

