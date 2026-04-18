package org.embeddedt.modernfix.common.mixin.perf.ticking_chunk_alloc;

import java.util.Collections;
import java.util.Map;
import net.minecraft.class_2791;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = class_2791.class, priority = 800)
public class ChunkAccessMixin {
   @Shadow
   @Final
   private Map<?, ?> field_34553;
   private Map<?, ?> mfix$structureRefsView;

   @Overwrite
   public Map<?, ?> method_12179() {
      if (this.field_34553.isEmpty()) {
         return Collections.emptyMap();
      } else {
         Map<?, ?> view = this.mfix$structureRefsView;
         if (view == null) {
            this.mfix$structureRefsView = view = Collections.unmodifiableMap(this.field_34553);
         }

         return view;
      }
   }
}
