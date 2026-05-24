/*
 * Decompiled with CFR 0.152.
 */
package com.subspaceparasite.phase;

import com.subspaceparasite.phase.EvoPhase;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EvoPhases {
    public List<EvoPhase> phases;
    public EvoPhase currentPhase;

    public void addPhase(EvoPhase phase) {
        this.phases.add(phase);
    }

    public void sortPhasesByThreshold() {
        this.phases.sort(Comparator.comparingInt(EvoPhase::getPointThreshold));
        Collections.reverse(this.phases);
        this.verifyPhaseOrder();
    }

    public void verifyPhaseOrder() {
        for (int i = 0; i < this.phases.size() - 1; ++i) {
            EvoPhase current = this.phases.get(i);
            EvoPhase next = this.phases.get(i + 1);
            if (current.getPointThreshold() >= next.getPointThreshold()) continue;
            throw new IllegalStateException("Phases are not in correct order based on point thresholds.");
        }
    }

    public void updateCurrentPhase(int totalPoints) {
        for (EvoPhase phase : this.phases) {
            if (totalPoints < phase.getPointThreshold()) continue;
            this.currentPhase = phase;
            break;
        }
    }

    public EvoPhase getCurrentPhase() {
        return this.currentPhase;
    }
}

