package com.magistuarmory.client.render.fabric;

import com.magistuarmory.api.item.ModItemsProvider;
import com.magistuarmory.client.render.ModRender;
import com.magistuarmory.client.render.entity.layer.ArmorDecorationLayer;
import com.magistuarmory.client.render.entity.layer.HorseArmorDecorationLayer;
import com.magistuarmory.client.render.model.decoration.ArmorDecorationModelSet;
import com.magistuarmory.client.render.tileentity.HeraldryItemStackRenderer;
import com.magistuarmory.fabric.client.render.entity.layer.MedievalArmorLayer;
import com.magistuarmory.fabric.client.render.tileentity.HeraldryItemStackRendererFabric;
import com.magistuarmory.item.MedievalShieldItem;
import com.magistuarmory.item.ModItems;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.HashMap;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback.RegistrationHelper;
import net.minecraft.class_1007;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_572;
import net.minecraft.class_910;
import net.minecraft.class_922;
import net.minecraft.class_5617.class_5618;

@Environment(EnvType.CLIENT)
public class ModRenderImpl {
   private static final Map<class_922<?, ?>, ArmorDecorationLayer<?, ?>> ARMOR_DECORATION_LAYERS = new HashMap<>();
   private static final Map<class_922<?, ?>, HorseArmorDecorationLayer> HORSE_ARMOR_DECORATION_LAYERS = new HashMap<>();

   static <T extends class_1309, M extends class_572<T>> void addLayers(
      ModItemsProvider content, class_1299<? extends class_1309> entitytype, class_922<?, ?> renderer, RegistrationHelper helper, class_5618 context
   ) {
      if (!content.armorDecorationItems.isEmpty()) {
         if (renderer.method_4038() instanceof class_572) {
            addArmorDecorationLayer((class_922<T, M>)renderer, content, context, helper);
         } else if (renderer instanceof class_1007 renderer0) {
            addArmorDecorationLayer(renderer0, content, context, helper);
         }

         if (renderer instanceof class_910 renderer0 && content instanceof ModItems) {
            addHorseArmorDecorationLayer(renderer0, content, context, helper);
         }
      }
   }

   public static <T extends class_1309, M extends class_572<T>> void addArmorDecorationLayer(
      class_922<T, M> renderer, ModItemsProvider content, class_5618 context, RegistrationHelper helper
   ) {
      ArmorDecorationLayer<T, M> decorationLayer;
      if (!ARMOR_DECORATION_LAYERS.containsKey(renderer)) {
         decorationLayer = new ArmorDecorationLayer<>(new ArmorDecorationModelSet<>(), renderer, context, class_2960.method_60655("magistuarmory", "surcoat"));
         helper.register(decorationLayer);
         ARMOR_DECORATION_LAYERS.put(renderer, decorationLayer);
      } else {
         decorationLayer = (ArmorDecorationLayer<T, M>)ARMOR_DECORATION_LAYERS.get(renderer);
      }

      decorationLayer.registerDecorations(content.armorDecorationItems, context);
   }

   public static void addHorseArmorDecorationLayer(class_910 renderer, ModItemsProvider content, class_5618 context, RegistrationHelper helper) {
      if (!HORSE_ARMOR_DECORATION_LAYERS.containsKey(renderer)) {
         HorseArmorDecorationLayer decorationLayer = new HorseArmorDecorationLayer(
            renderer, context, class_2960.method_60655("magistuarmory", "textures/entity/horse/armor/caparison.png"), "caparison"
         );
         helper.register(decorationLayer);
         HORSE_ARMOR_DECORATION_LAYERS.put(renderer, decorationLayer);
      }
   }

   public static void setupPlatform(ModItemsProvider content) {
      MedievalArmorLayer layer = new MedievalArmorLayer();

      for (RegistrySupplier<? extends class_1792> supplier : content.armorItems) {
         ArmorRenderer.register(layer, new class_1935[]{(class_1935)supplier.get()});
      }

      for (RegistrySupplier<? extends MedievalShieldItem> supplier : content.shieldItems) {
         MedievalShieldItem shield = (MedievalShieldItem)supplier.get();
         if (((MedievalShieldItem)supplier.get()).is3d()) {
            BuiltinItemRendererRegistry.INSTANCE.register((class_1935)supplier.get(), (DynamicItemRenderer)shield.getRenderer());
         }
      }
   }

   public static void registerModelsLoadListener(ModItemsProvider content) {
      LivingEntityFeatureRendererRegistrationCallback.EVENT
         .register((LivingEntityFeatureRendererRegistrationCallback)(entitytype, renderer, helper, context) -> {
            ModRender.loadModels(content, context);
            addLayers(content, entitytype, renderer, helper, context);
         });
   }

   public static HeraldryItemStackRenderer createHeraldryItemStackRenderer(String id, class_2960 location) {
      return new HeraldryItemStackRendererFabric(id, location);
   }
}
