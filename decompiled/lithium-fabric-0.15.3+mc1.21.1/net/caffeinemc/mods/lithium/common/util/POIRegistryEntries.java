package net.caffeinemc.mods.lithium.common.util;

import net.minecraft.class_2244;
import net.minecraft.class_2246;
import net.minecraft.class_2680;
import net.minecraft.class_2742;
import net.minecraft.class_4158;
import net.minecraft.class_6880;
import net.minecraft.class_7477;

public class POIRegistryEntries {
   public static final class_6880<class_4158> NETHER_PORTAL_ENTRY = (class_6880<class_4158>)class_7477.method_43989(class_2246.field_10316.method_9564())
      .orElseThrow(() -> new IllegalStateException("Nether portal poi type not found"));
   public static final class_6880<class_4158> HOME_ENTRY = (class_6880<class_4158>)class_7477.method_43989(
         (class_2680)class_2246.field_10069.method_9564().method_11657(class_2244.field_9967, class_2742.field_12560)
      )
      .orElseThrow(() -> new IllegalStateException("Home poi type not found"));
}
