/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.MapStorage
 *  net.minecraft.world.storage.WorldSavedData
 */
package com.subspaceparasite.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class ExtremeSnowData
extends WorldSavedData {
    private static final String KEY = "srp_extreme_snow";
    private boolean enabled = false;
    private float intensity = 1.0f;
    private boolean forceAnywhere = true;
    private float windDeg = 30.0f;
    private float windSpeed = 0.5f;

    public ExtremeSnowData() {
        super(KEY);
    }

    public ExtremeSnowData(String name) {
        super(name);
    }

    public static ExtremeSnowData get(World world) {
        String dimKey;
        MapStorage storage = world.getPerWorldStorage();
        ExtremeSnowData data = (ExtremeSnowData)storage.func_75742_a(ExtremeSnowData.class, dimKey = "srp_extreme_snow_" + world.field_73011_w.getDimension());
        if (data == null) {
            data = new ExtremeSnowData(dimKey);
            storage.func_75745_a(dimKey, (WorldSavedData)data);
        }
        return data;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public float getIntensity() {
        return this.intensity;
    }

    public void setEnabled(boolean e) {
        this.enabled = e;
    }

    public void setIntensity(float i) {
        this.intensity = i;
    }

    public boolean isForceAnywhere() {
        return this.forceAnywhere;
    }

    public void setForceAnywhere(boolean b) {
        this.forceAnywhere = b;
    }

    public float getWindDeg() {
        return this.windDeg;
    }

    public void setWindDeg(float d) {
        this.windDeg = d;
    }

    public float getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindSpeed(float s) {
        this.windSpeed = s;
    }

    public void func_76184_a(NBTTagCompound nbt) {
        this.enabled = nbt.func_74767_n("enabled");
        this.intensity = nbt.func_74760_g("intensity");
        this.forceAnywhere = nbt.func_74767_n("forceAnywhere");
        this.windDeg = nbt.func_74760_g("windDeg");
        this.windSpeed = nbt.func_74760_g("windSpeed");
    }

    public NBTTagCompound func_189551_b(NBTTagCompound nbt) {
        nbt.func_74757_a("enabled", this.enabled);
        nbt.func_74776_a("intensity", this.intensity);
        nbt.func_74757_a("forceAnywhere", this.forceAnywhere);
        nbt.func_74776_a("windDeg", this.windDeg);
        nbt.func_74776_a("windSpeed", this.windSpeed);
        return nbt;
    }
}

