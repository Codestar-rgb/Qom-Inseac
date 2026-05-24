/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.ISoundEventListener
 *  net.minecraft.client.audio.SoundEventAccessor
 *  net.minecraft.client.gui.GuiSubtitleOverlay
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package com.subspaceparasite.client.gui;

import com.subspaceparasite.bestiary.client.gui.GuiDistortionHelper;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventListener;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.gui.GuiSubtitleOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class DistortedGuiSubtitleOverlay
extends GuiSubtitleOverlay {
    private final Minecraft client;
    private final List<Subtitle> subtitles = Lists.newArrayList();
    private boolean enabled;

    public DistortedGuiSubtitleOverlay(Minecraft clientIn) {
        super(clientIn);
        this.client = clientIn;
    }

    public void func_184068_a(ScaledResolution resolution) {
        if (!this.enabled && this.client.field_71474_y.field_186717_N) {
            this.client.func_147118_V().func_184402_a((ISoundEventListener)this);
            this.enabled = true;
        } else if (this.enabled && !this.client.field_71474_y.field_186717_N) {
            this.client.func_147118_V().func_184400_b((ISoundEventListener)this);
            this.enabled = false;
        }
        if (this.enabled && !this.subtitles.isEmpty()) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
            Vec3d vec3d = new Vec3d(this.client.field_71439_g.field_70165_t, this.client.field_71439_g.field_70163_u + (double)this.client.field_71439_g.func_70047_e(), this.client.field_71439_g.field_70161_v);
            Vec3d vec3d1 = new Vec3d(0.0, 0.0, -1.0).func_178789_a(-this.client.field_71439_g.field_70125_A * ((float)Math.PI / 180)).func_178785_b(-this.client.field_71439_g.field_70177_z * ((float)Math.PI / 180));
            Vec3d vec3d2 = new Vec3d(0.0, 1.0, 0.0).func_178789_a(-this.client.field_71439_g.field_70125_A * ((float)Math.PI / 180)).func_178785_b(-this.client.field_71439_g.field_70177_z * ((float)Math.PI / 180));
            Vec3d vec3d3 = vec3d1.func_72431_c(vec3d2);
            int i = 0;
            int j = 0;
            Iterator<Subtitle> iterator = this.subtitles.iterator();
            while (iterator.hasNext()) {
                Subtitle subtitle = iterator.next();
                if (subtitle.getStartTime() + 3000L <= Minecraft.func_71386_F()) {
                    iterator.remove();
                    continue;
                }
                String measure = GuiDistortionHelper.shouldDistortSubtitles(this.client) ? GuiDistortionHelper.jamText(subtitle.getString()) : subtitle.getString();
                j = Math.max(j, this.client.field_71466_p.func_78256_a(measure));
            }
            j = j + this.client.field_71466_p.func_78256_a("<") + this.client.field_71466_p.func_78256_a(" ") + this.client.field_71466_p.func_78256_a(">") + this.client.field_71466_p.func_78256_a(" ");
            for (Subtitle subtitle : this.subtitles) {
                String s = subtitle.getString();
                if (GuiDistortionHelper.shouldDistortSubtitles(this.client)) {
                    s = GuiDistortionHelper.jamText(s);
                }
                Vec3d vec3d4 = subtitle.getLocation().func_178788_d(vec3d).func_72432_b();
                double d0 = -vec3d3.func_72430_b(vec3d4);
                double d1 = -vec3d1.func_72430_b(vec3d4);
                boolean flag = d1 > 0.5;
                int l = j / 2;
                int i1 = this.client.field_71466_p.field_78288_b;
                int j1 = i1 / 2;
                int k1 = this.client.field_71466_p.func_78256_a(s);
                int l1 = MathHelper.func_76128_c((double)MathHelper.func_151238_b((double)255.0, (double)75.0, (double)((float)(Minecraft.func_71386_F() - subtitle.getStartTime()) / 3000.0f)));
                int i2 = l1 << 16 | l1 << 8 | l1;
                GlStateManager.func_179094_E();
                GlStateManager.func_179109_b((float)((float)resolution.func_78326_a() - (float)l - 2.0f), (float)((float)(resolution.func_78328_b() - 30) - (float)(i * (i1 + 1))), (float)0.0f);
                DistortedGuiSubtitleOverlay.func_73734_a((int)(-l - 1), (int)(-j1 - 1), (int)(l + 1), (int)(j1 + 1), (int)-872415232);
                GlStateManager.func_179147_l();
                if (!flag) {
                    if (d0 > 0.0) {
                        this.client.field_71466_p.func_78276_b(">", l - this.client.field_71466_p.func_78256_a(">"), -j1, i2 + -16777216);
                    } else if (d0 < 0.0) {
                        this.client.field_71466_p.func_78276_b("<", -l, -j1, i2 + -16777216);
                    }
                }
                this.client.field_71466_p.func_78276_b(s, -k1 / 2, -j1, i2 + -16777216);
                GlStateManager.func_179121_F();
                ++i;
            }
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
        }
    }

    public void func_184067_a(ISound soundIn, SoundEventAccessor accessor) {
        if (accessor.func_188712_c() != null) {
            String s = accessor.func_188712_c().func_150254_d();
            for (Subtitle subtitle : this.subtitles) {
                if (!subtitle.getString().equals(s)) continue;
                subtitle.refresh(new Vec3d((double)soundIn.func_147649_g(), (double)soundIn.func_147654_h(), (double)soundIn.func_147651_i()));
                return;
            }
            this.subtitles.add(new Subtitle(s, new Vec3d((double)soundIn.func_147649_g(), (double)soundIn.func_147654_h(), (double)soundIn.func_147651_i())));
        }
    }

    private static class Subtitle {
        private final String subtitle;
        private long startTime;
        private Vec3d location;

        public Subtitle(String subtitleIn, Vec3d locationIn) {
            this.subtitle = subtitleIn;
            this.location = locationIn;
            this.startTime = Minecraft.func_71386_F();
        }

        public String getString() {
            return this.subtitle;
        }

        public long getStartTime() {
            return this.startTime;
        }

        public Vec3d getLocation() {
            return this.location;
        }

        public void refresh(Vec3d locationIn) {
            this.location = locationIn;
            this.startTime = Minecraft.func_71386_F();
        }
    }
}

