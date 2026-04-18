package org.embeddedt.modernfix.common.mixin.devenv;

import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.class_310;
import net.minecraft.class_542;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(class_310.class)
@ClientOnlyMixin
public class MinecraftMixin {
   @Overwrite
   private UserApiService method_31382(YggdrasilAuthenticationService yggdrasilAuthenticationService, class_542 arg) {
      return UserApiService.OFFLINE;
   }
}
