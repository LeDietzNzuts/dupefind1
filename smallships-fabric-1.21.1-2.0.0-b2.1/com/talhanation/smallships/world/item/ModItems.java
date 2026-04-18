package com.talhanation.smallships.world.item;

import com.talhanation.smallships.world.item.fabric.ModItemsImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1792;
import net.minecraft.class_1690.class_1692;

public class ModItems {
   public static final class_1792 SAIL = getItem("sail");
   public static final class_1792 CANNON = getItem("cannon");
   public static final CannonBallItem CANNON_BALL = (CannonBallItem)getItem("cannon_ball");
   public static final Map<class_1692, class_1792> COG_ITEMS = new HashMap<>(class_1692.values().length);
   public static final Map<class_1692, class_1792> BRIGG_ITEMS = new HashMap<>(class_1692.values().length);
   public static final Map<class_1692, class_1792> GALLEY_ITEMS = new HashMap<>(class_1692.values().length);
   public static final Map<class_1692, class_1792> DRAKKAR_ITEMS = new HashMap<>(class_1692.values().length);

   @ExpectPlatform
   @Transformed
   public static class_1792 getItem(String id) {
      return ModItemsImpl.getItem(id);
   }

   static {
      class_1692[] boatTypes = class_1692.values();

      for (class_1692 type : boatTypes) {
         String name = type.method_7559().replaceAll("[^a-z0-9_.-]", "_");
         COG_ITEMS.put(type, getItem(name + "_cog"));
         BRIGG_ITEMS.put(type, getItem(name + "_brigg"));
         GALLEY_ITEMS.put(type, getItem(name + "_galley"));
         DRAKKAR_ITEMS.put(type, getItem(name + "_drakkar"));
      }
   }
}
