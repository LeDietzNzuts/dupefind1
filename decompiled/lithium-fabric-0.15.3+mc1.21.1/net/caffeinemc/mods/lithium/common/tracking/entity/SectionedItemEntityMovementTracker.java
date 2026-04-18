package net.caffeinemc.mods.lithium.common.tracking.entity;

import java.util.ArrayList;
import java.util.List;
import net.caffeinemc.mods.lithium.common.util.tuples.WorldSectionBox;
import net.caffeinemc.mods.lithium.common.world.LithiumData;
import net.caffeinemc.mods.lithium.mixin.block.hopper.EntitySectionAccessor;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_3218;
import net.minecraft.class_3509;

public class SectionedItemEntityMovementTracker<S extends class_1297> extends SectionedEntityMovementTracker<class_1297, S> {
   public SectionedItemEntityMovementTracker(WorldSectionBox worldSectionBox, Class<S> clazz) {
      super(worldSectionBox, clazz);
   }

   public static <S extends class_1297> SectionedItemEntityMovementTracker<S> registerAt(class_3218 world, class_238 interactionArea, Class<S> clazz) {
      WorldSectionBox worldSectionBox = WorldSectionBox.entityAccessBox(world, interactionArea);
      SectionedItemEntityMovementTracker<S> tracker = new SectionedItemEntityMovementTracker<>(worldSectionBox, clazz);
      tracker = ((LithiumData)world).lithium$getData().entityMovementTrackers().getCanonical(tracker);
      tracker.register(world);
      return tracker;
   }

   public List<S> getEntities(class_238 interactionArea) {
      ArrayList<S> entities = new ArrayList<>();

      for (int sectionIndex = 0; sectionIndex < this.sortedSections.size(); sectionIndex++) {
         if (this.sectionVisible[sectionIndex]) {
            class_3509<S> collection = ((EntitySectionAccessor)this.sortedSections.get(sectionIndex)).getCollection();

            for (S entity : collection.method_15216(this.clazz)) {
               if (entity.method_5805()) {
                  class_238 entityBoundingBox = entity.method_5829();
                  if (entityBoundingBox.method_994(interactionArea)) {
                     entities.add(entity);
                  }
               }
            }
         }
      }

      return entities;
   }
}
