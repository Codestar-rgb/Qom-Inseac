/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationaryArchitect;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.world.gen.feature.WorldGenParasiteNexusProtection1;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public abstract class EntityPDispatcher
extends EntityPStationaryArchitect {
    protected ArrayList<String> mobNameId = new ArrayList();
    protected int crude;
    protected int inf;
    protected int mangler;
    protected int flesh;

    public EntityPDispatcher(World worldIn) {
        super(worldIn);
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
        this.setScentHPMultiplier(0.5f);
    }

    public boolean storeLodo(EntityParasiteBase in, boolean upgrade) {
        if (in.getParasiteIDRegister() == 5 && upgrade) {
            this.mobNameId.add("srparasites:rupter");
            in.particleStatus((byte)7);
            in.func_70106_y();
            return true;
        }
        if (in.getParasiteIDRegister() == 23 && upgrade) {
            in.particleStatus((byte)7);
            in.func_70106_y();
            ++this.flesh;
            if (this.flesh >= 2) {
                this.flesh -= 2;
                this.addPrim();
                return true;
            }
            return true;
        }
        return false;
    }

    protected boolean storeInf(EntityParasiteBase in, boolean upgrade) {
        if ((in instanceof EntityPInfected || in.getParasiteIDRegister() == 23) && upgrade) {
            ++this.inf;
            this.mobNameId.add(Objects.requireNonNull(EntityList.func_191301_a((Entity)in)).toString());
            in.particleStatus((byte)7);
            in.func_70106_y();
            if (this.inf >= 4 && this.mobNameId != null) {
                this.inf -= 4;
                int current = 0;
                for (int i = 0; i < this.mobNameId.size(); ++i) {
                    if (this.mobNameId.get(i) == null) {
                        this.mobNameId.remove(i);
                        --i;
                        continue;
                    }
                    if (!this.mobNameId.get(i).contains("sim_")) continue;
                    this.mobNameId.remove(i);
                    i = -1;
                    if (++current < 4) continue;
                    this.addPrim();
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    protected boolean storeCrude(EntityParasiteBase in, boolean upgrade) {
        if ((in.getParasiteIDRegister() == 43 || in.getParasiteIDRegister() == 39) && upgrade) {
            this.crude = in.getParasiteIDRegister() == 39 ? ++this.crude : (this.crude += 2);
            this.mobNameId.add(Objects.requireNonNull(EntityList.func_191301_a((Entity)in)).toString());
            in.particleStatus((byte)7);
            in.func_70106_y();
            if (this.crude >= 6 && this.mobNameId != null) {
                this.crude -= 6;
                int current = 0;
                for (int i = 0; i < this.mobNameId.size(); ++i) {
                    if (this.mobNameId.get(i) == null) {
                        this.mobNameId.remove(i);
                        --i;
                        continue;
                    }
                    if (!this.mobNameId.get(i).contains("incompleteform")) continue;
                    this.mobNameId.remove(i);
                    i = -1;
                    if (++current < 6) continue;
                    this.mobNameId.add("srparasites:heed");
                    break;
                }
            }
            return true;
        }
        return false;
    }

    protected boolean storeMudo(EntityParasiteBase in, boolean upgrade) {
        if (in.getParasiteIDRegister() == 12 && upgrade) {
            this.mobNameId.add("srparasites:mangler");
            in.particleStatus((byte)7);
            in.func_70106_y();
            return true;
        }
        return false;
    }

    protected boolean storeMangler(EntityParasiteBase in, boolean upgrade) {
        if (in.getParasiteIDRegister() == 76 && upgrade) {
            ++this.mangler;
            this.mobNameId.add(Objects.requireNonNull(EntityList.func_191301_a((Entity)in)).toString());
            in.particleStatus((byte)7);
            in.func_70106_y();
            return true;
        }
        return false;
    }

    protected boolean storeAll(EntityParasiteBase in) {
        this.mobNameId.add(Objects.requireNonNull(EntityList.func_191301_a((Entity)in)).toString());
        in.particleStatus((byte)7);
        in.func_70106_y();
        return true;
    }

    public boolean storeParasite(EntityParasiteBase in) {
        if (!in.func_70089_S()) {
            return true;
        }
        if (in.getParasiteIDRegister() == 36) {
            return true;
        }
        float bHard = 0.0f;
        IBlockState state2 = this.field_70170_p.func_180495_p(this.func_180425_c().func_177977_b());
        float atm = state2.func_185887_b(this.field_70170_p, this.func_180425_c().func_177977_b());
        if (atm <= 0.0f) {
            return true;
        }
        bHard += atm;
        state2 = this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(2));
        atm = state2.func_185887_b(this.field_70170_p, this.func_180425_c().func_177979_c(2));
        if (atm <= 0.0f) {
            return true;
        }
        bHard += atm;
        state2 = this.field_70170_p.func_180495_p(this.func_180425_c().func_177979_c(3));
        atm = state2.func_185887_b(this.field_70170_p, this.func_180425_c().func_177979_c(3));
        if (atm <= 0.0f) {
            return true;
        }
        return (bHard += atm) >= 10.0f;
    }

    private void addPrim() {
        int prim = this.field_70146_Z.nextInt(7);
        boolean canAdd = true;
        for (int safe = 0; canAdd && safe < 10; ++safe) {
            switch (prim) {
                case 0: {
                    if (!SRPConfigMobs.emanaEnabled) break;
                    this.mobNameId.add("srparasites:pri_yelloweye");
                    canAdd = false;
                    break;
                }
                case 1: {
                    if (!SRPConfigMobs.canraEnabled) break;
                    this.mobNameId.add("srparasites:pri_summoner");
                    canAdd = false;
                    break;
                }
                case 2: {
                    if (!SRPConfigMobs.zetmoEnabled) break;
                    this.mobNameId.add("srparasites:pri_bolster");
                    canAdd = false;
                    break;
                }
                case 3: {
                    if (!SRPConfigMobs.shycoEnabled) break;
                    this.mobNameId.add("srparasites:pri_longarms");
                    canAdd = false;
                    break;
                }
                case 4: {
                    if (!SRPConfigMobs.arachnidaEnabled) break;
                    this.mobNameId.add("srparasites:pri_arachnida");
                    canAdd = false;
                    break;
                }
                case 5: {
                    if (!SRPConfigMobs.noglaEnabled) break;
                    this.mobNameId.add("srparasites:pri_reeker");
                    canAdd = false;
                    break;
                }
                case 6: {
                    if (!SRPConfigMobs.hullEnabled) break;
                    this.mobNameId.add("srparasites:pri_manducater");
                    canAdd = false;
                }
            }
            if (++prim < 7) continue;
            prim = 0;
        }
    }

    public String getParasiteStored() {
        if (this.mobNameId.isEmpty()) {
            return null;
        }
        int j = this.field_70146_Z.nextInt(this.mobNameId.size());
        String name = this.mobNameId.get(j);
        if (name == null) {
            return null;
        }
        if (name.contains("sim_")) {
            --this.inf;
        } else if (name.contains("incompleteform_small")) {
            --this.crude;
        } else if (name.contains("incompleteform_medium")) {
            this.crude -= 2;
        } else if (name.contains("mangler")) {
            --this.mangler;
        }
        this.mobNameId.remove(j);
        return name;
    }

    public void addParaBack(String id) {
        this.mobNameId.add(id);
    }

    public ArrayList<String> getMobList() {
        return this.mobNameId;
    }

    @Override
    public void generateStructure() {
        if (SRPConfig.nexusStructures) {
            WorldGenParasiteNexusProtection1 p1 = new WorldGenParasiteNexusProtection1(false, 1);
            p1.func_180709_b(this.field_70170_p, new Random(), this.func_180425_c().func_177977_b());
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74768_a("dodcrude", this.crude);
        compound.func_74768_a("dodinf", this.inf);
        compound.func_74768_a("dodflesh", this.flesh);
        NBTTagList allResS = new NBTTagList();
        for (int i = 0; i < this.mobNameId.size(); ++i) {
            String res = this.mobNameId.get(i);
            if (res == null) continue;
            NBTTagCompound resT = new NBTTagCompound();
            resT.func_74778_a("mobs" + i, res);
            allResS.func_74742_a((NBTBase)resT);
        }
        compound.func_74782_a("dodmobs", (NBTBase)allResS);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("dodcrude", 99)) {
            this.crude = compound.func_74762_e("dodcrude");
        }
        if (compound.func_150297_b("dodinf", 99)) {
            this.inf = compound.func_74762_e("dodinf");
        }
        if (compound.func_150297_b("dodflesh", 99)) {
            this.flesh = compound.func_74762_e("dodflesh");
        }
        if (compound.func_74764_b("dodmobs")) {
            NBTTagList allResS = compound.func_150295_c("dodmobs", 10);
            for (int i = 0; i < allResS.func_74745_c(); ++i) {
                NBTTagCompound resT = allResS.func_150305_b(i);
                String res = resT.func_74779_i("mobs" + i);
                this.mobNameId.add(i, res);
            }
        }
    }
}

