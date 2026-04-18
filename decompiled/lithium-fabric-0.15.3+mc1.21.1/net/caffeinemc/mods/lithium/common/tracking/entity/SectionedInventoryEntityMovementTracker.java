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

public class SectionedInventoryEntityMovementTracker<S> extends SectionedEntityMovementTracker<class_1297, S> {
   public SectionedInventoryEntityMovementTracker(WorldSectionBox entityAccessBox, Class<S> clazz) {
      super(entityAccessBox, clazz);
   }

   public static <S> SectionedInventoryEntityMovementTracker<S> registerAt(class_3218 world, class_238 interactionArea, Class<S> clazz) {
      WorldSectionBox worldSectionBox = WorldSectionBox.entityAccessBox(world, interactionArea);
      SectionedInventoryEntityMovementTracker<S> tracker = new SectionedInventoryEntityMovementTracker<>(worldSectionBox, clazz);
      tracker = ((LithiumData)world).lithium$getData().entityMovementTrackers().getCanonical(tracker);
      tracker.register(world);
      return tracker;
   }

   public List<S> getEntities(class_238 box) {
      ArrayList<S> entities = new ArrayList<>();

      for (int i = 0; i < this.sortedSections.size(); i++) {
         if (this.sectionVisible[i]) {
            class_3509<S> collection = ((EntitySectionAccessor)this.sortedSections.get(i)).getCollection();

            for (S entity : collection.method_15216(this.clazz)) {
               class_1297 inventoryEntity = (class_1297)entity;
               if (inventoryEntity.method_5805() && inventoryEntity.method_5829().method_994(box)) {
                  entities.add(entity);
               }
            }
         }
      }

      return entities;
   }
}
