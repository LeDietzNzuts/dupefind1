package com.blamejared.clumps;

import java.util.function.BiPredicate;
import net.minecraft.class_1303;
import net.minecraft.class_1657;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClumpsCommon {
   public static final Logger LOG = LogManager.getLogger("clumps");
   public static BiPredicate<class_1657, class_1303> pickupXPEvent = (player, experienceOrb) -> false;
}
