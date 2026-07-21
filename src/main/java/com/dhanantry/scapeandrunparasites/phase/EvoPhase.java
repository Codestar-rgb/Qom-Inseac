package com.dhanantry.scapeandrunparasites.phase;

public class EvoPhase {
   private final int phaseNumber;
   private final int pointThreshold;
   private final int originCount;
   private final int phaseOriginBonusHealth;
   private final int phaseOriginPenalty;
   private final int phaseScentBonus;
   private final int pointGainDelay;
   private final String[] phaseSpawnEntries;
   private final String phaseWarningMessage;

   public EvoPhase(
      int phaseNumber,
      int pointThreshold,
      int originCount,
      int phaseOriginBonusHealth,
      int phaseOriginPenalty,
      int phaseScentBonus,
      int pointGainDelay,
      String[] phaseSpawnEntries,
      String phaseWarningMessage
   ) {
      this.phaseNumber = phaseNumber;
      this.pointThreshold = pointThreshold;
      this.originCount = originCount;
      this.phaseOriginBonusHealth = phaseOriginBonusHealth;
      this.phaseOriginPenalty = phaseOriginPenalty;
      this.phaseScentBonus = phaseScentBonus;
      this.pointGainDelay = pointGainDelay;
      this.phaseSpawnEntries = phaseSpawnEntries;
      this.phaseWarningMessage = phaseWarningMessage;
   }

   public int getPhaseNumber() {
      return this.phaseNumber;
   }

   public int getPointThreshold() {
      return this.pointThreshold;
   }

   public int getOriginCount() {
      return this.originCount;
   }

   public int getPhaseOriginBonusHealth() {
      return this.phaseOriginBonusHealth;
   }

   public int getPhaseOriginPenalty() {
      return this.phaseOriginPenalty;
   }

   public int getPhaseScentBonus() {
      return this.phaseScentBonus;
   }

   public int getPointGainDelay() {
      return this.pointGainDelay;
   }

   public String[] getPhaseSpawnEntries() {
      return this.phaseSpawnEntries;
   }

   public String getPhaseWarningMessage() {
      return this.phaseWarningMessage;
   }

   public static class Builder {
      private int phaseNumber = 0;
      private int pointThreshold = 0;
      private int originCount = 0;
      private int phaseOriginBonusHealth = 0;
      private int phaseOriginPenalty = 0;
      private int phaseScentBonus = 0;
      private int pointGainDelay = 0;
      private String[] phaseSpawnEntries = new String[0];
      private String phaseWarningMessage = "";

      public EvoPhase.Builder setPhaseNumber(int phaseNumber) {
         this.phaseNumber = phaseNumber;
         return this;
      }

      public EvoPhase.Builder setPointThreshold(int pointThreshold) {
         this.pointThreshold = pointThreshold;
         return this;
      }

      public EvoPhase.Builder setOriginCount(int originCount) {
         this.originCount = originCount;
         return this;
      }

      public EvoPhase.Builder setPhaseOriginPenalty(int phaseOriginPenalty) {
         this.phaseOriginPenalty = phaseOriginPenalty;
         return this;
      }

      public EvoPhase.Builder setPhaseScentBonus(int phaseScentBonus) {
         this.phaseScentBonus = phaseScentBonus;
         return this;
      }

      public EvoPhase.Builder setPointGainDelay(int pointGainDelay) {
         this.pointGainDelay = pointGainDelay;
         return this;
      }

      public EvoPhase.Builder setPhaseSpawnEntries(String[] phaseSpawnEntries) {
         this.phaseSpawnEntries = phaseSpawnEntries;
         return this;
      }

      public EvoPhase.Builder setPhaseWarningMessage(String phaseWarningMessage) {
         this.phaseWarningMessage = phaseWarningMessage;
         return this;
      }

      public EvoPhase.Builder setPhaseOriginBonusHealth(int phaseOriginBonusHealth) {
         this.phaseOriginBonusHealth = phaseOriginBonusHealth;
         return this;
      }

      public EvoPhase build() {
         return new EvoPhase(
            this.phaseNumber,
            this.pointThreshold,
            this.originCount,
            this.phaseOriginBonusHealth,
            this.phaseOriginPenalty,
            this.phaseScentBonus,
            this.pointGainDelay,
            this.phaseSpawnEntries,
            this.phaseWarningMessage
         );
      }
   }
}
