package noobanidus.mods.lootr.fabric.client.block;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelResolver;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelResolver.Context;
import net.minecraft.class_1100;
import net.minecraft.class_2960;
import noobanidus.mods.lootr.common.api.LootrAPI;
import org.jetbrains.annotations.Nullable;

public class BarrelModelLoader implements ModelLoadingPlugin, ModelResolver {
   public static final BarrelModelLoader INSTANCE = new BarrelModelLoader();
   private static final class_2960 LOOTR_BARREL_MODEL_UNOPENED = LootrAPI.rl("block/lootr_barrel");
   private static final class_2960 LOOTR_BARREL_MODEL_OPENED = LootrAPI.rl("block/lootr_barrel_open");
   private static final class_2960 LOOTR_BARREL_UNOPENED = LootrAPI.rl("block/lootr_barrel_unopened");
   private static final class_2960 LOOTR_BARREL_UNOPENED_OPEN = LootrAPI.rl("block/lootr_barrel_unopened_open");
   private static final class_2960 LOOTR_OPENED_BARREL = LootrAPI.rl("block/lootr_opened_barrel");
   private static final class_2960 LOOTR_OPENED_BARREL_OPEN = LootrAPI.rl("block/lootr_opened_barrel_open");
   private static final class_2960 VANILLA = class_2960.method_60655("minecraft", "block/barrel");
   private static final class_2960 VANILLA_OPEN = class_2960.method_60655("minecraft", "block/barrel_open");
   private static final class_2960 OLD_LOOTR_BARREL_UNOPENED = LootrAPI.rl("block/old_lootr_barrel_unopened");
   private static final class_2960 OLD_LOOTR_BARREL_UNOPENED_OPEN = LootrAPI.rl("block/old_lootr_barrel_unopened_open");
   private static final class_2960 OLD_LOOTR_OPENED_BARREL = LootrAPI.rl("block/old_lootr_opened_barrel");
   private static final class_2960 OLD_LOOTR_OPENED_BARREL_OPEN = LootrAPI.rl("block/old_lootr_opened_barrel_open");

   @Nullable
   public class_1100 resolveModel(Context context) {
      class_2960 resourceId = context.id();
      if (resourceId == null) {
         return null;
      } else if (resourceId.equals(LOOTR_BARREL_MODEL_UNOPENED)) {
         return new BarrelModel(
            context.getOrLoadModel(LOOTR_OPENED_BARREL),
            context.getOrLoadModel(LOOTR_BARREL_UNOPENED),
            context.getOrLoadModel(VANILLA),
            context.getOrLoadModel(OLD_LOOTR_OPENED_BARREL),
            context.getOrLoadModel(OLD_LOOTR_BARREL_UNOPENED)
         );
      } else {
         return resourceId.equals(LOOTR_BARREL_MODEL_OPENED)
            ? new BarrelModel(
               context.getOrLoadModel(LOOTR_OPENED_BARREL_OPEN),
               context.getOrLoadModel(LOOTR_BARREL_UNOPENED_OPEN),
               context.getOrLoadModel(VANILLA_OPEN),
               context.getOrLoadModel(OLD_LOOTR_OPENED_BARREL_OPEN),
               context.getOrLoadModel(OLD_LOOTR_BARREL_UNOPENED_OPEN)
            )
            : null;
      }
   }

   public void onInitializeModelLoader(net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin.Context pluginContext) {
      pluginContext.resolveModel().register(this);
   }
}
