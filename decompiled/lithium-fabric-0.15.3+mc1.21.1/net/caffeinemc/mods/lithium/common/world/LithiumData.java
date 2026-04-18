package net.caffeinemc.mods.lithium.common.world;

import it.unimi.dsi.fastutil.longs.Long2ReferenceOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import java.util.Objects;
import net.caffeinemc.mods.lithium.common.tracking.block.ChunkSectionChangeCallback;
import net.caffeinemc.mods.lithium.common.tracking.block.SectionedBlockChangeTracker;
import net.caffeinemc.mods.lithium.common.tracking.entity.SectionedEntityMovementTracker;
import net.caffeinemc.mods.lithium.common.util.deduplication.LithiumInterner;
import net.minecraft.class_1408;
import net.minecraft.class_1799;
import net.minecraft.class_3765;
import net.minecraft.class_5455;
import net.minecraft.class_7924;
import net.minecraft.class_7225.class_7874;

public interface LithiumData {
   LithiumData.Data lithium$getData();

   public record Data(
      GameEventDispatcherStorage gameEventDispatchers,
      class_1799 ominousBanner,
      ReferenceOpenHashSet<class_1408> activeNavigations,
      LithiumInterner<SectionedBlockChangeTracker> blockChangeTrackers,
      LithiumInterner<SectionedEntityMovementTracker<?, ?>> entityMovementTrackers,
      Long2ReferenceOpenHashMap<ChunkSectionChangeCallback> chunkSectionChangeCallbacks
   ) {
      public Data(class_7874 registries) {
         this(
            new GameEventDispatcherStorage(),
            Objects.requireNonNullElse(registries, class_5455.field_40585)
               .method_46759(class_7924.field_41252)
               .<class_1799>map(class_3765::method_16515)
               .orElse(null),
            new ReferenceOpenHashSet(),
            new LithiumInterner<>(),
            new LithiumInterner<>(),
            new Long2ReferenceOpenHashMap()
         );
      }
   }
}
