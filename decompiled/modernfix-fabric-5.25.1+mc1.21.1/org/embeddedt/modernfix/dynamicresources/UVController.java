package org.embeddedt.modernfix.dynamicresources;

import net.minecraft.class_787;

public class UVController {
   public static final ThreadLocal<Boolean> useDummyUv = ThreadLocal.withInitial(() -> Boolean.FALSE);
   public static final class_787 dummyUv = new class_787(new float[4], 0);
}
