package org.embeddedt.modernfix.common.mixin.feature.remove_chat_signing;

import com.mojang.authlib.minecraft.UserApiService;
import java.nio.file.Path;
import net.minecraft.class_320;
import net.minecraft.class_7853;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(class_7853.class)
@ClientOnlyMixin
public interface ProfileKeyPairManagerMixin {
   @Overwrite
   static class_7853 method_46532(UserApiService userApiService, class_320 user, Path gameDirectory) {
      return class_7853.field_40800;
   }
}
