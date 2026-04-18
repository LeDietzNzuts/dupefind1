package noobanidus.mods.lootr.fabric.client.block;

import java.util.HashMap;
import java.util.Map;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelResolver;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelResolver.Context;
import net.minecraft.class_1100;
import net.minecraft.class_2960;
import noobanidus.mods.lootr.common.api.client.ILootrFabricModelProvider;
import noobanidus.mods.lootr.common.impl.LootrServiceRegistry;
import org.jetbrains.annotations.Nullable;

public class CustomModelLoader implements ModelLoadingPlugin, ModelResolver, ILootrFabricModelProvider.Acceptor {
   public static final CustomModelLoader INSTANCE = new CustomModelLoader();
   private static final Map<class_2960, CustomModelLoader.CustomBarrelModelInfo> BARREL_MODEL_MAP = new HashMap<>();
   private static final Map<class_2960, CustomModelLoader.CustomBrushableModelInfo> BRUSHABLE_MODEL_MAP = new HashMap<>();

   @Nullable
   public class_1100 resolveModel(Context context) {
      class_2960 resourceId = context.id();
      if (resourceId == null) {
         return null;
      } else if (BARREL_MODEL_MAP.containsKey(resourceId)) {
         CustomModelLoader.CustomBarrelModelInfo info = BARREL_MODEL_MAP.get(resourceId);
         return new CustomBarrelModel(
            context.getOrLoadModel(info.opened), context.getOrLoadModel(info.unopened), info.vanilla != null ? context.getOrLoadModel(info.vanilla) : null
         );
      } else if (BRUSHABLE_MODEL_MAP.containsKey(resourceId)) {
         CustomModelLoader.CustomBrushableModelInfo info = BRUSHABLE_MODEL_MAP.get(resourceId);
         return new BrushableModel(
            context.getOrLoadModel(info.opened),
            context.getOrLoadModel(info.stage0),
            context.getOrLoadModel(info.stage1),
            context.getOrLoadModel(info.stage2),
            context.getOrLoadModel(info.stage3)
         );
      } else {
         return null;
      }
   }

   public void onInitializeModelLoader(net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin.Context pluginContext) {
      pluginContext.resolveModel().register(this);

      for (ILootrFabricModelProvider provider : LootrServiceRegistry.getModelAppenders()) {
         provider.provideModels(this);
      }
   }

   @Override
   public void acceptCustomModel(
      class_2960 modelName, class_2960 modelOpenedLocation, class_2960 modelUnopenedLocation, @Nullable class_2960 modelVanillaLocation
   ) {
      BARREL_MODEL_MAP.put(modelName, new CustomModelLoader.CustomBarrelModelInfo(modelOpenedLocation, modelUnopenedLocation, modelVanillaLocation));
   }

   @Override
   public void acceptBrushableModel(class_2960 modelName, class_2960 opened, class_2960 stage0, class_2960 stage1, class_2960 stage2, class_2960 stage3) {
      BRUSHABLE_MODEL_MAP.put(modelName, new CustomModelLoader.CustomBrushableModelInfo(opened, stage0, stage1, stage2, stage3));
   }

   record CustomBarrelModelInfo(class_2960 opened, class_2960 unopened, @Nullable class_2960 vanilla) {
   }

   record CustomBrushableModelInfo(class_2960 opened, class_2960 stage0, class_2960 stage1, class_2960 stage2, class_2960 stage3) {
   }
}
