package com.dhanantry.scapeandrunparasites.util;

public class LangHelper {
   public static String toRoman(int n) {
      switch (n) {
         case 1:
            return "§aI";
         case 2:
            return "§eII";
         case 3:
            return "§6III";
         case 4:
            return "§cIV";
         case 5:
            return "§dV";
         default:
            return Integer.toString(n);
      }
   }
}
