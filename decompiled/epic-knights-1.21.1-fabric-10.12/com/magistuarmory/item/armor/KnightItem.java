package com.magistuarmory.item.armor;

import com.magistuarmory.component.ModDataComponents;
import com.magistuarmory.item.ArmorDecorationItem;
import com.magistuarmory.item.DyeableArmorDecorationItem;
import com.magistuarmory.item.DyeableItemLike;
import com.magistuarmory.item.IHasModelProperty;
import com.magistuarmory.item.ModItems;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_9331;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_5253.class_5254;

public class KnightItem extends MedievalArmorItem implements ISurcoat, DyeableItemLike, IHasModelProperty {
   public KnightItem(ArmorType material, class_8051 type, class_1793 properties) {
      super(material, type, properties);
   }

   @Override
   public int getColor(class_1799 itemstack) {
      ArmorDecorationItem.DecorationInfo info = this.getPlumeDecorationInfo(itemstack);
      return class_5254.method_57174(info != null ? info.color() : 0);
   }

   public boolean hasPlume(class_1799 itemstack) {
      return this.getPlumeDecorationInfo(itemstack) != null;
   }

   public ArmorDecorationItem.DecorationInfo getPlumeDecorationInfo(class_1799 itemstack) {
      if (!itemstack.method_57826((class_9331)ModDataComponents.ARMOR_DECORATION.get())) {
         return null;
      } else {
         String plumename = ((DyeableArmorDecorationItem)ModItems.BIG_PLUME_DECORATION.get()).getResourceLocation().toString();
         return ArmorDecorationItem.createDecorations(ArmorDecorationItem.getDecorationTags(itemstack))
            .stream()
            .filter(d -> Objects.equals(d.name(), plumename))
            .findFirst()
            .orElse(null);
      }
   }

   @Environment(EnvType.CLIENT)
   @Override
   public void registerModelProperty() {
      ItemPropertiesRegistry.register(
         this, class_2960.method_60655("magistuarmory", "has_plume"), (stack, level, entity, i) -> this.hasPlume(stack) ? 1.0F : 0.0F
      );
   }
}
