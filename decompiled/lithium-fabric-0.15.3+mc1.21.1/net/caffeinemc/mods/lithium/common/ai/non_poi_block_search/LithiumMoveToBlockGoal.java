package net.caffeinemc.mods.lithium.common.ai.non_poi_block_search;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2338.class_2339;

public interface LithiumMoveToBlockGoal {
   boolean lithium$findNearestBlock(Predicate<class_2680> var1, BiPredicate<class_2791, class_2339> var2, boolean var3);
}
