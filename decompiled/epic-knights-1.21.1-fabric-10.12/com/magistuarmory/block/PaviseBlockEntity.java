package com.magistuarmory.block;

import com.magistuarmory.item.PaviseItem;
import java.util.function.Supplier;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2509;
import net.minecraft.class_2520;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_9304;
import net.minecraft.class_9307;
import net.minecraft.class_9334;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.NotNull;

public class PaviseBlockEntity extends class_2586 {
   String shieldId;
   @Nullable
   private class_1767 baseColor;
   private class_9307 patterns;
   private boolean enchanted = false;
   private class_2487 stackCompound;

   public PaviseBlockEntity(Supplier<class_2591<PaviseBlockEntity>> type, class_2338 blockpos, class_2680 blockstate) {
      super(type.get(), blockpos, blockstate);
      this.patterns = class_9307.field_49404;
   }

   public class_9307 getPatterns() {
      return this.patterns;
   }

   public void fromItem(class_1799 stack) {
      if (this.method_10997() != null && !this.method_10997().method_8608()) {
         if (stack.method_7909() instanceof PaviseItem paviseitem) {
            class_9304 enchantments = (class_9304)stack.method_57824(class_9334.field_49633);
            this.enchanted = enchantments != null && !enchantments.method_57543();
            this.shieldId = paviseitem.getId();
            this.baseColor = (class_1767)stack.method_57824(class_9334.field_49620);
            this.patterns = (class_9307)stack.method_57824(class_9334.field_49619);
            this.stackCompound = (class_2487)stack.method_57358(this.method_10997().method_30349());
            this.method_5431();
         }
      }
   }

   protected void method_11007(class_2487 compound, class_7874 provider) {
      super.method_11007(compound, provider);
      compound.method_10582("ShieldId", this.shieldId);
      if (this.baseColor != null) {
         compound.method_10569("Base", this.baseColor.method_7789());
      }

      compound.method_10556("Enchanted", this.enchanted);
      if (this.stackCompound != null) {
         compound.method_10566("ItemStack", this.stackCompound);
      }

      if (this.patterns != null) {
         compound.method_10566(
            "patterns", (class_2520)class_9307.field_49405.encodeStart(provider.method_57093(class_2509.field_11560), this.patterns).getOrThrow()
         );
      }
   }

   public boolean hasFoil() {
      return this.enchanted;
   }

   public boolean isPainted() {
      return this.getBaseColor() != null;
   }

   public void method_11014(class_2487 compound, class_7874 provider) {
      super.method_11014(compound, provider);
      this.shieldId = compound.method_10558("ShieldId");
      if (compound.method_10545("Base")) {
         this.baseColor = class_1767.method_7791(compound.method_10550("Base"));
      }

      this.stackCompound = compound.method_10562("ItemStack");
      this.enchanted = compound.method_10577("Enchanted");
      if (compound.method_10545("patterns")) {
         class_9307.field_49405
            .parse(provider.method_57093(class_2509.field_11560), compound.method_10580("patterns"))
            .resultOrPartial(string -> {})
            .ifPresent(patterns -> this.patterns = patterns);
      }
   }

   public class_2622 getUpdatePacket() {
      return class_2622.method_38585(this);
   }

   @NotNull
   public class_2487 method_16887(class_7874 provider) {
      return this.method_38244(provider);
   }

   public class_1799 getStack() {
      return this.method_10997() == null
         ? class_1799.field_8037
         : class_1799.method_57360(this.method_10997().method_30349(), this.stackCompound).orElse(class_1799.field_8037);
   }

   public class_1767 getBaseColor() {
      return this.baseColor;
   }

   public String getShieldId() {
      return this.shieldId;
   }
}
