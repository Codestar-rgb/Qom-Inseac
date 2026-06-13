/*
 * Decompiled with CFR 0.152.
 */
package com.dhanantry.scapeandrunparasites.util;

public class LangHelper {
    public static String toRoman(int n) {
        switch (n) {
            case 1: {
                return "\u00a7aI";
            }
            case 2: {
                return "\u00a7eII";
            }
            case 3: {
                return "\u00a76III";
            }
            case 4: {
                return "\u00a7cIV";
            }
            case 5: {
                return "\u00a7dV";
            }
        }
        return Integer.toString(n);
    }
}

