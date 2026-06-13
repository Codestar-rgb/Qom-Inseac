/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SRPPacketParticle
implements IMessage {
    private double x;
    private double y;
    private double z;
    private float width;
    private float height;
    private byte type;

    public SRPPacketParticle() {
    }

    public SRPPacketParticle(double xx, double yy, double zz, float width, float height, byte particleType) {
        this.x = xx;
        this.y = yy;
        this.z = zz;
        this.width = width;
        this.height = height;
        this.type = particleType;
    }

    public void fromBytes(ByteBuf ByteBuf2) {
        this.x = ByteBuf2.readDouble();
        this.y = ByteBuf2.readDouble();
        this.z = ByteBuf2.readDouble();
        this.width = ByteBuf2.readFloat();
        this.height = ByteBuf2.readFloat();
        this.type = ByteBuf2.readByte();
    }

    public void toBytes(ByteBuf ByteBuf2) {
        ByteBuf2.writeDouble(this.x);
        ByteBuf2.writeDouble(this.y);
        ByteBuf2.writeDouble(this.z);
        ByteBuf2.writeFloat(this.width);
        ByteBuf2.writeFloat(this.height);
        ByteBuf2.writeByte((int)this.type);
    }

    public static class Handler
    implements IMessageHandler<SRPPacketParticle, IMessage> {
        public IMessage onMessage(SRPPacketParticle message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).func_152344_a(() -> this.handle(message, ctx));
            return null;
        }

        private void handle(SRPPacketParticle message, MessageContext ctx) {
            Random rand = new Random();
            switch (message.type) {
                case 1: {
                    this.spawnParticles(SRPEnumParticle.GSPLASH, rand, message, 0, 0, 0);
                    break;
                }
                case 2: {
                    for (int i = 0; i <= 3; ++i) {
                        this.spawnParticles(SRPEnumParticle.GCLOUD, rand, message, 0, 0, 0);
                    }
                    break;
                }
                case 3: {
                    for (int i = 0; i <= 5; ++i) {
                        this.spawnParticles(SRPEnumParticle.RHAPPY, rand, message, 0, 0, 0);
                    }
                    break;
                }
                case 4: {
                    for (int i = 0; i <= 23; ++i) {
                        this.spawnParticles(SRPEnumParticle.GCLOUD, rand, message, 125, 227, 118);
                    }
                    break;
                }
                case 5: {
                    break;
                }
                case 10: {
                    int i;
                    this.spawnParticles(SRPEnumParticle.GSPLASH, rand, message, 0, 0, 0);
                    this.spawnParticles(SRPEnumParticle.GSPLASH, rand, message, 0, 0, 0);
                    this.spawnParticles(SRPEnumParticle.GSPLASH, rand, message, 0, 0, 0);
                    for (i = 0; i <= 20; ++i) {
                        if (i % 5 != 0) continue;
                        this.spawnParticlesGore(message, rand, SRPEnumParticle.GSPLASH, 0, -1, -1);
                    }
                    for (i = 0; i <= 20; ++i) {
                        if (i % 5 != 0) continue;
                        this.spawnParticles(SRPEnumParticle.GCLOUD, rand, message, 127, 0, 0);
                    }
                    break;
                }
                case 11: {
                    int i;
                    for (i = 0; i <= 160; ++i) {
                        if (i % 5 != 0) continue;
                        this.spawnParticlesGore(message, rand, SRPEnumParticle.GSPLASH, 0, -1, -1);
                    }
                    for (i = 0; i <= 60; ++i) {
                        if (i % 5 != 0) continue;
                        this.spawnParticles(SRPEnumParticle.GCLOUD, rand, message, 127, 0, 0);
                    }
                    break;
                }
            }
        }

        private void spawnParticles(SRPEnumParticle particleType, Random rand, SRPPacketParticle message, int r, int g, int b) {
            double d0 = rand.nextGaussian() * 0.02;
            double d1 = rand.nextGaussian() * 0.02;
            double d2 = rand.nextGaussian() * 0.02;
            ParticleSpawner.spawnParticle(particleType, message.x + (double)(rand.nextFloat() * message.width * 2.0f) - (double)message.width, message.y + 0.5 + (double)(rand.nextFloat() * message.height), message.z + (double)(rand.nextFloat() * message.width * 2.0f) - (double)message.width, d0, d1, d2, r, g, b);
        }

        private void spawnParticlesGore(SRPPacketParticle message, Random ran, SRPEnumParticle particleType, int r, int g, int b) {
            this.spawnParticlesGore(message, ran, particleType, r, g, b, 1.0, 4.0);
        }

        private void spawnParticlesGore(SRPPacketParticle message, Random ran, SRPEnumParticle particleType, int r, int g, int b, double xF, double yF) {
            Random rand = new Random();
            double d0 = (float)message.x + rand.nextFloat();
            double d1 = (float)message.y + rand.nextFloat();
            double d2 = (float)message.z + rand.nextFloat();
            double d3 = d0 - message.x;
            double d4 = d1 - message.y;
            double d5 = d2 - message.z;
            double d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            double d7 = 0.5 / (d6 / 4.0 + 0.1);
            d3 = d3 * (d7 *= (double)(rand.nextFloat() * rand.nextFloat() + 0.3f)) * xF;
            d4 = d4 * d7 * yF;
            d5 = d5 * d7 * xF;
            d3 = Math.min(d3, 0.2) * (Math.random() * 2.0 - 1.0);
            d4 = Math.min(d4, 0.6) * (Math.random() * 2.0 - 1.0);
            d5 = Math.min(d5, 0.2) * (Math.random() * 2.0 - 1.0);
            ParticleSpawner.spawnParticle(particleType, message.x, message.y + 0.2 + 1.0, message.z, d3, d4, d5, r, g, b);
        }
    }
}

