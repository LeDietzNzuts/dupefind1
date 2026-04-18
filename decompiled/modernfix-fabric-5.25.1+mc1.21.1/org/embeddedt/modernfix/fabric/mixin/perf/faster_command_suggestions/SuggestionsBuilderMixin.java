package org.embeddedt.modernfix.fabric.mixin.perf.faster_command_suggestions;

import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.List;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SuggestionsBuilder.class)
public class SuggestionsBuilderMixin {
   @Unique
   private static final int MAX_SUGGESTIONS = 10000;
   @Shadow(remap = false)
   @Final
   @Mutable
   private List<Suggestion> result;

   @Redirect(method = "*", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"), require = 0)
   private <T> boolean addIfFits(List<T> list, T entry) {
      return list == this.result && list.size() >= 10000 ? false : list.add(entry);
   }
}
