/*
 * Decompiled with CFR 0.152.
 */
package com.subspaceparasite.util.handlers;

import com.subspaceparasite.network.SPPacketBiomeChangeBatch;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiomeUpdateQueue {
    private static final Deque<BiomeUpdate> queue = new ArrayDeque<BiomeUpdate>();

    public static synchronized void enqueue(int x, int y, int z, boolean convert, int type, int dimension) {
        if (queue.size() > 10000) {
            return;
        }
        queue.addLast(new BiomeUpdate(x, y, z, convert, type, dimension));
    }

    public static synchronized Deque<BiomeUpdate> drainUpTo(int max) {
        ArrayDeque<BiomeUpdate> out = new ArrayDeque<BiomeUpdate>();
        for (int i = 0; i < max && !queue.isEmpty(); ++i) {
            out.addLast(queue.pollFirst());
        }
        return out;
    }

    public static int size() {
        return queue.size();
    }

    public static Map<Integer, List<SPPacketBiomeChangeBatch.Entry>> buildBatches(Deque<BiomeUpdate> drained) {
        HashMap<Integer, List<SPPacketBiomeChangeBatch.Entry>> batches = new HashMap<Integer, List<SPPacketBiomeChangeBatch.Entry>>();
        for (BiomeUpdate u : drained) {
            batches.computeIfAbsent(u.dimension, k -> new ArrayList()).add(new SPPacketBiomeChangeBatch.Entry(u.x, u.y, u.z, u.convert, u.type));
        }
        return batches;
    }

    public static class BiomeUpdate {
        public final int x;
        public final int y;
        public final int z;
        public final boolean convert;
        public final int type;
        public final int dimension;

        public BiomeUpdate(int x, int y, int z, boolean convert, int type, int dimension) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.convert = convert;
            this.type = type;
            this.dimension = dimension;
        }
    }
}

