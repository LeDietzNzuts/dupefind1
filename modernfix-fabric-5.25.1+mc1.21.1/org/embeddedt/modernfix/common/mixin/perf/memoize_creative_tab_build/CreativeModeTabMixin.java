package org.embeddedt.modernfix.common.mixin.perf.memoize_creative_tab_build;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.class_1761;
import net.minecraft.class_7923;
import net.minecraft.class_1761.class_7916;
import net.minecraft.class_1761.class_8128;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_1761.class)
public abstract class CreativeModeTabMixin {
   @Unique
   private class_8128 mfix$oldParameters;
   @Unique
   private static boolean MFIX$REBUILT_NON_CATEGORY = false;

   @Shadow
   public abstract class_7916 method_47312();

   @WrapMethod(method = "buildContents")
   private synchronized void buildContentsIfChanged(class_8128 parameters, Operation<Void> original) {
      synchronized (class_1761.class) {
         if (this.mfix$oldParameters == null || this.mfix$oldParameters.method_48932(parameters.comp_1251(), parameters.comp_1252(), parameters.comp_1253())) {
            original.call(new Object[]{parameters});
            if (this.method_47312() == class_7916.field_41052) {
               if (MFIX$REBUILT_NON_CATEGORY) {
                  for (class_1761 tab : class_7923.field_44687) {
                     if (tab.method_47312() != class_7916.field_41052) {
                        ((CreativeModeTabMixin)tab).mfix$oldParameters = null;
                     }
                  }

                  MFIX$REBUILT_NON_CATEGORY = false;
               }
            } else {
               MFIX$REBUILT_NON_CATEGORY = true;
            }
         }

         this.mfix$oldParameters = parameters;
      }
   }
}
