package com.magistuarmory.item;

import com.magistuarmory.client.render.ModRender;
import com.magistuarmory.client.render.tileentity.HeraldryItemStackRenderer;
import com.magistuarmory.util.CombatHelper;
import com.magistuarmory.util.ModDamageSources;
import dev.architectury.platform.Platform;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import java.util.List;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_1282;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1746;
import net.minecraft.class_1799;
import net.minecraft.class_1819;
import net.minecraft.class_1836;
import net.minecraft.class_1856;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3489;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_5617.class_5618;
import org.jetbrains.annotations.NotNull;

public class MedievalShieldItem extends class_1819 implements IHasModelProperty {
   private final ShieldType type;
   private final String id;
   private final class_2960 location;
   private final boolean is3d;
   private Supplier<class_1856> repairItem = () -> class_1856.method_8106(class_3489.field_15537);
   protected HeraldryItemStackRenderer renderer;
   private final boolean paintable;
   private final float maxBlockDamage;
   private final float weight;

   public MedievalShieldItem(String id, class_2960 location, class_1793 properties, ModItemTier material, boolean paintable, boolean is3d, ShieldType type) {
      super(properties.method_7889(1).method_7895(type.getDurability(material)));
      this.type = type;
      this.id = id;
      this.location = location;
      this.is3d = is3d;
      this.paintable = paintable;
      this.maxBlockDamage = type.getMaxBlockDamage() + material.method_8028();
      this.weight = type.getWeight() + material.method_8028();
      if (type.isRepairable()) {
         this.repairItem = material::method_8023;
      }

      if (this.is3d && Platform.getEnv() == EnvType.CLIENT) {
         this.renderer = ModRender.createHeraldryItemStackRenderer(this.id, this.location);
      }
   }

   public String getId() {
      return this.id;
   }

   public class_2960 getLocation() {
      return this.location;
   }

   public void method_7851(@NotNull class_1799 stack, class_9635 tooltipContext, List<class_2561> list, class_1836 flag) {
      list.add(class_2561.method_43469("maxdamageblock", new Object[]{this.getMaxBlockDamage()}).method_27692(class_124.field_1078));
      list.add(class_2561.method_43469("kgweight", new Object[]{this.getWeight()}).method_27692(class_124.field_1078));
      if (this.getWeight() >= 10.0F) {
         list.add(class_2561.method_43471("slowmovementspeed").method_27692(class_124.field_1061));
      }

      class_1746.method_7705(stack, list);
   }

   public void method_7888(class_1799 stack, class_1937 level, class_1297 entity, int i, boolean selected) {
      if (this.getWeight() >= 10.0F
         && entity instanceof class_1309 livingentity
         && (livingentity.method_6079() == stack || livingentity.method_6047() == stack)) {
         livingentity.method_6092(new class_1293(class_1294.field_5909, 40, 0, false, false, false));
      }

      super.method_7888(stack, level, entity, i, selected);
   }

   protected float getWeight() {
      return this.weight;
   }

   public String method_7866(class_1799 stack) {
      return super.method_7876();
   }

   public int method_7881(class_1799 stack, class_1309 entity) {
      return (int)(12000.0F * this.weight);
   }

   public boolean method_7878(class_1799 stack, class_1799 stack2) {
      return this.repairItem.get().method_8093(stack2);
   }

   public boolean isPaintable() {
      return this.paintable;
   }

   public void loadModel(class_5618 context) {
      if (this.is3d) {
         this.renderer.loadModel(context);
      }
   }

   public HeraldryItemStackRenderer getRenderer() {
      return this.renderer;
   }

   public float getMaxBlockDamage() {
      return this.maxBlockDamage;
   }

   public void onBlocked(class_1799 stack, float damage, class_1309 victim, class_1282 source) {
      try {
         if (ModDamageSources.isAdditional(source)) {
            return;
         }
      } catch (NullPointerException var8) {
         System.out.println(var8.getMessage());
         return;
      }

      class_1297 attacker = source.method_5529();
      float f = CombatHelper.getArmorPiercingFactor(attacker);
      if (damage > this.getMaxBlockDamage()) {
         f *= 1.5F;
         float damage2 = damage - this.getMaxBlockDamage();
         victim.method_5643(ModDamageSources.additional(), damage2);
      }

      stack.method_7970((int)(f * damage), victim, class_1304.field_6173);
   }

   @Environment(EnvType.CLIENT)
   @Override
   public void registerModelProperty() {
      ItemPropertiesRegistry.register(
         this,
         class_2960.method_60656("blocking"),
         (stack, level, entity, i) -> entity != null && entity.method_6115() && entity.method_6030() == stack ? 1.0F : 0.0F
      );
   }

   public boolean is3d() {
      return this.is3d;
   }
}
