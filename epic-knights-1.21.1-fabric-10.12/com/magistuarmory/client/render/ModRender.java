package com.magistuarmory.client.render;

import com.magistuarmory.api.item.ModItemsProvider;
import com.magistuarmory.client.render.fabric.ModRenderImpl;
import com.magistuarmory.client.render.model.ModModels;
import com.magistuarmory.client.render.model.item.MedievalShieldModel;
import com.magistuarmory.client.render.tileentity.HeraldryItemStackRenderer;
import com.magistuarmory.client.render.tileentity.PaviseBlockRenderer;
import com.magistuarmory.item.DyeableItemLike;
import com.magistuarmory.item.IHasModelProperty;
import com.magistuarmory.item.MedievalShieldItem;
import com.magistuarmory.item.PaviseItem;
import com.magistuarmory.item.armor.MedievalArmorItem;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_3879;
import net.minecraft.class_5601;
import net.minecraft.class_572;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_5617.class_5618;

@Environment(EnvType.CLIENT)
public class ModRender {
   public static class_572<class_1309> INNER_ARMOR = null;
   public static class_572<class_1309> OUTER_ARMOR = null;
   public static Map<class_2960, class_572<class_1309>> ARMOR_MODELS_CACHE = new HashMap<>();
   public static Map<class_2960, class_3879> SHIELD_MODELS_CACHE = new HashMap<>();

   public static void setup(ModItemsProvider content) {
      for (RegistrySupplier<? extends class_1792> supplier : content.dyeableItems) {
         ColorHandlerRegistry.registerItemColors(
            (stack, i) -> i > 0 ? -1 : ((DyeableItemLike)stack.method_7909()).getColor(stack), new class_1935[]{(class_1935)supplier.get()}
         );
      }

      for (RegistrySupplier<? extends class_1792> supplier : content.items) {
         if (supplier.get() instanceof IHasModelProperty havingproperty) {
            havingproperty.registerModelProperty();
         }
      }

      content.shieldItems
         .stream()
         .filter(s -> s.get() instanceof PaviseItem)
         .map(s -> (PaviseItem)s.get())
         .forEach(
            p -> BlockEntityRendererRegistry.register(p.getBlock().getEntityType(), context -> new PaviseBlockRenderer(context, p.getId(), p.getLocation()))
         );
      setupPlatform(content);
   }

   @ExpectPlatform
   @Transformed
   public static void setupPlatform(ModItemsProvider content) {
      ModRenderImpl.setupPlatform(content);
   }

   @ExpectPlatform
   @Transformed
   public static void registerModelsLoadListener(ModItemsProvider content) {
      ModRenderImpl.registerModelsLoadListener(content);
   }

   public static void registerRenderers() {
   }

   public static void loadModels(ModItemsProvider content, class_5618 context) {
      OUTER_ARMOR = new class_572(context.method_32167(ModModels.DEFAULT_ARMOR_LOCATION));
      INNER_ARMOR = new class_572(context.method_32167(ModModels.DEFAULT_LEGGINGS_LOCATION));

      for (RegistrySupplier<? extends MedievalShieldItem> supplier : content.shieldItems) {
         loadShieldModel(context, (MedievalShieldItem)supplier.get());
      }

      for (RegistrySupplier<? extends MedievalArmorItem> supplier : content.armorItems) {
         loadArmorModel(context, (MedievalArmorItem)supplier.get());
      }
   }

   public static void loadShieldModel(class_5618 context, MedievalShieldItem shield) {
      if (shield.is3d()) {
         class_2960 location = shield.getLocation();
         class_3879 model = SHIELD_MODELS_CACHE.computeIfAbsent(
            location, k -> new MedievalShieldModel(context.method_32167(ModModels.createLocation(location)))
         );
         shield.getRenderer().setModel(model);
      }
   }

   public static void loadArmorModel(class_5618 context, MedievalArmorItem armor) {
      Optional<class_5601> location = armor.getArmorType().getModelLocation();
      class_572<class_1309> model = location.<class_572<class_1309>>map(
            l -> ARMOR_MODELS_CACHE.computeIfAbsent(l.method_35743(), k -> new class_572(context.method_32167(l)))
         )
         .orElseGet(() -> armor.method_48398() == class_8051.field_41936 ? INNER_ARMOR : OUTER_ARMOR);
      armor.setModel(model);
   }

   @ExpectPlatform
   @Transformed
   public static HeraldryItemStackRenderer createHeraldryItemStackRenderer(String id, class_2960 location) {
      return ModRenderImpl.createHeraldryItemStackRenderer(id, location);
   }
}
