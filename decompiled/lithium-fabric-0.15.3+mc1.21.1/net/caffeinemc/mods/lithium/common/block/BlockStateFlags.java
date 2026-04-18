package net.caffeinemc.mods.lithium.common.block;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Reference2BooleanArrayMap;
import it.unimi.dsi.fastutil.objects.Reference2BooleanMap.Entry;
import java.util.ArrayList;
import net.caffeinemc.mods.lithium.common.ai.pathing.BlockStatePathingCache;
import net.caffeinemc.mods.lithium.common.ai.pathing.PathNodeCache;
import net.caffeinemc.mods.lithium.common.entity.FluidCachingEntity;
import net.caffeinemc.mods.lithium.common.reflection.ReflectionUtil;
import net.minecraft.class_1297;
import net.minecraft.class_2246;
import net.minecraft.class_2680;
import net.minecraft.class_2826;
import net.minecraft.class_3481;
import net.minecraft.class_3486;
import net.minecraft.class_7;
import net.minecraft.class_4970.class_4971;

public class BlockStateFlags {
   public static final boolean ENABLED = BlockCountingSection.class.isAssignableFrom(class_2826.class);
   public static final int NUM_LISTENING_FLAGS;
   public static final ListeningBlockStatePredicate[] LISTENING_FLAGS;
   public static final int LISTENING_MASK_OR;
   public static final ListeningBlockStatePredicate ANY;
   public static final int NUM_TRACKED_FLAGS;
   public static final TrackedBlockStatePredicate[] TRACKED_FLAGS;
   public static final TrackedBlockStatePredicate OVERSIZED_SHAPE;
   public static final TrackedBlockStatePredicate PATH_NOT_OPEN;
   public static final TrackedBlockStatePredicate WATER;
   public static final TrackedBlockStatePredicate LAVA;
   public static final TrackedBlockStatePredicate[] FLAGS;
   public static final TrackedBlockStatePredicate ENTITY_TOUCHABLE;

   static {
      Reference2BooleanArrayMap<ListeningBlockStatePredicate> listeningFlags = new Reference2BooleanArrayMap();
      ANY = new ListeningBlockStatePredicate(listeningFlags.size()) {
         public boolean test(class_2680 operand) {
            return true;
         }
      };
      listeningFlags.put(ANY, false);
      NUM_LISTENING_FLAGS = listeningFlags.size();
      int listenMaskOR = 0;
      int iteration = 0;
      ObjectIterator countingFlags = listeningFlags.reference2BooleanEntrySet().iterator();

      while (countingFlags.hasNext()) {
         Entry<ListeningBlockStatePredicate> entry = (Entry<ListeningBlockStatePredicate>)countingFlags.next();
         boolean listenOnlyXOR = entry.getBooleanValue();
         listenMaskOR |= listenOnlyXOR ? 0 : 1 << iteration;
      }

      LISTENING_MASK_OR = listenMaskOR;
      LISTENING_FLAGS = (ListeningBlockStatePredicate[])listeningFlags.keySet().toArray(new ListeningBlockStatePredicate[NUM_LISTENING_FLAGS]);
      ArrayList<TrackedBlockStatePredicate> countingFlagsx = new ArrayList<>(listeningFlags.keySet());
      OVERSIZED_SHAPE = new TrackedBlockStatePredicate(countingFlagsx.size()) {
         public boolean test(class_2680 operand) {
            return operand.method_26209();
         }
      };
      countingFlagsx.add(OVERSIZED_SHAPE);
      if (FluidCachingEntity.class.isAssignableFrom(class_1297.class)) {
         WATER = new TrackedBlockStatePredicate(countingFlagsx.size()) {
            public boolean test(class_2680 operand) {
               return operand.method_26227().method_15772().method_15791(class_3486.field_15517);
            }
         };
         countingFlagsx.add(WATER);
         LAVA = new TrackedBlockStatePredicate(countingFlagsx.size()) {
            public boolean test(class_2680 operand) {
               return operand.method_26227().method_15772().method_15791(class_3486.field_15518);
            }
         };
         countingFlagsx.add(LAVA);
      } else {
         WATER = null;
         LAVA = null;
      }

      if (BlockStatePathingCache.class.isAssignableFrom(class_4971.class)) {
         PATH_NOT_OPEN = new TrackedBlockStatePredicate(countingFlagsx.size()) {
            public boolean test(class_2680 operand) {
               return PathNodeCache.getNeighborPathNodeType(operand) != class_7.field_7;
            }
         };
         countingFlagsx.add(PATH_NOT_OPEN);
      } else {
         PATH_NOT_OPEN = null;
      }

      NUM_TRACKED_FLAGS = countingFlagsx.size();
      TRACKED_FLAGS = countingFlagsx.toArray(new TrackedBlockStatePredicate[NUM_TRACKED_FLAGS]);
      ArrayList<TrackedBlockStatePredicate> flags = new ArrayList<>(countingFlagsx);
      ENTITY_TOUCHABLE = new TrackedBlockStatePredicate(countingFlagsx.size()) {
         public boolean test(class_2680 operand) {
            return ReflectionUtil.isBlockStateEntityTouchable(operand)
               || operand.method_27852(class_2246.field_10164)
               || operand.method_26164(class_3481.field_21952);
         }
      };
      flags.add(ENTITY_TOUCHABLE);
      FLAGS = flags.toArray(new TrackedBlockStatePredicate[0]);
   }
}
