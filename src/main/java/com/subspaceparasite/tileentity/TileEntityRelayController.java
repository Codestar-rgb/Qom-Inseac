/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.InventoryHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.SPacketUpdateTileEntity
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ITickable
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.util.text.translation.I18n
 *  net.minecraft.world.World
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.items.CapabilityItemHandler
 *  net.minecraftforge.items.ItemStackHandler
 */
package com.subspaceparasite.tileentity;

import com.subspaceparasite.entity.ai.misc.EntityCanHaveBodies;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.item.ItemDislodgementReport;
import com.subspaceparasite.item.ItemModule;
import com.subspaceparasite.item.ItemPhaseReport;
import com.subspaceparasite.item.ItemVectorMapReport;
import com.subspaceparasite.network.SPCommandEvolution;
import com.subspaceparasite.tileentity.TileEntityNodeRelay;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.SPWorldData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityRelayController
extends TileEntity
implements ITickable {
    public boolean formed = false;
    private long nextScanTick = 0L;
    private static final String NBT_NEXT_SCAN = "NextScanTick";
    private boolean scanPending = false;
    private int scanTicks = 0;
    private UUID scanPlayer = null;
    private ItemModule.Kind scanKind = null;
    private final List<BlockPos> childPositions = new ArrayList<BlockPos>();
    private boolean dismantling = false;
    private static final int SCAN_RADIUS = 8;
    private final ItemStackHandler itemHandler = new ItemStackHandler(1){

        public boolean isItemValid(int slot, ItemStack stack) {
            return stack != null && !stack.func_190926_b() && stack.func_77973_b() instanceof ItemModule;
        }

        protected void onContentsChanged(int slot) {
            TileEntityRelayController.this.func_70296_d();
        }
    };
    private boolean capsInvalidated = false;

    public void setChildPositions(List<BlockPos> positions) {
        this.childPositions.clear();
        if (positions != null) {
            this.childPositions.addAll(positions);
        }
        this.func_70296_d();
    }

    private int scannerCooldownTicks() {
        return SPConfigSystems.getScannerCooldownTicks();
    }

    public boolean canScan() {
        return this.field_145850_b == null || this.field_145850_b.func_82737_E() >= this.nextScanTick;
    }

    public int getCooldownRemainingTicks() {
        if (this.field_145850_b == null) {
            return 0;
        }
        long t = this.nextScanTick - this.field_145850_b.func_82737_E();
        return t > 0L ? (int)t : 0;
    }

    public int getCooldownTotalTicks() {
        return this.scannerCooldownTicks();
    }

    public void startCooldown() {
        if (this.field_145850_b == null) {
            return;
        }
        this.nextScanTick = this.field_145850_b.func_82737_E() + (long)this.scannerCooldownTicks();
        this.func_70296_d();
        if (!this.field_145850_b.field_72995_K) {
            IBlockState s = this.field_145850_b.func_180495_p(this.func_174877_v());
            this.field_145850_b.func_184138_a(this.func_174877_v(), s, s, 3);
        }
    }

    public void addChild(BlockPos p) {
        if (p != null && !this.childPositions.contains(p)) {
            this.childPositions.add(p);
            this.func_70296_d();
        }
    }

    public ItemStackHandler getHandler() {
        return this.itemHandler;
    }

    public void dropContents() {
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return;
        }
        ItemStack stack = this.itemHandler.getStackInSlot(0);
        if (!stack.func_190926_b()) {
            InventoryHelper.func_180173_a((World)this.field_145850_b, (double)((double)this.func_174877_v().func_177958_n() + 0.5), (double)((double)this.func_174877_v().func_177956_o() + 0.5), (double)((double)this.func_174877_v().func_177952_p() + 0.5), (ItemStack)stack.func_77946_l());
            this.itemHandler.setStackInSlot(0, ItemStack.field_190927_a);
            this.func_70296_d();
        }
    }

    public void setFormed(boolean val) {
        if (this.formed == val) {
            return;
        }
        this.formed = val;
        this.func_70296_d();
        if (this.field_145850_b != null && !this.field_145850_b.field_72995_K) {
            IBlockState s = this.field_145850_b.func_180495_p(this.field_174879_c);
            this.field_145850_b.func_184138_a(this.field_174879_c, s, s, 3);
            this.field_145850_b.func_175704_b(this.field_174879_c, this.field_174879_c);
        }
    }

    public void func_73660_a() {
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return;
        }
        if (!this.scanPending) {
            return;
        }
        if (--this.scanTicks > 0) {
            return;
        }
        this.scanPending = false;
        EntityPlayerMP player = this.field_145850_b.func_73046_m().func_184103_al().func_177451_a(this.scanPlayer);
        if (player == null) {
            this.func_70296_d();
            return;
        }
        if (SPConfigSystems.relayScannerDebugGlow) {
            this.debugGlowParasites();
        }
        if (this.scanKind == ItemModule.Kind.VECTORS) {
            this.giveVectorPaper(player);
        } else if (this.scanKind == ItemModule.Kind.PHASE) {
            this.givePhasePaper(player);
        } else if (this.scanKind == ItemModule.Kind.DISLODGEMENT) {
            this.giveDislodgementPaper(player);
        } else {
            this.giveScanPaper(player, this.scanKind);
        }
        this.field_145850_b.func_184133_a(null, this.field_174879_c, SPSounds.RELAY_PAPER, SoundCategory.BLOCKS, 1.0f, 1.0f);
        this.func_70296_d();
    }

    private void debugGlowParasites() {
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return;
        }
        List all = this.field_145850_b.func_175644_a(EntityLivingBase.class, e -> true);
        for (EntityLivingBase e2 : all) {
            ResourceLocation key = EntityList.func_191301_a((Entity)e2);
            if (key == null || !"subspaceparasite".equals(key.func_110624_b())) continue;
            e2.func_70690_d(new PotionEffect(MobEffects.field_188423_x, 200, 0, false, false));
        }
    }

    private void giveScanPaper(EntityPlayerMP player, ItemModule.Kind kind) {
        String ratio;
        String percent;
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return;
        }
        ScanRegistry.ModuleProfile profile = ScanRegistry.getProfileFor(kind);
        if (profile == null) {
            return;
        }
        List all = this.field_145850_b.func_175644_a(EntityLivingBase.class, e -> true);
        LinkedHashMap<ScanRegistry.Tier, Integer> tierCounts = new LinkedHashMap<ScanRegistry.Tier, Integer>();
        int parasitesTotal = 0;
        for (ScanRegistry.Tier tier : profile.tiers) {
            int c = 0;
            for (EntityLivingBase e2 : all) {
                if (!(e2 instanceof EntityLiving) || !tier.matches(e2)) continue;
                ++c;
            }
            tierCounts.put(tier, c);
            parasitesTotal += c;
        }
        int mobTotal = 0;
        for (EntityLivingBase e3 : all) {
            if (!(e3 instanceof EntityLiving)) continue;
            ++mobTotal;
        }
        int n = Math.max(0, mobTotal - parasitesTotal);
        String string = percent = mobTotal == 0 ? "0.0%" : String.format(Locale.ROOT, "%.1f%%", 100.0 * (double)parasitesTotal / (double)Math.max(1, mobTotal));
        if (parasitesTotal == 0 && n == 0) {
            ratio = "0:0";
        } else {
            int g = TileEntityRelayController.gcd(parasitesTotal, n);
            if (g == 0) {
                g = 1;
            }
            ratio = parasitesTotal / g + ":" + n / g;
        }
        int dim = this.field_145850_b.field_73011_w.getDimension();
        ItemStack paper = new ItemStack(Items.field_151121_aF);
        paper.func_151001_c(I18n.func_74838_a((String)"item.subspaceparasite.scan_report.name"));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(TextFormatting.DARK_PURPLE.toString() + I18n.func_74838_a((String)"item.subspaceparasite.scan_report.header"));
        lore.add(" ");
        lore.add(TextFormatting.GRAY.toString() + I18n.func_74837_a((String)"item.subspaceparasite.scan_report.dimension", (Object[])new Object[]{TextFormatting.GOLD.toString() + dim + TextFormatting.GRAY.toString()}));
        lore.add(TextFormatting.GRAY.toString() + I18n.func_74837_a((String)"item.subspaceparasite.scan_report.total_mobs", (Object[])new Object[]{TextFormatting.WHITE.toString() + mobTotal + TextFormatting.GRAY.toString()}));
        lore.add(TextFormatting.GRAY.toString() + I18n.func_74837_a((String)"item.subspaceparasite.scan_report.total_parasites", (Object[])new Object[]{TextFormatting.RED.toString() + parasitesTotal + TextFormatting.GRAY.toString()}));
        lore.add(TextFormatting.GRAY.toString() + I18n.func_74837_a((String)"item.subspaceparasite.scan_report.percent", (Object[])new Object[]{TextFormatting.YELLOW.toString() + percent + TextFormatting.GRAY.toString()}));
        lore.add(TextFormatting.GRAY.toString() + I18n.func_74837_a((String)"item.subspaceparasite.scan_report.ratio", (Object[])new Object[]{TextFormatting.AQUA.toString() + ratio + TextFormatting.GRAY.toString()}));
        lore.add(" ");
        lore.add(TextFormatting.DARK_GRAY.toString() + I18n.func_74838_a((String)"item.subspaceparasite.scan_report.tiers"));
        for (Map.Entry e4 : tierCounts.entrySet()) {
            ScanRegistry.Tier t = (ScanRegistry.Tier)e4.getKey();
            int c = (Integer)e4.getValue();
            String tierId = t.id == null ? "" : t.id.toLowerCase(Locale.ROOT);
            TextFormatting color = TextFormatting.WHITE;
            switch (tierId) {
                case "inborn": {
                    color = TextFormatting.GREEN;
                    break;
                }
                case "assimilated": {
                    color = TextFormatting.AQUA;
                    break;
                }
                case "assimara": {
                    color = TextFormatting.DARK_AQUA;
                    break;
                }
                case "hijacked": {
                    color = TextFormatting.DARK_RED;
                    break;
                }
                case "feral": {
                    color = TextFormatting.RED;
                    break;
                }
                case "crude": {
                    color = TextFormatting.DARK_GRAY;
                    break;
                }
                case "primitive": {
                    color = TextFormatting.GRAY;
                    break;
                }
                case "adapted": {
                    color = TextFormatting.GOLD;
                    break;
                }
                case "nexus": {
                    color = TextFormatting.LIGHT_PURPLE;
                    break;
                }
                case "deterrent": {
                    color = TextFormatting.DARK_PURPLE;
                    break;
                }
                case "pure": {
                    color = TextFormatting.BLUE;
                    break;
                }
                case "preeminent": {
                    color = TextFormatting.DARK_GREEN;
                    break;
                }
                case "ancient": {
                    color = TextFormatting.DARK_PURPLE;
                    break;
                }
                case "derived": {
                    color = TextFormatting.DARK_BLUE;
                    break;
                }
                default: {
                    color = TextFormatting.WHITE;
                }
            }
            String tierDisplay = t.getDisplayName();
            String line = color.toString() + tierDisplay + TextFormatting.GRAY.toString() + " : " + TextFormatting.WHITE.toString() + c;
            lore.add(line);
        }
        NBTTagCompound tag = paper.func_77978_p();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        NBTTagCompound display = tag.func_74775_l("display");
        NBTTagList loreTag = new NBTTagList();
        for (String line : lore) {
            loreTag.func_74742_a((NBTBase)new NBTTagString(line));
        }
        display.func_74782_a("Lore", (NBTBase)loreTag);
        tag.func_74782_a("display", (NBTBase)display);
        paper.func_77982_d(tag);
        if (!player.field_71071_by.func_70441_a(paper)) {
            player.func_71019_a(paper, false);
        }
    }

    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T)CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast((Object)this.itemHandler);
        }
        return (T)super.getCapability(capability, facing);
    }

    public void func_145843_s() {
        super.func_145843_s();
        this.capsInvalidated = true;
    }

    public void onChunkUnload() {
        super.onChunkUnload();
        this.capsInvalidated = true;
    }

    public void func_145829_t() {
        super.func_145829_t();
    }

    public NBTTagCompound func_189515_b(NBTTagCompound nbt) {
        super.func_189515_b(nbt);
        nbt.func_74757_a("Formed", this.formed);
        nbt.func_74772_a(NBT_NEXT_SCAN, this.nextScanTick);
        NBTTagList list = new NBTTagList();
        for (BlockPos p : this.childPositions) {
            if (p == null) continue;
            list.func_74742_a((NBTBase)NBTUtil.func_186859_a((BlockPos)p));
        }
        nbt.func_74782_a("Children", (NBTBase)list);
        nbt.func_74782_a("Inv", (NBTBase)this.itemHandler.serializeNBT());
        return nbt;
    }

    public void func_145839_a(NBTTagCompound nbt) {
        super.func_145839_a(nbt);
        this.nextScanTick = nbt.func_74763_f(NBT_NEXT_SCAN);
        this.formed = nbt.func_74767_n("Formed");
        this.childPositions.clear();
        if (nbt.func_150297_b("Children", 9)) {
            NBTTagList list = nbt.func_150295_c("Children", 10);
            for (int i = 0; i < list.func_74745_c(); ++i) {
                this.childPositions.add(NBTUtil.func_186861_c((NBTTagCompound)list.func_150305_b(i)));
            }
        }
        if (nbt.func_150297_b("Inv", 10)) {
            this.itemHandler.deserializeNBT(nbt.func_74775_l("Inv"));
        }
    }

    public void onLoad() {
        if (this.field_145850_b == null) {
            return;
        }
        if (!this.field_145850_b.field_72995_K) {
            this.relinkChildrenToMe();
            if (this.nextScanTick < 0L) {
                this.nextScanTick = 0L;
                this.func_70296_d();
            }
            IBlockState s = this.field_145850_b.func_180495_p(this.field_174879_c);
            this.field_145850_b.func_184138_a(this.field_174879_c, s, s, 3);
        }
    }

    public NBTTagCompound func_189517_E_() {
        return this.func_189515_b(new NBTTagCompound());
    }

    public SPacketUpdateTileEntity func_189518_D_() {
        NBTTagCompound tag = new NBTTagCompound();
        this.func_189515_b(tag);
        return new SPacketUpdateTileEntity(this.func_174877_v(), 0, tag);
    }

    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.func_145839_a(pkt.func_148857_g());
        if (this.field_145850_b != null) {
            this.field_145850_b.func_175704_b(this.field_174879_c, this.field_174879_c);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void dismantle() {
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return;
        }
        if (this.dismantling) {
            return;
        }
        this.dismantling = true;
        try {
            List<BlockPos> targets = new ArrayList<BlockPos>(this.childPositions);
            if (targets.isEmpty()) {
                targets = this.findChildrenByScan();
            }
            this.formed = false;
            this.dropContents();
            for (BlockPos p : targets) {
                if (p == null || !this.field_145850_b.func_175667_e(p) || this.field_145850_b.func_180495_p(p).func_177230_c() != SPBlocks.NODE_RELAY) continue;
                this.field_145850_b.func_175698_g(p);
            }
            this.childPositions.clear();
            this.func_70296_d();
            if (this.field_145850_b.func_175667_e(this.func_174877_v()) && this.field_145850_b.func_180495_p(this.func_174877_v()).func_177230_c() == SPBlocks.RELAY_CONTROLLER) {
                this.field_145850_b.func_175655_b(this.func_174877_v(), true);
            }
        }
        finally {
            this.dismantling = false;
        }
    }

    private void relinkChildrenToMe() {
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return;
        }
        for (BlockPos p : this.childPositions) {
            TileEntity te;
            if (p == null || !this.field_145850_b.func_175667_e(p) || !((te = this.field_145850_b.func_175625_s(p)) instanceof TileEntityNodeRelay)) continue;
            ((TileEntityNodeRelay)te).setControllerPos(this.func_174877_v());
        }
    }

    private List<BlockPos> findChildrenByScan() {
        ArrayList<BlockPos> found = new ArrayList<BlockPos>();
        if (this.field_145850_b == null) {
            return found;
        }
        BlockPos origin = this.func_174877_v();
        for (int dx = -8; dx <= 8; ++dx) {
            for (int dy = -8; dy <= 8; ++dy) {
                for (int dz = -8; dz <= 8; ++dz) {
                    BlockPos p = origin.func_177982_a(dx, dy, dz);
                    if (p.equals((Object)origin) || !this.field_145850_b.func_175667_e(p) || this.field_145850_b.func_180495_p(p).func_177230_c() != SPBlocks.NODE_RELAY) continue;
                    found.add(p);
                }
            }
        }
        return found;
    }

    public boolean performScan(EntityPlayerMP player) {
        ScanRegistry.ModuleProfile profile;
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return false;
        }
        if (!this.formed) {
            player.func_145747_a((ITextComponent)new TextComponentTranslation("chat.subspaceparasite.relay.not_formed", new Object[0]));
            return false;
        }
        ItemStack stack = this.itemHandler.getStackInSlot(0);
        if (stack.func_190926_b() || !(stack.func_77973_b() instanceof ItemModule)) {
            player.func_145747_a((ITextComponent)new TextComponentTranslation("chat.subspaceparasite.relay.insert_module", new Object[0]));
            return false;
        }
        ItemModule.Kind kind = ((ItemModule)stack.func_77973_b()).getKind(stack);
        if (kind == ItemModule.Kind.VECTORS) {
            if (!SPConfigWorld.originActivated) {
                player.func_145747_a((ITextComponent)new TextComponentTranslation("chat.subspaceparasite.vectors.not_activated", new Object[0]));
                return false;
            }
        } else if (kind != ItemModule.Kind.PHASE && kind != ItemModule.Kind.DISLODGEMENT && ((profile = ScanRegistry.getProfileFor(kind)) == null || profile.tiers.isEmpty())) {
            player.func_145747_a((ITextComponent)new TextComponentTranslation("chat.subspaceparasite.relay.no_profile", new Object[]{kind.name()}));
            return false;
        }
        if (this.scanPending) {
            player.func_145747_a((ITextComponent)new TextComponentTranslation("chat.subspaceparasite.relay.scan_busy", new Object[0]));
            return false;
        }
        this.scanPending = true;
        this.scanTicks = 110;
        this.scanPlayer = player.func_110124_au();
        this.scanKind = kind;
        this.field_145850_b.func_184133_a(null, this.field_174879_c, SPSounds.RELAY_ACTIVATE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        player.func_145747_a((ITextComponent)new TextComponentTranslation("chat.subspaceparasite.relay.scan_started", new Object[0]));
        this.startCooldown();
        this.func_70296_d();
        return true;
    }

    private void giveVectorPaper(EntityPlayerMP player) {
        int total;
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return;
        }
        if (!SPConfigWorld.originActivated) {
            player.func_145747_a((ITextComponent)new TextComponentTranslation("chat.subspaceparasite.vectors.disabled", new Object[0]));
            return;
        }
        SPWorldData data = SPWorldData.get(this.field_145850_b);
        if (data == null) {
            return;
        }
        ArrayList<Integer> xs = data.getorigins("x");
        ArrayList<Integer> ys = data.getorigins("y");
        ArrayList<Integer> zs = data.getorigins("z");
        ArrayList<Integer> rs = data.getorigins("a");
        ArrayList<Integer> hs = data.getorigins("h");
        int n = Integer.MAX_VALUE;
        if (xs != null) {
            n = Math.min(n, xs.size());
        }
        if (ys != null) {
            n = Math.min(n, ys.size());
        }
        if (zs != null) {
            n = Math.min(n, zs.size());
        }
        if (rs != null) {
            n = Math.min(n, rs.size());
        }
        if (hs != null) {
            n = Math.min(n, hs.size());
        }
        if (n == Integer.MAX_VALUE) {
            n = 0;
        }
        int day = (int)(this.field_145850_b.func_72820_D() / 24000L);
        int cx = player.func_180425_c().func_177958_n();
        int cz = player.func_180425_c().func_177952_p();
        int n2 = total = n <= 0 ? 1 : n;
        if (n <= 0) {
            ItemStack mapPaper = new ItemStack(SPItems.VECTOR_MAP);
            NBTTagCompound t = ItemVectorMapReport.getOrCreate(mapPaper);
            int printDay = (int)(this.field_145850_b.func_72820_D() / 24000L);
            int printTime = (int)(this.field_145850_b.func_72820_D() % 24000L);
            t.func_74768_a("PrintDay", printDay);
            t.func_74768_a("PrintTime", printTime);
            t.func_74768_a("CenterX", cx);
            t.func_74768_a("CenterZ", cz);
            t.func_74768_a("VectorX", cx);
            t.func_74768_a("VectorZ", cz);
            t.func_74768_a("Radius", 0);
            t.func_74768_a("Day", day);
            t.func_74768_a("Index", 1);
            t.func_74768_a("Total", total);
            if (!player.field_71071_by.func_70441_a(mapPaper)) {
                player.func_71019_a(mapPaper, false);
            }
        } else {
            for (int i = 0; i < n; ++i) {
                ItemStack mapPaper = new ItemStack(SPItems.VECTOR_MAP);
                NBTTagCompound t = ItemVectorMapReport.getOrCreate(mapPaper);
                int printDay = (int)(this.field_145850_b.func_72820_D() / 24000L);
                int printTime = (int)(this.field_145850_b.func_72820_D() % 24000L);
                t.func_74768_a("PrintDay", printDay);
                t.func_74768_a("PrintTime", printTime);
                t.func_74768_a("CenterX", cx);
                t.func_74768_a("CenterZ", cz);
                t.func_74768_a("VectorX", xs.get(i).intValue());
                t.func_74768_a("VectorZ", zs.get(i).intValue());
                t.func_74768_a("Radius", rs.get(i).intValue());
                t.func_74768_a("Day", day);
                t.func_74768_a("Index", i + 1);
                t.func_74768_a("Total", total);
                if (player.field_71071_by.func_70441_a(mapPaper)) continue;
                player.func_71019_a(mapPaper, false);
            }
        }
    }

    private void givePhasePaper(EntityPlayerMP player) {
        Entity ent;
        int i;
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return;
        }
        List serverList = this.field_145850_b.field_72996_f;
        int parasiteCount = 0;
        int cothCount = 0;
        int totalMobCount = 0;
        for (i = 0; i < serverList.size(); ++i) {
            ent = (Entity)serverList.get(i);
            if (ent instanceof EntityLivingBase && !(ent instanceof EntityPlayer)) {
                ++totalMobCount;
            }
            if (!(ent instanceof EntityParasiteBase)) continue;
            if (ent instanceof EntityCanHaveBodies) {
                EntityCanHaveBodies bodies = (EntityCanHaveBodies)ent;
                if (bodies.getBodyNumber() != 0) continue;
                ++parasiteCount;
                continue;
            }
            ++parasiteCount;
        }
        for (i = 0; i < serverList.size(); ++i) {
            ent = (Entity)serverList.get(i);
            if (!(ent instanceof EntityLivingBase) || ent instanceof EntityParasiteBase || !((EntityLivingBase)ent).func_70644_a(SPPotions.COTH_E)) continue;
            ++cothCount;
        }
        int dim = this.field_145850_b.field_73011_w.getDimension();
        SPSaveData data = SPSaveData.get(this.field_145850_b, 59);
        int players = this.field_145850_b.field_73010_i.size();
        players *= SPConfig.worldMobCapPlusPlayer;
        byte phase = data.getEvolutionPhase(dim);
        int totalPoints = data.getTotalKills(dim);
        int nextPoints = SPCommandEvolution.getNeededPoints((byte)(phase + 1));
        double progress = nextPoints <= 0 ? 0.0 : (double)totalPoints / (double)nextPoints * 100.0;
        String progStr = String.format(Locale.ROOT, "%.1f", progress);
        ItemStack report = new ItemStack(SPItems.phase_report);
        NBTTagCompound tag = ItemPhaseReport.getOrCreate(report);
        long worldTime = this.field_145850_b.func_72820_D();
        int day = (int)(worldTime / 24000L);
        int time = (int)(worldTime % 24000L);
        tag.func_74768_a("PrintDay", day);
        tag.func_74768_a("PrintTime", time);
        tag.func_74768_a("PhaseDimension", dim);
        tag.func_74768_a("PhaseValue", (int)phase);
        tag.func_74768_a("PhaseTotalPoints", totalPoints);
        tag.func_74768_a("PhasePointsNext", nextPoints);
        tag.func_74778_a("PhaseProgress", progStr);
        tag.func_74768_a("PhaseCooldown", data.getCooldown(this.field_145850_b, dim));
        tag.func_74757_a("PhaseCanGain", data.getCanGain(dim));
        tag.func_74757_a("PhaseCanLoss", data.getCanLoss(dim));
        tag.func_74768_a("PhaseMobcap", SPConfig.worldMobCap + players);
        tag.func_74768_a("PhaseGeneration", (int)data.getGeneration(dim));
        tag.func_74768_a("PhaseGenTicks", data.getGenerationNeededTime(this.field_145850_b, dim));
        tag.func_74768_a("PhaseParasiteCount", parasiteCount);
        tag.func_74768_a("PhaseCothCount", cothCount);
        tag.func_74768_a("PhaseTotalMobs", totalMobCount);
        report.func_77982_d(tag);
        if (!player.field_71071_by.func_70441_a(report)) {
            player.func_71019_a(report, false);
        }
    }

    private static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a == 0 ? 1 : a;
    }

    private void giveDislodgementPaper(EntityPlayerMP player) {
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return;
        }
        int dim = this.field_145850_b.field_73011_w.getDimension();
        SPSaveData data = SPSaveData.get(this.field_145850_b, 59);
        String raw = data.getCurrentCodeU(dim);
        String code = "";
        if (raw != null && !raw.trim().isEmpty()) {
            String[] vals = raw.split(";");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < vals.length; ++i) {
                int v = 0;
                try {
                    v = Integer.parseInt(vals[i].trim());
                }
                catch (Throwable t) {
                    v = 0;
                }
                if (v == 0) continue;
                int timeSec = data.getCurrentCodeDuration(dim, i);
                if (sb.length() > 0) {
                    sb.append(";");
                }
                sb.append(dim).append(";").append(i).append(";").append(v).append(";").append(timeSec);
            }
            code = sb.toString();
        }
        ItemStack report = new ItemStack(SPItems.DISLODGEMENT_REPORT);
        NBTTagCompound t = ItemDislodgementReport.getOrCreate(report);
        t.func_74778_a("DislodgementCode", code);
        int day = (int)(this.field_145850_b.func_72820_D() / 24000L);
        int time = (int)(this.field_145850_b.func_72820_D() % 24000L);
        t.func_74768_a("PrintDay", day);
        t.func_74768_a("PrintTime", time);
        if (!player.field_71071_by.func_70441_a(report)) {
            player.func_71019_a(report, false);
        }
    }

    public static final class ScanRegistry {
        private static boolean INIT = false;
        private static final Map<String, Tier> TIERS = new LinkedHashMap<String, Tier>();
        private static final Map<ItemModule.Kind, ModuleProfile> MODULES = new EnumMap<ItemModule.Kind, ModuleProfile>(ItemModule.Kind.class);

        static void ensureInit() {
            if (INIT) {
                return;
            }
            INIT = true;
            Tier INBORN = ScanRegistry.tier("INBORN");
            Tier ASSIMILATED = ScanRegistry.tier("ASSIMILATED");
            Tier HIJACKED = ScanRegistry.tier("HIJACKED");
            Tier FERAL = ScanRegistry.tier("FERAL");
            Tier CRUDE = ScanRegistry.tier("CRUDE");
            Tier PRIMITIVE = ScanRegistry.tier("PRIMITIVE");
            Tier ADAPTED = ScanRegistry.tier("ADAPTED");
            Tier NEXUS = ScanRegistry.tier("NEXUS");
            Tier DETERRENT = ScanRegistry.tier("DETERRENT");
            Tier PURE = ScanRegistry.tier("PURE");
            Tier PREEMINENT = ScanRegistry.tier("PREEMINENT");
            Tier ANCIENT = ScanRegistry.tier("ANCIENT");
            Tier ASSIMARA = ScanRegistry.tier("ASSIMARA");
            Tier DERIVED = ScanRegistry.tier("DERIVED");
            String MOD = "subspaceparasite";
            Function<String, ResourceLocation> rl = path -> new ResourceLocation("subspaceparasite", path);
            ASSIMILATED.addIds(rl.apply("sim_bigspider"), rl.apply("sim_squid"), rl.apply("sim_human"), rl.apply("sim_cow"), rl.apply("sim_sheep"), rl.apply("sim_wolf"), rl.apply("sim_pig"), rl.apply("sim_villager"), rl.apply("sim_adventurer"), rl.apply("sim_horse"), rl.apply("sim_bear"), rl.apply("sim_enderman"), rl.apply("sim_dragone"), rl.apply("sim_sheephead"), rl.apply("sim_wolfhead"), rl.apply("sim_cowhead"), rl.apply("sim_pighead"), rl.apply("sim_villagerhead"), rl.apply("sim_horsehead"), rl.apply("sim_humanhead"), rl.apply("sim_endermanhead"), rl.apply("sim_dragonehead"), rl.apply("sim_adventurerhead"));
            ASSIMARA.addIds(rl.apply("mar_enderman"), rl.apply("mar_cow"), rl.apply("mar_villager"), rl.apply("mar_human"), rl.apply("mar_sheep"), rl.apply("mar_bear"));
            DERIVED.addIds(rl.apply("draconite"), rl.apply("kirin"));
            FERAL.addIds(rl.apply("fer_bear"), rl.apply("fer_cow"), rl.apply("fer_enderman"), rl.apply("fer_horse"), rl.apply("fer_human"), rl.apply("fer_pig"), rl.apply("fer_sheep"), rl.apply("fer_villager"), rl.apply("fer_wolf"));
            HIJACKED.addIds(rl.apply("hi_blaze"), rl.apply("hi_golem"), rl.apply("hi_skeleton"));
            INBORN.addIds(rl.apply("carrier_heavy"), rl.apply("carrier_light"), rl.apply("buglin"), rl.apply("carrier_flying"), rl.apply("rupter"), rl.apply("movingflesh"), rl.apply("worker"), rl.apply("mangler"), rl.apply("gnat"), rl.apply("lice"));
            DETERRENT.addIds(rl.apply("kyphosis"), rl.apply("sentry"), rl.apply("seizer"), rl.apply("worm"));
            NEXUS.addIds(rl.apply("beckon_si"), rl.apply("beckon_sii"), rl.apply("beckon_siii"), rl.apply("beckon_siv"), rl.apply("dispatcherten"), rl.apply("dispatcher_si"), rl.apply("dispatcher_sii"), rl.apply("dispatcher_siii"), rl.apply("dispatcher_siv"), rl.apply("rooterball"), rl.apply("rooter_si"), rl.apply("rooter_sii"), rl.apply("rooter_siii"), rl.apply("rooter_siv"));
            CRUDE.addIds(rl.apply("incompleteform_small"), rl.apply("incompleteform_medium"), rl.apply("host"), rl.apply("hostii"), rl.apply("heed"), rl.apply("crux"), rl.apply("crux_incomplete"), rl.apply("thrall"), rl.apply("dredge"), rl.apply("airscrew"), rl.apply("carrier_worm"));
            PRIMITIVE.addIds(rl.apply("pri_longarms"), rl.apply("pri_manducater"), rl.apply("pri_reeker"), rl.apply("pri_yelloweye"), rl.apply("pri_summoner"), rl.apply("pri_bolster"), rl.apply("pri_tozoon"), rl.apply("pri_arachnida"), rl.apply("pri_devourer"), rl.apply("pri_vermin"), rl.apply("pri_viscera"), rl.apply("pri_burrower"));
            ADAPTED.addIds(rl.apply("ada_longarms"), rl.apply("ada_manducater"), rl.apply("ada_reeker"), rl.apply("ada_yelloweye"), rl.apply("ada_summoner"), rl.apply("ada_bolster"), rl.apply("ada_tozoon"), rl.apply("ada_arachnida"), rl.apply("ada_devourer"), rl.apply("ada_vermin"), rl.apply("ada_viscera"), rl.apply("ada_burrower"));
            PURE.addIds(rl.apply("overseer"), rl.apply("vigilante"), rl.apply("warden"), rl.apply("bomber_light"), rl.apply("marauder"), rl.apply("monarch"), rl.apply("grunt"));
            PREEMINENT.addIds(rl.apply("bomber_heavy"), rl.apply("wraith"), rl.apply("bogle"), rl.apply("haunter"), rl.apply("carrier_colony"), rl.apply("succor"), rl.apply("seeker"), rl.apply("architect"));
            ANCIENT.addIds(rl.apply("anc_dreadnaut"), rl.apply("anc_overlord"), rl.apply("anc_pod"), rl.apply("anc_dreadnaut_ten"));
            ScanRegistry.map(ItemModule.Kind.INBORN, ScanRegistry.profile("Inborn").add(INBORN));
            ScanRegistry.map(ItemModule.Kind.ASSIMILATED, ScanRegistry.profile("Assimilated").add(ASSIMILATED));
            ScanRegistry.map(ItemModule.Kind.HIJACKED, ScanRegistry.profile("Hijacked").add(HIJACKED));
            ScanRegistry.map(ItemModule.Kind.FERAL, ScanRegistry.profile("Feral").add(FERAL));
            ScanRegistry.map(ItemModule.Kind.CRUDE, ScanRegistry.profile("Crude").add(CRUDE));
            ScanRegistry.map(ItemModule.Kind.PRIMITIVE, ScanRegistry.profile("Primitive").add(PRIMITIVE));
            ScanRegistry.map(ItemModule.Kind.ADAPTED, ScanRegistry.profile("Adapted").add(ADAPTED));
            ScanRegistry.map(ItemModule.Kind.NEXUS, ScanRegistry.profile("Nexus").add(NEXUS));
            ScanRegistry.map(ItemModule.Kind.DETERRENT, ScanRegistry.profile("Deterrent").add(DETERRENT));
            ScanRegistry.map(ItemModule.Kind.PURE, ScanRegistry.profile("Pure").add(PURE));
            ScanRegistry.map(ItemModule.Kind.PREEMINENT, ScanRegistry.profile("Preeminent").add(PREEMINENT));
            ScanRegistry.map(ItemModule.Kind.ANCIENT, ScanRegistry.profile("Ancient").add(ANCIENT));
            ScanRegistry.map(ItemModule.Kind.ASSIMARA, ScanRegistry.profile("Assimara").add(ASSIMARA));
            ScanRegistry.map(ItemModule.Kind.DERIVED, ScanRegistry.profile("Derived").add(DERIVED));
            ScanRegistry.map(ItemModule.Kind.DESMOID, ScanRegistry.profile("Desmoid").add(INBORN, ASSIMARA, ASSIMILATED, HIJACKED));
            ScanRegistry.map(ItemModule.Kind.ESCHAR, ScanRegistry.profile("Eschar").add(FERAL, CRUDE, PRIMITIVE));
            ScanRegistry.map(ItemModule.Kind.RESISTANCE, ScanRegistry.profile("Resistance").add(ADAPTED, NEXUS, DETERRENT));
            ScanRegistry.map(ItemModule.Kind.IDEAL, ScanRegistry.profile("Ideal").add(PURE, PREEMINENT, DERIVED, ANCIENT));
            ScanRegistry.map(ItemModule.Kind.ORIGIN, ScanRegistry.profile("Origin").add(INBORN, ASSIMARA, ASSIMILATED, HIJACKED, FERAL, CRUDE, PRIMITIVE, ADAPTED, NEXUS, DETERRENT, PURE, PREEMINENT, DERIVED, ANCIENT));
        }

        private static Tier tier(String id) {
            Tier t = new Tier(id);
            TIERS.put(id, t);
            return t;
        }

        private static ModuleProfile profile(String name) {
            return new ModuleProfile(name);
        }

        private static void map(ItemModule.Kind kind, ModuleProfile mp) {
            MODULES.put(kind, mp);
        }

        @Nullable
        public static ModuleProfile getProfileFor(ItemModule.Kind kind) {
            ScanRegistry.ensureInit();
            return MODULES.get((Object)kind);
        }

        public static Collection<Tier> getAllTiers() {
            ScanRegistry.ensureInit();
            return Collections.unmodifiableCollection(TIERS.values());
        }

        public static final class ModuleProfile {
            public final String name;
            public final LinkedHashSet<Tier> tiers = new LinkedHashSet();

            ModuleProfile(String name) {
                this.name = name;
            }

            ModuleProfile add(Tier ... ts) {
                Collections.addAll(this.tiers, ts);
                return this;
            }
        }

        public static final class Tier {
            public final String id;
            private final Set<ResourceLocation> entityIds = new LinkedHashSet<ResourceLocation>();
            final String langKey;
            final Set<ResourceLocation> ids = new LinkedHashSet<ResourceLocation>();

            Tier(String id) {
                this.id = id;
                this.langKey = "tier.subspaceparasite." + id.toLowerCase(Locale.ROOT);
            }

            public String getDisplayName() {
                return I18n.func_74838_a((String)this.langKey);
            }

            void addIds(ResourceLocation ... rs) {
                Collections.addAll(this.ids, rs);
                Collections.addAll(this.entityIds, rs);
            }

            boolean matches(EntityLivingBase e) {
                ResourceLocation key = EntityList.func_191301_a((Entity)e);
                return key != null && this.ids.contains(key);
            }

            public Set<ResourceLocation> getEntityIds() {
                return Collections.unmodifiableSet(this.entityIds);
            }

            public String getIdLower() {
                return this.id == null ? "" : this.id.toLowerCase(Locale.ROOT);
            }
        }
    }
}

